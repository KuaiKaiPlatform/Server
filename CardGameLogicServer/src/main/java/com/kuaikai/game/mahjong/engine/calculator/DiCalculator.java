package com.kuaikai.game.mahjong.engine.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetailWithPriorityRate;
import com.kuaikai.game.mahjong.engine.constants.PlayMode;
import com.kuaikai.game.mahjong.engine.model.GamePoints;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.rafo.chess.common.utils.CollectionUtils;

public abstract class DiCalculator extends Calculator {

	protected Map<Integer, Integer> userScores = new HashMap<Integer, Integer>();	// 本局玩家得失分总和（未归零）
	protected Set<MahjongPlayer> positivePlayers = new HashSet<MahjongPlayer>();					// 有得分项的玩家
	
	public DiCalculator(MahjongDesk room) {
		super(room);
	}

	/**
	 * 每局开始时调用
	 */
	public void onSetStart() {
		super.onSetStart();
		userScores.clear();
		positivePlayers.clear();
	}
	
	/*
	 * 本局结算（按底数玩法）
	 */
	@Override
	protected void calculate() {
		if(!room.getEngine().checkPlayMode(PlayMode.DI)) {
			super.calculate();
			return;
		}
		
		calculateDi();
		
/*		// 计算各项分数，得到所有玩家得失分，记下有得分项的玩家
		performCalculation();
		
		// 底分够输，正常结算
		if(!checkNegative()) {
			// 当前局各结算项计算结果和总得失分
			super.handleSetResult();
			
			// 更新整场玩家分数和统计
			super.handleGameResult();
			
			return;
		}
		
		// 只有一名玩家有得分项时
		if(positivePlayers.size() == 1) {
			// 当前局各结算项计算结果和总得失分
			super.handleSetResult();
			
			// 更新整场玩家分数和统计
			handleGameResultWithSinglePositive();
			
			return;
		}
		
		// 有玩家负分并且多人有得分项时
		this.calculateWithNegativeAndMultiplePositive();*/
	}
	
	@Override
	protected void performCalculation() {
		// 计算有效分数项，计入玩家得失分
		for (CommonCalculatorDetail calculatorDetail : calcDetails) {
			if (calculatorDetail.calc()) {
				if(calculatorDetail.isToPay()) positivePlayers.addAll(calculatorDetail.getWinners());	// 记下有得分项的玩家
				genGainAndLostDetails(calculatorDetail);
			}
		}
	}
	
	/*
	 * 检查本局玩家底分扣除输分后是否为负
	 */
	protected boolean checkNegative() {
		genUserScores();
		for(Map.Entry<Integer, Integer> entry : userScores.entrySet()) {
			// 检查当前底的底分扣除输分，是否为负数
			MahjongPlayer player = room.getPlayerById(entry.getKey());
			GamePoints diPoints = player.getMjPlayer().getGameResult().getCurrentDiGamePoints();
			int diPoint = diPoints.getPoint(INDEX_DI);
			if(diPoint + entry.getValue() < 0) return true;
		}
		return false;
	}
	
	/*
	 * 计算本局玩家得失分总和（未归零）
	 */
	protected void genUserScores() {
		for (Map.Entry<Integer, List<ScoreDetail>> entry : this.gainDetail.entrySet()) {
			int uid = entry.getKey();
			List<ScoreDetail> scoreDetails = entry.getValue();
			for (ScoreDetail scoreDetail : scoreDetails) {
				if(scoreDetail.isToPay()) CollectionUtils.increaseBy(userScores, uid, scoreDetail.getScore());	
			}
		}
		for (Map.Entry<Integer, List<ScoreDetail>> entry : this.lostDetail.entrySet()) {
			int uid = entry.getKey();
			List<ScoreDetail> scoreDetails = entry.getValue();
			for (ScoreDetail scoreDetail : scoreDetails) {
				if(scoreDetail.isToPay()) CollectionUtils.increaseBy(userScores, uid, -scoreDetail.getScore());	
			}
		}
	}
	
