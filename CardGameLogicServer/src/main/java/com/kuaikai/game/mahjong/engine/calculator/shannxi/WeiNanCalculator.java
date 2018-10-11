package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonBetCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonGangCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

public class WeiNanCalculator extends Calculator {

	public WeiNanCalculator(MahjongDesk room) {
		super(room);
	}

	/*
	 * 荒庄不荒杠，缺门必须打完才算分
	 */
	@Override
	public void onHuangZhuang() {
		for (CommonCalculatorDetail calculatorDetail : calcDetails) {
			// 荒庄设置一下杠失效
			if (calculatorDetail instanceof CommonGangCalculatorDetail) {
				if(((CommonGangCalculatorDetail)calculatorDetail).getSingleWinner().getMjPlayer().hasQueMenHandCard()) {
					calculatorDetail.setInvalid(true);
				}
			}
		}
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		int rate = room.getCreateRoomParam().getSettingInt(GameSetting.BASE_RATE_HU);
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, rate, false, false);
		
		// 清一色4番
		if(oper.containsPaiXin(PaiXin.QING_YI_SE)) {
			huCalculatorDetail.addSubType(PaiXin.QING_YI_SE);
			rate *= 4;
		}

		// 碰碰胡2番
		if(oper.containsPaiXin(PaiXin.PENG_PENG_HU)) {
			huCalculatorDetail.addSubType(PaiXin.DA_DUI_ZI);
			rate *= 2;
		}
		
		// 超豪华七对7番
		if(oper.containsPaiXin(PaiXin.ZUI_HAO_QI_DUI) || oper.containsPaiXin(PaiXin.CHAO_HAO_QI_DUI)) {
			huCalculatorDetail.addSubType(PaiXin.CHAO_HAO_QI_DUI);
			rate *= 7;
		}
		
		// 豪华七对5番
		if(oper.containsPaiXin(PaiXin.HAO_QI_DUI)) {
			huCalculatorDetail.addSubType(PaiXin.HAO_QI_DUI);
			rate *= 5;
		}
		
		// 七对3番
		if(oper.containsPaiXin(PaiXin.QI_DUI)) {
			huCalculatorDetail.addSubType(PaiXin.QI_DUI);
			rate *= 3;
		}		

		// 抢杠胡2番
		if (oper.isQiangGang()) {
			huCalculatorDetail.addSubType(JieSuan.QIANG_GANG_HU);
			rate *= 2;
		}
		
		// 杠上花2番
		if (HuModesChecker.gangShangHua(oper)) {
			huCalculatorDetail.addSubType(JieSuan.GANG_SHANG_HUA);
			rate *= 2;
		}

		// 金钩钓3番
		if(oper.containsPaiXin(PaiXin.QUAN_QIU_REN)) {
			huCalculatorDetail.addSubType(PaiXin.JIN_GOU_DIAO);
			rate *= 3;
		}

		// 天胡7番
		if(HuModesChecker.tianHu(oper, true)) {
			huCalculatorDetail.addSubType(JieSuan.TIAN_HU);
			rate *= 7;
		}

		// 地胡5番
		if(HuModesChecker.diHu(oper)) {
			huCalculatorDetail.addSubType(JieSuan.DI_HU);
			rate *= 5;
		}
		
		// 根胡2番
		if(oper.containsPaiXin(PaiXin.SI_HE)) {
			huCalculatorDetail.addSubType(PaiXin.GEN_HU);
			rate *= 2;
		}
		
		// 海底捞2番
		if (HuModesChecker.haiDiLao(oper)) {
			huCalculatorDetail.addSubType(JieSuan.HAI_DI_LAO);
			rate *= 2;
		}
		
		// 杠后炮2番
		if (HuModesChecker.gangHouPao(oper)) {
			huCalculatorDetail.addSubType(JieSuan.GANG_HOU_PAO);
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
