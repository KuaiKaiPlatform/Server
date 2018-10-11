package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonBetCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.model.CardPool;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;


public class OneFiveNineCalculator extends Calculator {

	public OneFiveNineCalculator(MahjongDesk room) {
		super(room);
	}

	private int caclRate(HuOperation oper) {
		int ma159 = room.getCreateRoomParam().getSettingInt(GameSetting.MA_159);
		CardPool cardPool = room.getEngine().getCardPool();
		int score = 2;	// 自摸，抢杠胡都是胡牌2分
		if(ma159 == 1) {	// 一码全中（买1）时：牌面点数是几就算几分，一和红中算10分
			int card = cardPool.takeNextCard();
			int remain = Mahjong.getRemain(card);
			if(room.getEngine().getAlmightyCardNum() == card || remain == 1) {
				score += 10;
			} else {
				score += remain;
			}
		} else if(ma159 > 0) {
			int base = 2; // 一码 2分
			for(int i=0; i<ma159; i++) {
				int card = cardPool.takeNextCard();
				if(room.getEngine().getAlmightyCardNum() == card) {
					score += base;
				} else {
					switch(Mahjong.getRemain(card)) {
					case 1 :
					case 5 :
					case 9 :
						score += base;
						break;
					}
				}
			}
		}
		
		return score;
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		int rate = caclRate(oper);
		CommonHuCalculatorDetail calculatorDetail = new CommonHuCalculatorDetail(oper, rate, false, false);
		calculatorDetail.setMainType(oper.getSinglePaiXin());
		result.add(calculatorDetail);
		
		// 抢杠胡
		if(oper.isQiangGang()) {
			calculatorDetail.addSubType(JieSuan.QIANG_GANG_HU);
		}
		
		// 炮子
		int paoZi = room.getCreateRoomParam().getSettingInt(GameSetting.PAO_ZI);
		if (paoZi != 0) {
			CommonBetCalculatorDetail paoziCalculatorDetail = new CommonBetCalculatorDetail(oper, paoZi, false, false);
			paoziCalculatorDetail.setMainType(JieSuan.PAO_ZI);
			result.add(paoziCalculatorDetail);
		}
		
		this.calcDetails.addAll(result);
		return result;
	}

}
