package com.kuaikai.game.mahjong.engine.calculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonGangCalculatorDetail;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.SetResult;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

/**
 * 结算器
 * 
 * @author zl
 */
public abstract class Calculator {
	
	protected static final Logger logger = LoggerFactory.getLogger("cacl");
	
	protected final MahjongDesk room;
	
	protected List<CommonCalculatorDetail> calcDetails = new ArrayList<CommonCalculatorDetail>();	// 当前局的计算细节列表（由杠、胡等产生），按计算优先顺序排序
	protected Map<Integer, List<ScoreDetail>> gainDetail = new HashMap<>(); 			// 当前局所有CalculatorDetail计算得到的赢家得分明细
	protected Map<Integer, List<ScoreDetail>> lostDetail = new HashMap<>();				// 当前局所有CalculatorDetail计算得到的输家失分明细
	protected Map<Integer, PlayerSetResult> playerSetResults = new HashMap<>(); 		// 当前局各玩家输赢明细
	
	//protected Map<Integer, PlayerGameResult> playerGameResults = new HashMap<>();		// 整场牌局统计(累计所有局、底或圈)，转到MJPlayer中
	
	public static final int INDEX_DI = 0;	// 底分在points中的位置，默认为0
	
	public Calculator(MahjongDesk room) {
		this.room = room;
	}

	public MahjongDesk getRoom() {
		return room;
	}

	/**
	 * 每局开始时调用
	 */
	public void onSetStart() {
		// 清除每局的计算器
		calcDetails.clear();
		gainDetail.clear();
		lostDetail.clear();
		playerSetResults.clear();
	}

	/**
	 * 默认只有一个总分
	 * 
	 */
	public int getPointsSize() {
		return 1;
	}
	
	/**
	 * 默认初始底分为0
	 */
	public int getInitDiFen() {
		return 0;
	}
	
	/**
	 * 游戏开始时调用
	 */
	public void onGameStart() {
		// 整场牌局统计
		//initPlayerGameResults();
	}
	
	/**
	 * 当前局计算前初始化数据
	 */
	protected void initPlayerSetResults() {
		// 初始化当前局各玩家明细
		playerSetResults.clear();
		for (MahjongPlayer player : room.getAllPlayers()) {
			playerSetResults.put(player.getId(), new PlayerSetResult(player, getPointsSize()));
		}
	}
	
	public Collection<PlayerSetResult> getPlayerSetResults() {
		return playerSetResults.values();
	}
	
	public PlayerSetResult getPlayerSetResult(MahjongPlayer player) {
		return playerSetResults.get(player.getId());
	}

	public void onHuangZhuang() {
		// 荒庄不荒杠
		if(room.getCreateRoomParam().getSettingBool(GameSetting.GANG_DE_FEN_HUANG_ZHUANG)) return;
		
		for (CommonCalculatorDetail calculatorDetail : calcDetails) {
			// 荒庄设置一下杠失效
			if (calculatorDetail instanceof CommonGangCalculatorDetail) {
				calculatorDetail.setInvalid(true);
			}
		}
	}

	/*
	 * 跟庄时调用
	 */
	public void onGenZhuang() {
		int rate = room.getCreateRoomParam().getSettingInt(GameSetting.BASE_RATE);
		CommonCalculatorDetail genZhuangCalculatorDetail = new CommonCalculatorDetail(room, rate, false, false, false);
		genZhuangCalculatorDetail.setMainType(JieSuan.GEN_ZHUANG);
		genZhuangCalculatorDetail.addLoser(room.getBanker());
		for(MahjongPlayer winner : room.getAllPlayers()) {
			if(winner.isBanker()) continue;
			genZhuangCalculatorDetail.addWinner(winner);
		}
		this.calcDetails.add(genZhuangCalculatorDetail);
	}
	
	public void onSetEnd() {
		if(room.getEngine().getCurrentSetResult().isHuangZhuang()) {
			onHuangZhuang();
		}
		
		// 本局结算
		calculate();
	}
	
