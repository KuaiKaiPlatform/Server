package com.kuaikai.game.mahjong.engine.calculator.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.calculator.ScoreDetail;

/*
 * 具有相同优先级和得分的一组结算项
 */
public class CommonCalculatorDetailWithPriorityRate implements Comparable<CommonCalculatorDetailWithPriorityRate> {

	private int priority;
	private int rate;
	private List<CommonCalculatorDetail> calculatorDetails = new ArrayList<CommonCalculatorDetail>();
	
	private Map<Integer, List<ScoreDetail>> gainDetails = new HashMap<>(); 			// 所有CalculatorDetail计算得到的赢家得分明细
	private Map<Integer, List<ScoreDetail>> lostDetails = new HashMap<>();			// 所有CalculatorDetail计算得到的输家失分明细
	
	public CommonCalculatorDetailWithPriorityRate(int priority, int rate) {
		super();
		this.priority = priority;
		this.rate = rate;
	}

	public void addCalculatorDetail(CommonCalculatorDetail detail) {
		calculatorDetails.add(detail);
	}
	
	public boolean checkCalculatorDetail(CommonCalculatorDetail detail) {
		return detail.getPriority() == priority && detail.getRate() == rate;
	}
	
	public List<CommonCalculatorDetail> getCalculatorDetails() {
		return calculatorDetails;
	}
	
	public int getPriority() {
		return priority;
	}

	public int getRate() {
		return rate;
	}

	@Override
	public int compareTo(CommonCalculatorDetailWithPriorityRate o) {
		// 值大的优先
		if(priority != o.getPriority()) return o.getPriority() - priority;
		return o.getRate() - rate;
	}

	public boolean calculate(Map<Integer, Integer> remainPoints, Map<Integer, Integer> duePoints) {
		// 计算有效分数项，计入玩家得失分
		for (CommonCalculatorDetail calculatorDetail : calculatorDetails) {
			if (calculatorDetail.calc()) {
				genGainAndLostDetails(calculatorDetail);
			}
		}
		
		
		return true;
	}
	
	protected void genGainAndLostDetails(CommonCalculatorDetail calculatorDetail) {
		for (Map.Entry<Integer, ScoreDetail> entry : calculatorDetail.getUser2Gain().entrySet()) {
			int userid = entry.getKey();
			List<ScoreDetail> scoreDetails = this.gainDetails.get(userid);
			if (scoreDetails == null) {
				scoreDetails = new ArrayList<>();
				this.gainDetails.put(userid, scoreDetails);
			}
			scoreDetails.add(entry.getValue());
		}

		for (Map.Entry<Integer, ScoreDetail> entry : calculatorDetail.getUser2Lost().entrySet()) {
			int userid = entry.getKey();
			List<ScoreDetail> scoreDetails = this.lostDetails.get(userid);
			if (scoreDetails == null) {
				scoreDetails = new ArrayList<>();
				this.lostDetails.put(userid, scoreDetails);
			}
			scoreDetails.add(entry.getValue());
		}
	}
	
}
