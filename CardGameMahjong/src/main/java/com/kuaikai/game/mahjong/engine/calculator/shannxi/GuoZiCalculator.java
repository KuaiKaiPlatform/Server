package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.calculator.DiCalculator;
import com.kuaikai.game.mahjong.engine.calculator.ScoreDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonGangCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.PlayMode;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

public class GuoZiCalculator extends DiCalculator {

	public GuoZiCalculator(MahjongDesk room) {
		super(room);
	}

	/**
	 * 100点时初始底分为100
	 */
	@Override
	public int getInitDiFen() {
		return desk.getEngine().checkPlayMode(PlayMode.DI)?100:0;
	}
	
	@Override
	public CommonCalculatorDetail createGangDetail(BaseOperation oper) {
		boolean bankerDouble = desk.getSetting().getBool(CardGameSetting.BANKER_DOUBLE);
		int rate = getCardRate(oper.getTarget());

		CommonGangCalculatorDetail gangCalculatorDetail = null;
		switch (oper.getOperType()) {
		case OperType.BU_GANG:		// 补杠
			gangCalculatorDetail = new CommonGangCalculatorDetail(oper, rate, bankerDouble, false);
			break;
		case OperType.AN_GANG:		// 暗杠
			gangCalculatorDetail = new CommonGangCalculatorDetail(oper, rate * 2, bankerDouble, false);
			break;
		case OperType.DIAN_GANG:	// 点杠
			gangCalculatorDetail = new CommonGangCalculatorDetail(oper, rate, bankerDouble, false);
			// 打锅子选项杠随胡的意思是“未听牌的点炮者支付点杠分”
			if (desk.getSetting().getBool(CardGameSetting.DIAN_PAO_PAY_DIAN_GANG)) {
				// 先设置为杠分三家付，胡牌时若点炮者未听牌则包三家
				gangCalculatorDetail.setPayerAll();
			} else {
				// 点杠者已听牌，杠分三家付；未听牌则包三家
				if (oper.getPrePlayer().isBaoTing()) {
					gangCalculatorDetail.setPayerAll();
				} else {
					gangCalculatorDetail.setPayerDianGang();
					gangCalculatorDetail.setPayAll();
				}
			}
			break;
		}
		if (gangCalculatorDetail != null) {
			this.calcDetails.add(gangCalculatorDetail);
		}
		return gangCalculatorDetail;
	}

