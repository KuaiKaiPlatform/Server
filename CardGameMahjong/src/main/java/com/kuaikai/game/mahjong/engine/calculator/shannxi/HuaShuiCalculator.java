package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonBetCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

public class HuaShuiCalculator extends Calculator {

	public HuaShuiCalculator(MahjongDesk room) {
		super(room);
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		int rate = desk.getSetting().getInt(CardGameSetting.BASE_RATE_HU);
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, rate, false, true);		
		
		if(oper.containsQiDuiPaiXin()) {
			huCalculatorDetail.addSubType(PaiXin.QI_DUI);
			rate *= 2;
		}
		
		if(oper.containsPaiXin(PaiXin.QING_YI_SE)) {
			huCalculatorDetail.addSubType(PaiXin.QING_YI_SE);
			rate *= 2;
		}
		
		if (HuModesChecker.gangShangHua(oper)) {
			huCalculatorDetail.addSubType(JieSuan.GANG_SHANG_HUA);
			rate *= 2;
		}
		
		if (HuModesChecker.haiDiLao(oper)) {
			huCalculatorDetail.addSubType(JieSuan.HAI_DI_LAO);
			rate *= 2;
		}
		
		if (oper.isQiangGang()) {
			huCalculatorDetail.addSubType(JieSuan.QIANG_GANG_HU);
			rate *= 2;
		}
		
		if (desk.getSetting().getBool(CardGameSetting.HUANG_ZHUANG_DOUBLE) && desk.getEngine().getLastSetResult().isHuangZhuang()) {
			huCalculatorDetail.addSubType(JieSuan.HUANG_ZHUANG_DOUBLE);
			rate *= 2;
		}
		
		huCalculatorDetail.setRate(rate);
		result.add(huCalculatorDetail);
		
		int yu = desk.getSetting().getInt(CardGameSetting.HUA_SHUI_YU);
		if (yu > 0) {
			CommonBetCalculatorDetail yuCalculatorDetail = new CommonBetCalculatorDetail(oper, yu, false, false);
			yuCalculatorDetail.setMainType(JieSuan.HUA_SHUI_YU);
			yuCalculatorDetail.setWinnerBetOnly(true);
			result.add(yuCalculatorDetail);
		}
		
		this.calcDetails.addAll(result);
		return result;
	}

}
