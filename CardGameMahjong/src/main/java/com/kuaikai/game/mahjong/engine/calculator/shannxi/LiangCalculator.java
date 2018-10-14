package com.kuaikai.game.mahjong.engine.calculator.shannxi;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonHuCalculatorDetail;
import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.engine.process.shannxi.LiangProcessor;

public class LiangCalculator extends Calculator {
	
	public LiangCalculator(MahjongDesk room) {
		super(room);
	}

	@Override
	public List<CommonCalculatorDetail> createHuDetails(HuOperation oper) {
		List<CommonCalculatorDetail> result = new LinkedList<CommonCalculatorDetail>();
		
		// 底分及牌型翻倍
		int rate = desk.getSetting().getInt(CardGameSetting.BASE_RATE_HU);
		Set<Integer> paiXins = oper.getPaiXins();
		for(int paiXin : paiXins) {
			switch(paiXin) {
			case PaiXin.SHI_SAN_YAO :
				rate *= 8;
				break;
			case PaiXin.SHI_SAN_BU_KAO :
				rate *= 2;
				break;
			case PaiXin.QI_DUI :
				rate *= 2;
				break;
			case PaiXin.HAO_QI_DUI :
			case PaiXin.CHAO_HAO_QI_DUI :
			case PaiXin.ZUI_HAO_QI_DUI :
				rate *= 4;
				break;
			case PaiXin.QING_YI_SE :
				rate *= 2;
				break;
			case PaiXin.YI_TIAO_LONG :
				rate *= 2;
				break;
			default :
				break;
			}
		}

		// 计算点子，自摸翻倍
		int dianZi = this.caclDianZi(oper) * rate;
		if(oper.isZimo()) dianZi *= 2;
		
		// 点子，只显示在赢家结算明细
		CommonHuCalculatorDetail dianZiCalculatorDetail = new CommonHuCalculatorDetail(oper, dianZi, false, false);
		dianZiCalculatorDetail.setMainType(JieSuan.DIAN_ZI);
		dianZiCalculatorDetail.addSubTypes(paiXins);
		dianZiCalculatorDetail.setLoserDisplay(false);
		dianZiCalculatorDetail.setToPay(false);
		result.add(dianZiCalculatorDetail);
		
		// 四舍五入，如：12点子算10分，16点子算20分
		int perRate = (int) (Math.round(dianZi * 0.1) * 10);
		
		// 实际分数，不显示在结算明细
		CommonHuCalculatorDetail huCalculatorDetail = new CommonHuCalculatorDetail(oper, perRate, false, false);
		huCalculatorDetail.setWinnerDisplay(false);
		huCalculatorDetail.setLoserDisplay(false);
		result.add(huCalculatorDetail);
		
		this.calcDetails.addAll(result);
		return result;
	}

	private int caclDianZi(HuOperation oper) {
		int dianZi = 0;
		
		// 亮牌分数
		LiangProcessor processor = (LiangProcessor)desk.getEngine().getProcessor();
		for (MJCard card : processor.getLiangCards(oper.getPlayer())) {
			dianZi += getCardDianZi(card);
		}
		
		// 扣听前摸的牌算点子
		MJCard card = processor.getKouTingMoCard(oper.getPlayer());
		if(card != null) {
			dianZi += getCardDianZi(card);
		}
		
		// 十三幺，十三不靠，卡边吊：胡的牌算点子
		if(oper.containsPaiXin(PaiXin.SHI_SAN_YAO) || oper.containsPaiXin(PaiXin.SHI_SAN_BU_KAO) || HuModesChecker.kaBianDiao(oper)) {
			dianZi += getCardDianZi(oper.getTarget());
		}
		
		return dianZi;
	}
	
	/*
	 * 返回牌的点子：牌面点数2至8的为1点；牌面点数1和9的为2点；字牌为3点
	 */
	private int getCardDianZi(MJCard card) {
		if(card.isZi()) return 3;
		return card.isYaoJiu()? 2 : 1;
	}

}
