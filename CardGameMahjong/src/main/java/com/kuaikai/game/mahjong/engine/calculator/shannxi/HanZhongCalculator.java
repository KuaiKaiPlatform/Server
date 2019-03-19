package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonBetCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonGangCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.SetResult;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

public class HanZhongCalculator extends Calculator {

	public HanZhongCalculator(MahjongDesk room) {
		super(room);
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		int rate = desk.getSetting().getInt(CardGameSetting.BASE_RATE_HU);
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, rate, false, true);
		
		// 牌型和胡牌模式翻番
		boolean jiaFan = desk.getSetting().getBool(CardGameSetting.HAN_ZHONG_JIA_FAN);
		if (jiaFan) {
			if(oper.containsQiDuiPaiXin()) {
				huCalculatorDetail.addSubType(JieSuan.QI_DUI_VALUE);
				rate *= 2;
			}
			
			if(oper.containsPaiXin(JieSuan.QING_YI_SE_VALUE)) {
				huCalculatorDetail.addSubType(JieSuan.QING_YI_SE_VALUE);
				rate *= 3;
			}
			
			if(oper.containsPaiXin(JieSuan.PENG_PENG_HU_VALUE)) {
				huCalculatorDetail.addSubType(JieSuan.PENG_PENG_HU_VALUE);
				rate *= 3;
			}
			
			if (HuModesChecker.gangShangHua(oper)) {
				huCalculatorDetail.addSubType(JieSuan.GANG_SHANG_HUA_VALUE);
				rate *= 2;
			}
			
			if (HuModesChecker.haiDiLao(oper)) {
				huCalculatorDetail.addSubType(JieSuan.HAI_DI_LAO_VALUE);
				rate *= 3;
			}
			
			if (oper.isQiangGang()) {
				huCalculatorDetail.addSubType(JieSuan.QIANG_GANG_HU_VALUE);
				rate *= 2;
			}
			
		}

		huCalculatorDetail.setRate(rate);
		result.add(huCalculatorDetail);
		
		int paoZi = desk.getSetting().getInt(CardGameSetting.PAO_ZI);
		if (paoZi != 0) {
			CommonBetCalculatorDetail paoziCalculatorDetail = new CommonBetCalculatorDetail(oper, paoZi, false, true);
			paoziCalculatorDetail.setMainType(JieSuan.PAO_ZI_VALUE);
			result.add(paoziCalculatorDetail);
		}
		
		this.calcDetails.addAll(result);
		return result;
	}
	
	@Override
	public void postMultipleHu() {
		// 不加番时，胡牌的杠有效
		if (desk.getSetting().getBool(CardGameSetting.HAN_ZHONG_JIA_FAN)) return;
		
		SetResult setResult = desk.getEngine().getCurrentSetResult();
		for (CommonCalculatorDetail calculatorDetail : calcDetails) {
			// 一炮多响时，胡牌的杠有效，杠随胡走，点炮的支付一倍杠分
			if (calculatorDetail instanceof CommonGangCalculatorDetail) {
				CommonGangCalculatorDetail gangCalculatorDetail = (CommonGangCalculatorDetail) calculatorDetail;
				if(setResult.checkHuPlayer(gangCalculatorDetail.getSingleWinner())) {
					gangCalculatorDetail.setInvalid(false);
					gangCalculatorDetail.setSingleLoser(setResult.getDianPlayer());
					gangCalculatorDetail.setPaySelf();
				}
			}
		}
	}
	
}
