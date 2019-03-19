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
			huCalculatorDetail.addSubType(JieSuan.QI_DUI_VALUE);
			rate *= 2;
		}
		
		if(oper.containsPaiXin(JieSuan.QING_YI_SE_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.QING_YI_SE_VALUE);
			rate *= 2;
		}
		
		if (HuModesChecker.gangShangHua(oper)) {
			huCalculatorDetail.addSubType(JieSuan.GANG_SHANG_HUA_VALUE);
			rate *= 2;
		}
		
		if (HuModesChecker.haiDiLao(oper)) {
			huCalculatorDetail.addSubType(JieSuan.HAI_DI_LAO_VALUE);
			rate *= 2;
		}
		
		if (oper.isQiangGang()) {
			huCalculatorDetail.addSubType(JieSuan.QIANG_GANG_HU_VALUE);
			rate *= 2;
		}
		
		if (desk.getSetting().getBool(CardGameSetting.HUANG_ZHUANG_DOUBLE) && desk.getEngine().getLastSetResult().isHuangZhuang()) {
			huCalculatorDetail.addSubType(JieSuan.HUANG_ZHUANG_DOUBLE_VALUE);
			rate *= 2;
		}
		
		huCalculatorDetail.setRate(rate);
		result.add(huCalculatorDetail);
		
		int yu = desk.getSetting().getInt(CardGameSetting.HUA_SHUI_YU);
		if (yu > 0) {
			CommonBetCalculatorDetail yuCalculatorDetail = new CommonBetCalculatorDetail(oper, yu, false, false);
			yuCalculatorDetail.setMainType(JieSuan.HUA_SHUI_YU_VALUE);
			yuCalculatorDetail.setWinnerBetOnly(true);
			result.add(yuCalculatorDetail);
		}
		
		this.calcDetails.addAll(result);
		return result;
	}

}