	/*
	 * 本局结算（按局数和圈数玩法，底数玩法在DiCalculator中重载）
	 */
	protected void calculate() {
		// 计算各项分数，得到所有玩家得失分
		performCalculation();
		
		// 当前局各结算项计算结果和总得失分
		handleSetResult();
		
		// 更新整场玩家分数和统计
		handleGameResult();
	}

	protected void performCalculation() {
		// 计算有效分数项，计入玩家得失分
		for (CommonCalculatorDetail calculatorDetail : calcDetails) {
			if (calculatorDetail.calc()) {
				genGainAndLostDetails(calculatorDetail);
			}
		}
	}

	protected void genGainAndLostDetails(CommonCalculatorDetail calculatorDetail) {
		for (Map.Entry<Integer, ScoreDetail> entry : calculatorDetail.user2Gain.entrySet()) {
			int userid = entry.getKey();
			List<ScoreDetail> scoreDetails = this.gainDetail.get(userid);
			if (scoreDetails == null) {
				scoreDetails = new ArrayList<>();
				this.gainDetail.put(userid, scoreDetails);
			}
			scoreDetails.add(entry.getValue());
		}

		for (Map.Entry<Integer, ScoreDetail> entry : calculatorDetail.user2Lost.entrySet()) {
			int userid = entry.getKey();
			List<ScoreDetail> scoreDetails = this.lostDetail.get(userid);
			if (scoreDetails == null) {
				scoreDetails = new ArrayList<>();
				this.lostDetail.put(userid, scoreDetails);
			}
			scoreDetails.add(entry.getValue());
		}
	}
	
	protected void handleSetResult() {
		// 初始化
		initPlayerSetResults();
		
		// 将当前局各结算项计算结果和总得失分计入各玩家的 playerSetResult中
		for (Map.Entry<Integer, List<ScoreDetail>> entry : this.gainDetail.entrySet()) {
			int userid = entry.getKey();
			List<ScoreDetail> scoreDetails = entry.getValue();
			PlayerSetResult playerSetResult = this.playerSetResults.get(userid);
			for (ScoreDetail scoreDetail : scoreDetails) {
				if(scoreDetail.isToPay()) playerSetResult.getGamePoints().changePoint(INDEX_DI, scoreDetail.getScore());
				if(scoreDetail.isDisplay()) playerSetResult.addScoreDetail(scoreDetail);
				logger.info("gainscore room:" + room.getRoomid() + ",round:" + room.getCurSet() + ",userid:" + userid + "," + scoreDetail.toString());
			}
		}
		for (Map.Entry<Integer, List<ScoreDetail>> entry : this.lostDetail.entrySet()) {
			int userid = entry.getKey();
			List<ScoreDetail> scoreDetails = entry.getValue();
			PlayerSetResult playerSetResult = this.playerSetResults.get(userid);
			for (ScoreDetail scoreDetail : scoreDetails) {
				scoreDetail.setLost();
				if(scoreDetail.isToPay()) playerSetResult.getGamePoints().changePoint(INDEX_DI, scoreDetail.getScore());
				if(scoreDetail.isDisplay()) playerSetResult.addScoreDetail(scoreDetail);
				logger.info("lostscore room:" + room.getRoomid() + ",round:"+room.getCurSet() + ",userid:" + userid + "," + scoreDetail.toString());
			}
		}
		
		// 找出本局赢家和输家
		findWinnersAndLosers();
	}
	
	protected void findWinnersAndLosers() {
		SetResult setResult = room.getEngine().getCurrentSetResult();
		for (PlayerSetResult playerSetResult : playerSetResults.values()) {
			int point = playerSetResult.getGamePoints().getTotalPoint();
			if(point > 0) {
				setResult.addWinner(playerSetResult.getPlayer());
			} else if(point < 0) {
				setResult.addLoser(playerSetResult.getPlayer());
			}
		}
	}