	/*
	 * 按底数玩法时，计算各玩家底分，剩余底分为负数时，归0，重新计算赢家实际分数（适用于仅有一名玩家得分的情况）
	 */
	protected void handleGameResultWithSinglePositive() {
		// 更新玩家分数
		int totalLose = 0;
		for (PlayerSetResult playerSetResult : playerSetResults.values()) {
			int setPoint = playerSetResult.getGamePoints().getPoint(INDEX_DI);
			if(setPoint < 0) {	// 输家，底分不够时，归0，重新计算实际输分
				PlayerGameResult playerGameResult = playerSetResult.getPlayer().getMjPlayer().getGameResult();
				int remain = playerGameResult.getCurrentDiGamePoints().getPoint(INDEX_DI);
				int losePoint = Math.max(-remain, setPoint);								// 本局实际输分： 最多输玩家剩余底分
				totalLose += losePoint;
				
				playerSetResult.getGamePoints().setPoint(INDEX_DI, losePoint);				// 当前局实际输分
				playerGameResult.getFinalGamePoints().changePoint(INDEX_DI, losePoint);		// 计入累计分数
				playerGameResult.getCurrentDiGamePoints().changePoint(INDEX_DI, losePoint);	// 计入当前底分数
			}
		}
		
		for (PlayerSetResult playerSetResult : playerSetResults.values()) {
			int setPoint = playerSetResult.getGamePoints().getPoint(INDEX_DI);
			if(setPoint > 0) {	// 赢家
				PlayerGameResult playerGameResult = playerSetResult.getPlayer().getMjPlayer().getGameResult();
				int winPoint = -totalLose;
				
				playerSetResult.getGamePoints().setPoint(INDEX_DI, winPoint);				// 当前局实际赢分
				playerGameResult.getFinalGamePoints().changePoint(INDEX_DI, winPoint);		// 计入累计分数
				playerGameResult.getCurrentDiGamePoints().changePoint(INDEX_DI, winPoint);	// 计入当前底分数
			}
		}
	}

	/*
	 * 将所有 Calculator Detail 按优先级和分数分组
	 */
	protected List<CommonCalculatorDetailWithPriorityRate> groupCalculatorDetailsByPriorityAndRate() {
		Map<String, CommonCalculatorDetailWithPriorityRate> result = new HashMap<String, CommonCalculatorDetailWithPriorityRate>();
		for (CommonCalculatorDetail calculatorDetail : calcDetails) {
			String key = calculatorDetail.getKey();
			CommonCalculatorDetailWithPriorityRate details = result.get(key);
			if(details == null) {
				details = new CommonCalculatorDetailWithPriorityRate(calculatorDetail.getPriority(), calculatorDetail.getRate());
				result.put(key, details);
			}
			details.addCalculatorDetail(calculatorDetail);
		}
		List<CommonCalculatorDetailWithPriorityRate> list = new ArrayList<CommonCalculatorDetailWithPriorityRate>();
		list.addAll(result.values());
		Collections.sort(list);
		return list;
	}
	
	/*
	 * 按底数玩法时结算
	 */
	protected void calculateDi() {
		// 将所有 Calculator Detail 按优先级和得分分组并排序
		List<CommonCalculatorDetailWithPriorityRate> details = groupCalculatorDetailsByPriorityAndRate();
		
		// 所有玩家剩余分数
		Map<Integer, Integer> remainPoints = new HashMap<Integer, Integer>();
		
		// 所有玩家未结算分数（剩余分数不够时欠下的和未收取的）
		Map<Integer, Integer> duePoints = new HashMap<Integer, Integer>();
		
		for(MahjongPlayer player : room.getAllPlayers()) {
			remainPoints.put(player.getId(), player.getMjPlayer().getGameResult().getCurrentDiGamePoints().getPoint(INDEX_DI));
			duePoints.put(player.getId(), 0);
		}
		
		// 清空 lostDetail 和 gainDetail
/*		gainDetail.clear();
		lostDetail.clear();*/
		
		// 按优先级和得分结算，相同优先级和得分的平分所赢。
		for(CommonCalculatorDetailWithPriorityRate detail : details) {
			detail.calculate(remainPoints, duePoints);
		}
		
	}
	
}
