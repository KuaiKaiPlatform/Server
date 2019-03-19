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
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

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
				if(((CommonGangCalculatorDetail)calculatorDetail).getSingleWinner().hasQueMenHandCard()) {
					calculatorDetail.setInvalid(true);
				}
			}
		}
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		int rate = desk.getSetting().getInt(CardGameSetting.BASE_RATE_HU);
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, rate, false, false);
		
		// 清一色4番
		if(oper.containsPaiXin(JieSuan.QING_YI_SE_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.QING_YI_SE_VALUE);
			rate *= 4;
		}

		// 碰碰胡2番
		if(oper.containsPaiXin(JieSuan.PENG_PENG_HU_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.DA_DUI_ZI_VALUE);
			rate *= 2;
		}
		
		// 超豪华七对7番
		if(oper.containsPaiXin(JieSuan.ZUI_HAO_QI_DUI_VALUE) || oper.containsPaiXin(JieSuan.CHAO_HAO_QI_DUI_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.CHAO_HAO_QI_DUI_VALUE);
			rate *= 7;
		}
		
		// 豪华七对5番
		if(oper.containsPaiXin(JieSuan.HAO_QI_DUI_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.HAO_QI_DUI_VALUE);
			rate *= 5;
		}
		
		// 七对3番
		if(oper.containsPaiXin(JieSuan.QI_DUI_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.QI_DUI_VALUE);
			rate *= 3;
		}		

		// 抢杠胡2番
		if (oper.isQiangGang()) {
			huCalculatorDetail.addSubType(JieSuan.QIANG_GANG_HU_VALUE);
			rate *= 2;
		}
		
		// 杠上花2番
		if (HuModesChecker.gangShangHua(oper)) {
			huCalculatorDetail.addSubType(JieSuan.GANG_SHANG_HUA_VALUE);
			rate *= 2;
		}

		// 金钩钓3番
		if(oper.containsPaiXin(JieSuan.QUAN_QIU_REN_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.JIN_GOU_DIAO_VALUE);
			rate *= 3;
		}

		// 天胡7番
		if(HuModesChecker.tianHu(oper, true)) {
			huCalculatorDetail.addSubType(JieSuan.TIAN_HU_VALUE);
			rate *= 7;
		}

		// 地胡5番
		if(HuModesChecker.diHu(oper)) {
			huCalculatorDetail.addSubType(JieSuan.DI_HU_VALUE);
			rate *= 5;
		}
		
		// 根胡2番
		if(oper.containsPaiXin(JieSuan.SI_HE_VALUE)) {
			huCalculatorDetail.addSubType(JieSuan.GEN_HU_VALUE);
			rate *= 2;
		}
		
		// 海底捞2番
		if (HuModesChecker.haiDiLao(oper)) {
			huCalculatorDetail.addSubType(JieSuan.HAI_DI_LAO_VALUE);
			rate *= 2;
		}
		
		// 杠后炮2番
		if (HuModesChecker.gangHouPao(oper)) {
			huCalculatorDetail.addSubType(JieSuan.GANG_HOU_PAO_VALUE);
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
