package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonBetCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

public class SXMJCalculator extends Calculator {

	public SXMJCalculator(MahjongDesk room) {
		super(room);
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		int rate = desk.getSetting().getInt(CardGameSetting.BASE_RATE_HU);
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, rate, true, true);		
		
		if(!desk.getSetting().getBool(CardGameSetting.NO_JIA_FAN_QI_DUI) && oper.containsQiDuiPaiXin()) {
			huCalculatorDetail.addSubType(JieSuan.QI_DUI_VALUE);
			rate *= 2;
		}
		
		if(!desk.getSetting().getBool(CardGameSetting.NO_JIA_FAN_QING_YI_SE) && oper.containsPaiXin(JieSuan.QING_YI_SE_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.QING_YI_SE_VALUE);
			rate *= 2;
		}
		
		if (!desk.getSetting().getBool(CardGameSetting.NO_JIA_FAN_GANG_SHANG_HUA) && HuModesChecker.gangShangHua(oper)) {
			huCalculatorDetail.addSubType(JieSuan.GANG_SHANG_HUA_VALUE);
			rate *= 2;
		}
		
		if (!desk.getSetting().getBool(CardGameSetting.NO_JIA_FAN_HAI_DI_LAO) && HuModesChecker.haiDiLao(oper)) {
			huCalculatorDetail.addSubType(JieSuan.HAI_DI_LAO_VALUE);
			rate *= 2;
		}
		
		if (!desk.getSetting().getBool(CardGameSetting.NO_JIA_FAN_QIANG_GANG_HU) && oper.isQiangGang()) {
			huCalculatorDetail.addSubType(JieSuan.QIANG_GANG_HU_VALUE);
			rate *= 2;
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

}
