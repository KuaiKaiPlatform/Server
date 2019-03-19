package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

/***
 * 渭南麻将胡牌检查器
 * 
 */
public class WeiNanHuChecker extends DefaultHuChecker {
	
	public WeiNanHuChecker(MahjongPlayer player) {
		super(player);
	}

	/**
	 * 初始化牌型检查器
	 * 
	 ********************************************
	 *				七对				|	
	 *******************************|
	 *		|		金钩钓			|
	 * 		|***********************|	清一色
	 * 标准胡	|		碰碰胡			|
	 * 		|***********************|
	 * 	    |	   	根胡				|
	 * 		|						|
	 ******************************************** 
	 *
	 */
	protected void initPaiXinChecker() {		
		// 其他牌型
		CheckerArray others = new CheckerArray();
		paiXinChecker.setChecker(others);
		
		// 七对
		SingleChecker qiDui = new SingleChecker(JieSuan.QI_DUI_VALUE, paiXinChecker);
		others.addSingleChecker(qiDui);

		// 清一色，依赖于标准胡或七对牌型
		SingleChecker qingYiSe = new SingleChecker(JieSuan.QING_YI_SE_VALUE, paiXinChecker);
		qingYiSe.addOrDependency(JieSuan.BIAO_ZHUN_HU_VALUE);
		qingYiSe.addOrDependency(JieSuan.QI_DUI_VALUE);
		others.addSingleChecker(qingYiSe);
		
		// 其他牌型（七对）
		CheckerArray othersQiDui = new CheckerArray();
		qiDui.setNextChecker(othersQiDui);
		
		// 标准胡
		SingleChecker biaoZhunHu = new SingleChecker(JieSuan.BIAO_ZHUN_HU_VALUE, paiXinChecker);
		othersQiDui.addSingleChecker(biaoZhunHu);

		// 金钩钓
		SingleChecker jinGouDiao = new SingleChecker(JieSuan.QUAN_QIU_REN_VALUE, paiXinChecker);
		jinGouDiao.addOrDependency(JieSuan.BIAO_ZHUN_HU_VALUE);
		othersQiDui.addSingleChecker(jinGouDiao);
		
		// 碰碰胡
		SingleChecker pengPengHu = new SingleChecker(JieSuan.PENG_PENG_HU_VALUE, paiXinChecker);
		jinGouDiao.setNextChecker(pengPengHu);

		// 根胡
		SingleChecker genHu = new SingleChecker(JieSuan.SI_HE_VALUE, paiXinChecker);
		genHu.addOrDependency(JieSuan.BIAO_ZHUN_HU_VALUE);
		pengPengHu.setNextChecker(genHu);
		
	}
	
	@Override
	public boolean preCheck(BaseOperation oper) {
		// 是否缺一门
		Mahjong.CardType queMen = player.getQueMen();
		if(queMen == null) return true;

		if(queMen.equals(oper.getTarget().getCardType())) return false; 
		
		for(MJCard card : player.getCardContainer().getHandCards()) {
			// 带缺门的牌不能胡
			if(queMen.equals(card.getCardType())) return false;
		}
		
		return true;
	}

	@Override
	public boolean postCheck(BaseOperation act, HuOperation huOper) {
		if(!desk.getSetting().getBool(CardGameSetting.DING_QUE)) return true;	// 不带缺一门，无限制番数
		if(huOper.getPaiXins().size() > 1 || !huOper.containsPaiXin(JieSuan.BIAO_ZHUN_HU_VALUE)) return true;	// 不是普通平胡，有其他牌型，可胡
		
		// 检查胡牌番数，点炮2番起胡
		if(huOper.isZimo()) return true;						// 自摸无限制番数
		if(huOper.isQiangGang()) return true;					// 抢杠胡2番可胡
		if(HuModesChecker.diHu(huOper)) return true;			// 地胡5番可胡
		if(HuModesChecker.gangHouPao(huOper)) return true;		// 杠后炮2番可胡
		
		return false;
	}
	
}