	protected void handleGameResult() {
		// 更新玩家分数，更新整场统计
		StringBuilder logStrBuffer = new StringBuilder();
		for (PlayerSetResult playerSetResult : playerSetResults.values()) {
			int winPoint = playerSetResult.getGamePoints().getPoint(INDEX_DI);
			MahjongPlayer player = playerSetResult.getPlayer();
			player.getMjPlayer().getGameResult().getFinalGamePoints().changePoint(INDEX_DI, winPoint);
			logStrBuffer.append(",").append(player.getId()).append(":").append(player.getMjPlayer().getGameResult().getFinalGamePoints().getPoint(INDEX_DI));
		}
		logger.info("totalscore room:" + room.getRoomid() + ",set:" + room.getCurSet() + logStrBuffer.toString());
	}

	/**
	 * 胡动作通知，创建相关 CalculatorDetail
	 * 
	 * @param huOperation
	 * @return
	 */
	public void onHuOperation(HuOperation huOperation) {
		if(huOperation.isZimo()) {
			for(CommonCalculatorDetail detail : calcDetails) {
				detail.setZimo(true);
			}
		}
		
		createHuDetails(huOperation);
		handleGangSuiHu(huOperation);
		logger.info(huOperation.getPlayer().getLogPrefix().append(";PaiXins:").append(huOperation.getPaiXins()).toString());
	}


	/**
	 * 处理杠随胡
	 * 
	 */
	protected void handleGangSuiHu(HuOperation huOperation) {
		boolean gangSuiHu = room.getCreateRoomParam().getSettingBool(GameSetting.GANG_SUI_HU);
		boolean dianPaoPayDianGang = room.getCreateRoomParam().getSettingBool(GameSetting.DIAN_PAO_PAY_DIAN_GANG);
		boolean dianPaoPayOtherGang = room.getCreateRoomParam().getSettingBool(GameSetting.DIAN_PAO_PAY_OTHER_GANG);
		boolean zimo = huOperation.isZimo();
		MahjongPlayer huPlayer = huOperation.getPlayer();
		
		for(CommonCalculatorDetail calculatorDetail : this.calcDetails) {
			if(!(calculatorDetail instanceof CommonGangCalculatorDetail)) continue;
			// 杠随胡时，去掉非胡牌玩家的杠
			CommonGangCalculatorDetail gang = (CommonGangCalculatorDetail)calculatorDetail;
			if(gangSuiHu && !huPlayer.equals(gang.getOperation().getPlayer())) {
				calculatorDetail.setInvalid(true);
			}
			// 点炮
			if(!zimo) {
				switch(gang.getOperation().getOperType()) {
				case OperType.DIAN_GANG :
					// 点炮者支付点杠分
					if(dianPaoPayDianGang) {
						gang.setSingleLoser(huOperation.getPrePlayer());
					}
					break;
				default :
					// 点炮者支付其他杠分
					if(dianPaoPayOtherGang) {
						gang.setSingleLoser(huOperation.getPrePlayer());
					}
					break;
				}
			}
		}
	}
	
	/**
	 * 创建杠的结算方式
	 * 
	 * @param oper
	 * @return
	 */
	public CommonCalculatorDetail createGangDetail(BaseOperation oper) {
		if(!room.getCreateRoomParam().getSettingBool(GameSetting.GANG_DE_FEN)) return null;
		
		CommonGangCalculatorDetail gangCalculatorDetail = null;
		switch (oper.getOperType()) {
		case OperType.DIAN_GANG : // 点杠
		case OperType.BU_GANG : // 补杠
			gangCalculatorDetail = new CommonGangCalculatorDetail(oper, room.getCreateRoomParam().getSettingInt(GameSetting.BASE_RATE_MING_GANG), false, false);
			break;
		case OperType.AN_GANG : // 暗杠
			gangCalculatorDetail = new CommonGangCalculatorDetail(oper, room.getCreateRoomParam().getSettingInt(GameSetting.BASE_RATE_AN_GANG), false, false);
			break;
		}
		if (gangCalculatorDetail != null) {
			this.calcDetails.add(gangCalculatorDetail);
			oper.setCalculatorDetail(gangCalculatorDetail);
		}
		return gangCalculatorDetail;
	}
	
	/**
	 * 创建胡的结算方式
	 * 
	 * @param huOperation
	 * @return
	 */
	public abstract List<CommonCalculatorDetail> createHuDetails(HuOperation huOperation);
	
	public void postMultipleHu() {}
	
}
