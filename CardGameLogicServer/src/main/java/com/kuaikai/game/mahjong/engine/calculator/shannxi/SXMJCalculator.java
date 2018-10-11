package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonBetCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

public class SXMJCalculator extends Calculator {

	public SXMJCalculator(MahjongDesk room) {
		super(room);
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		int rate = room.getCreateRoomParam().getSettingInt(GameSetting.BASE_RATE_HU);
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, rate, true, true);		
		
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
		
		huCalculatorDetail.setRate(rate);
		result.add(huCalculatorDetail);
		
		int paoZi = room.getCreateRoomParam().getSettingInt(GameSetting.PAO_ZI);
		if (paoZi != 0) {
			CommonBetCalculatorDetail paoziCalculatorDetail = new CommonBetCalculatorDetail(oper, paoZi, false, true);
			paoziCalculatorDetail.setMainType(JieSuan.PAO_ZI);
			result.add(paoziCalculatorDetail);
		}
		
		this.calcDetails.addAll(result);
		return result;
	}

}