	private int getCardRate(MJCard card) {
		return card.isZi() ? 10 : card.getRemain();
	}
	
	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		int rate = getCardRate(oper.getTarget());
		boolean bankerDouble = desk.getSetting().getBool(CardGameSetting.BANKER_DOUBLE);
		
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, rate, bankerDouble, true);
		// 未听牌的点炮者包三家
		if (!huCalculatorDetail.isZimo()) {
			if (!oper.getPrePlayer().isBaoTing()) {
				huCalculatorDetail.setPayerDianPao();
				huCalculatorDetail.setPayAll();
				dianPaoPayDianGang(oper.getPrePlayer());
			}
		}
		
		huCalculatorDetail.addSubTypes(oper.getPaiXins());
		result.add(huCalculatorDetail);
		
		this.calcDetails.addAll(result);
		return result;
	}
	
	/*
	 * 若点炮者未听牌，则由点炮者包点杠
	 */
	private void dianPaoPayDianGang(MahjongPlayer dianPlayer) {
		if (!desk.getSetting().getBool(CardGameSetting.DIAN_PAO_PAY_DIAN_GANG)) return;
		
		for(CommonCalculatorDetail cd : calcDetails) {
			if(cd.getMainType() == JieSuan.DIAN_GANG__VALUE) {
				cd.setSingleLoser(dianPlayer);
				cd.setPayAll();
			}
		}
	}
	
	class PayResult {
		int payed = 0;
		int notPayed = 0;
		// 对应的支付细节
		CommonCalculatorDetail calculatorDetail;
	}

	class PayUserResult {
		int useid;
		PayResult payResult;
	}

	@Override
	public void onSetEnd() {
/*		if (!room.getSettingBool(RoomSetting.TOTAL_DI)) { // 够扣 走通用的情况
			super.calcDetail();
			return;
		}
		// 胡的结算优先
		List<HuCalculatorDetail> huCalculatorDetails = new ArrayList<>();
		// 其他的结算方式
		List<CalculatorDetail> calculatorDetails = new LinkedList<>();
		// 胡支付 没有支付完全的列表
		Map<Integer, LinkedHashMap<Integer, PayResult>> huScoreNotEndMap = new HashMap<>();
		// 其他支付方式没支付完全列表
		Map<Integer, List<PayUserResult>> otherScoreNotEndMap = new HashMap<>();
		// 初始化玩家点数
		Map<Integer, Integer> user2Point = new HashMap<>();
		List<MJPlayer> playerList = room.getAllPlayer();
		for (MJPlayer p : playerList) {
			user2Point.put(p.getUid(), p.getScore());
		}

		logger.debug("room [" + room.getRoomId() + "] is calculating ");
		try {
			calDataInit();
			for (CalculatorInputObj inputObj : calculatorInputObjs) {
				for (CalculatorDetail calculatorDetail : calcDetails) {
					if (calculatorDetail instanceof HuCalculatorDetail) {
						for (IBeforeHuCalcHandler beforeHuCalcHandler : beforeHuCalcHanders) {
							beforeHuCalcHandler.beforeHuCalc(this, inputObj, (HuCalculatorDetail) calculatorDetail);
						}
					} else {// 其他的暂时没有需求 先不处理

					}
				}

				// 计算
				for (CalculatorDetail calculatorDetail : calcDetails) {
					if (calculatorDetail.calc(this, inputObj)) {// 计算成功 加入结果
						if (calculatorDetail instanceof HuCalculatorDetail) {
							HuCalculatorDetail huCalculatorDetail = (HuCalculatorDetail) calculatorDetail;
							huCalculatorDetails.add(huCalculatorDetail);
						} else {
							calculatorDetails.add(calculatorDetail);
						}
					}
				}

			}

		} catch (Exception e) {
			logger.error("error:" + room.getRoomId() + ",round:" + room.getCurrRounds(), e);
		}

		// 初始化
		initHuScoreNotEndMap(huCalculatorDetails, huScoreNotEndMap);
		initOtherScoreNotEndMap(calculatorDetails, otherScoreNotEndMap);

		// 先支付胡 直到不能支付
		// 检测
		payHuScore(huScoreNotEndMap, user2Point);

		// 检测
		for (int i = 0; i < 1000; i++) {// TODO 应该写成死循环 重构还是安全第一 先写成这个 没问题在改
			boolean ret = false;
			// 检测
			for (Map.Entry<Integer, Integer> user2PointEntry : user2Point.entrySet()) {
				int useid = user2PointEntry.getKey();
				int point = user2PointEntry.getValue();
				boolean containsUseid = huScoreNotEndMap.containsKey(useid) || otherScoreNotEndMap.containsKey(useid);
				if (point > 0 && containsUseid) {
					ret = true;
					// 支付杠之后可能玩家有钱了 继续先支付胡 后支付杠
					payHuScore(useid, huScoreNotEndMap, user2Point);
					Integer leftPoint = user2Point.get(useid);
					if (leftPoint == null) {
						leftPoint = 0;
					}
					if (leftPoint > 0) {
						int nowLeftPoint = payBackOther(useid, leftPoint, otherScoreNotEndMap, user2Point);
						user2Point.put(useid, nowLeftPoint);
					} else {
						user2Point.put(useid, leftPoint);
					}

				}
			}
			if (!ret) {
				break;
			}
		}

		// 重新计算每一个Detail的值
		for (Map.Entry<Integer, LinkedHashMap<Integer, PayResult>> entry : huScoreNotEndMap.entrySet()) {
			int payedUserid = entry.getKey();
			LinkedHashMap<Integer, PayResult> notPayEnoughMap = entry.getValue();
			for (Map.Entry<Integer, PayResult> notEnoughEntry : notPayEnoughMap.entrySet()) {
				int notEnoughUserid = notEnoughEntry.getKey();
				PayResult payResult = notEnoughEntry.getValue();
				// 修改没有支付的部分
				ScoreChangeDetail notEnoughDetail = payResult.calculatorDetail.getUser2Gain().get(notEnoughUserid);
				notEnoughDetail.setChangeScore(notEnoughDetail.getChangeScore() - payResult.notPayed);
				ScoreChangeDetail notPayedDetail = payResult.calculatorDetail.getUser2Lost().get(payedUserid);
				notPayedDetail.setChangeScore(notPayedDetail.getChangeScore() - payResult.notPayed);
			}
		}

		for (Map.Entry<Integer, List<PayUserResult>> entry : otherScoreNotEndMap.entrySet()) {
			int payedUserid = entry.getKey();
			for (PayUserResult payUserResult : entry.getValue()) {
				int notEnoughUserid = payUserResult.useid;
				// 修改没有支付的部分
				ScoreChangeDetail notEnoughDetail = payUserResult.payResult.calculatorDetail.getUser2Gain()
						.get(notEnoughUserid);
				notEnoughDetail.setChangeScore(notEnoughDetail.getChangeScore() - payUserResult.payResult.notPayed);
				ScoreChangeDetail notPayedDetail = payUserResult.payResult.calculatorDetail.getUser2Lost()
						.get(payedUserid);
				notPayedDetail.setChangeScore(notPayedDetail.getChangeScore() - payUserResult.payResult.notPayed);
			}
		}
		for (HuCalculatorDetail huCalculatorDetail : huCalculatorDetails) {
			addCalculatorDetail(huCalculatorDetail);
		}
		for (CalculatorDetail calculatorDetail : calculatorDetails) {
			addCalculatorDetail(calculatorDetail);
		}*/
		// 计算完成 走通用的计算

	}

	private void payHuScore(int userid, Map<Integer, LinkedHashMap<Integer, PayResult>> huScoreNotEndMap,
			Map<Integer, Integer> user2Point) {
		for (int i = 0; i < 1000; i++) {
			boolean ret = false;
			Integer point = user2Point.get(userid);
			if (point == null) {
				point = 0;
			}
			boolean containsUseid = huScoreNotEndMap.containsKey(userid);
			if (point > 0 && containsUseid) {
				ret = true;
				int leftScore = payHuExe(userid, point, huScoreNotEndMap, user2Point);
				user2Point.put(userid, leftScore);
			}
			if (!ret) {
				break;
			}
		}
	}

	private void payHuScore(Map<Integer, LinkedHashMap<Integer, PayResult>> huScoreNotEndMap,
			Map<Integer, Integer> user2Point) {
		for (int i = 0; i < 1000; i++) {// TODO 应该写成死循环 重构还是安全第一 先写成这个 没问题在改
			boolean ret = false;
			// 检测
			for (Map.Entry<Integer, Integer> user2PointEntry : user2Point.entrySet()) {
				int useid = user2PointEntry.getKey();
				int point = user2PointEntry.getValue();
				boolean containsUseid = huScoreNotEndMap.containsKey(useid);
				if (point > 0 && containsUseid) {
					ret = true;
					int leftScore = payHuExe(useid, point, huScoreNotEndMap, user2Point);
					user2Point.put(useid, leftScore);
				}
			}
			if (!ret) {
				break;
			}
		}
	}

	private void initOtherScoreNotEndMap(List<CommonCalculatorDetail> calculatorDetails,
			Map<Integer, List<PayUserResult>> otherScoreNotEndMap) {
		for (CommonCalculatorDetail calculatorDetail : calculatorDetails) {
			for (Map.Entry<Integer, ScoreDetail> entry : calculatorDetail.getUser2Lost().entrySet()) {
				int userid = entry.getKey();
				ScoreDetail scoreChangeDetail = entry.getValue();
				int needPay = scoreChangeDetail.getScore();
				for (Map.Entry<Integer, ScoreDetail> gainEntry : calculatorDetail.getUser2Gain().entrySet()) {
					int payedUserid = gainEntry.getKey();
					PayResult payResult = new PayResult();
					payResult.calculatorDetail = calculatorDetail;
					// 可能一付多 可能多付一
					int shouldPay = Math.min(needPay, gainEntry.getValue().getScore());
					payResult.notPayed = shouldPay;
					payResult.payed = 0;
					PayUserResult payUserResult = new PayUserResult();
					payUserResult.useid = payedUserid;
					payUserResult.payResult = payResult;
					addToNotPayEndOtherMap(userid, payUserResult, otherScoreNotEndMap);
				}
			}
		}
	}

	private void initHuScoreNotEndMap(List<CommonHuCalculatorDetail> huCalculatorDetails,
			Map<Integer, LinkedHashMap<Integer, PayResult>> huScoreNotEndMap) {
		for (int i = 0; i < huCalculatorDetails.size(); i++) {
			CommonHuCalculatorDetail huCalculatorDetail = huCalculatorDetails.get(i);
			for (Map.Entry<Integer, ScoreDetail> entry : huCalculatorDetail.getUser2Lost().entrySet()) {
				int userid = entry.getKey();
				ScoreDetail scoreChangeDetail = entry.getValue();
				for (Map.Entry<Integer, ScoreDetail> gainEntry : huCalculatorDetail.getUser2Gain().entrySet()) {
					int gainUseid = gainEntry.getKey();
					PayResult payResult = new PayResult();
					payResult.calculatorDetail = huCalculatorDetail;
					payResult.payed = 0;
					payResult.notPayed = scoreChangeDetail.getScore();
					addToNotPayEnoughMap(userid, gainUseid, payResult, huScoreNotEndMap);
					break;
				}
			}

		}

	}

	private int payBackOther(int useid, int leftPoint, Map<Integer, List<PayUserResult>> otherScoreNotEndMap,
			Map<Integer, Integer> user2Point) {
		List<PayUserResult> notPayResults = otherScoreNotEndMap.get(useid);
		if (notPayResults == null) {
			return leftPoint;
		}
		int huNotPaySize = notPayResults.size();
		if (huNotPaySize <= 0) {
			otherScoreNotEndMap.remove(useid);
			return leftPoint;
		}
		Iterator<PayUserResult> iterator = notPayResults.iterator();
		while (iterator.hasNext()) {
			PayUserResult payUserResult = iterator.next();
			PayResult payResult = payUserResult.payResult;
			int payUserid = payUserResult.useid;
			if (leftPoint >= payResult.notPayed) {
				iterator.remove();
				leftPoint -= payResult.notPayed;
				// 增加玩家分数
				Integer oriPoint = user2Point.get(payUserid);
				oriPoint = oriPoint == null ? payResult.notPayed : oriPoint + payResult.notPayed;
				user2Point.put(payUserid, oriPoint);
			} else {
				payResult.notPayed -= leftPoint;
				payResult.payed += leftPoint;
				// 增加玩家分数
				Integer oriPoint = user2Point.get(payUserid);
				oriPoint = oriPoint == null ? leftPoint : oriPoint + leftPoint;
				user2Point.put(payUserid, oriPoint);
				leftPoint = 0;
				break;
			}
		}
		int nowNotPaySize = notPayResults.size();
		if (nowNotPaySize <= 0) {
			otherScoreNotEndMap.remove(useid);
		}
		return leftPoint;
	}

	private int payHuExe(int useid, int point, Map<Integer, LinkedHashMap<Integer, PayResult>> huScoreNotEndMap,
			Map<Integer, Integer> user2Point) {
		LinkedHashMap<Integer, PayResult> notPayMap = huScoreNotEndMap.get(useid);
		if (notPayMap == null) {
			return point;
		}
		int huNotPaySize = notPayMap.size();
		if (huNotPaySize <= 0) {
			huScoreNotEndMap.remove(useid);
			return point;
		}
		int addEveryNum = Math.max(1, point / huNotPaySize);
		Iterator<Map.Entry<Integer, PayResult>> iterator = notPayMap.entrySet().iterator();
		int smallUserid = 0;
		int smallPayed = 0;
		while (iterator.hasNext()) {
			Map.Entry<Integer, PayResult> entry = iterator.next();
			PayResult payResult = entry.getValue();
			int payUserid = entry.getKey();
			if (point < addEveryNum) {
				// 上面max定了 至少是1 因此 当小于的情况发生时 point一定为0
				break;
			}
			if (addEveryNum >= payResult.notPayed) {
				iterator.remove();
				point -= payResult.notPayed;
				// 增加玩家分数
				Integer oriPoint = user2Point.get(payUserid);
				oriPoint = oriPoint == null ? payResult.notPayed : oriPoint + payResult.notPayed;
				user2Point.put(payUserid, oriPoint);
			} else {
				payResult.notPayed -= addEveryNum;
				payResult.payed += addEveryNum;
				point -= addEveryNum;
				// 增加玩家分数
				Integer oriPoint = user2Point.get(payUserid);
				oriPoint = oriPoint == null ? addEveryNum : oriPoint + addEveryNum;
				user2Point.put(payUserid, oriPoint);
				if (smallUserid == 0) {
					smallUserid = payUserid;
					smallPayed = payResult.payed;
				} else {
					if (payResult.payed < smallPayed) {
						smallUserid = payUserid;
						smallPayed = payResult.payed;
					}
				}
			}
		}
		if (point > 0 && notPayMap.containsKey(smallUserid)) {
			PayResult payResult = notPayMap.get(smallUserid);
			if (point >= payResult.notPayed) {
				notPayMap.remove(smallUserid);
				point -= payResult.notPayed;
				// 增加玩家分数
				Integer oriPoint = user2Point.get(smallUserid);
				oriPoint = oriPoint == null ? payResult.notPayed : oriPoint + payResult.notPayed;
				user2Point.put(smallUserid, oriPoint);

			} else {
				payResult.notPayed -= point;
				payResult.payed += point;
				// 增加玩家分数
				Integer oriPoint = user2Point.get(smallUserid);
				oriPoint = oriPoint == null ? point : oriPoint + point;
				user2Point.put(smallUserid, oriPoint);
				point = 0;
			}
		}
		int nowNotPaySize = notPayMap.size();
		if (nowNotPaySize <= 0) {
			huScoreNotEndMap.remove(useid);
		}
		return point;

	}

	private void addToNotPayEndOtherMap(int userid, PayUserResult payUserResult,
			Map<Integer, List<PayUserResult>> otherScoreNotEndMap) {
		List<PayUserResult> payResults = otherScoreNotEndMap.get(userid);
		if (payResults == null) {
			payResults = new LinkedList<>();
			otherScoreNotEndMap.put(userid, payResults);
		}
		payResults.add(payUserResult);
	}

	private void addToNotPayEnoughMap(int userid, int gainUseid, PayResult payResult,
			Map<Integer, LinkedHashMap<Integer, PayResult>> huScoreNotEndMap) {
		LinkedHashMap<Integer, PayResult> payResultMap = huScoreNotEndMap.get(userid);
		if (payResultMap == null) {
			payResultMap = new LinkedHashMap<>();
			huScoreNotEndMap.put(userid, payResultMap);
		}
		payResultMap.put(gainUseid, payResult);
	}

}
