package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

/***
 * 汉中麻将胡牌检查器
 * 
 */
public class HanZhongHuChecker extends DefaultHuChecker {

	public HanZhongHuChecker(MahjongPlayer player) {
		super(player);
	}
	
	/**
	 * 初始化牌型检查器
	 * 
	 ********************************************
	 *				七对				|	
	 *******************************|
	 *		|						|	
	 * 		|						|	清一色
	 * 标准胡	|		碰碰胡			|
	 * 	    |	   					|
	 * 		|						|
	 ******************************************** 
	 *
	 */
	protected void initPaiXinChecker() {
		// 其他牌型
		CheckerArray others = new CheckerArray();
		paiXinChecker.setChecker(others);
		
		// 七对
		SingleChecker qiDui = new SingleChecker(PaiXin.QI_DUI, paiXinChecker);
		others.addSingleChecker(qiDui);

		// 清一色
		SingleChecker qingYiSe = new SingleChecker(PaiXin.QING_YI_SE, paiXinChecker);
		others.addSingleChecker(qingYiSe);
		
		// 其他牌型（七对）
		CheckerArray othersQiDui = new CheckerArray();
		qiDui.setNextChecker(othersQiDui);
		
		// 标准胡
		SingleChecker biaoZhunHu = new SingleChecker(PaiXin.BIAO_ZHUN_HU, paiXinChecker);
		othersQiDui.addSingleChecker(biaoZhunHu);

		// 碰碰胡
		SingleChecker pengPengHu = new SingleChecker(PaiXin.PENG_PENG_HU, paiXinChecker);
		othersQiDui.addSingleChecker(pengPengHu);
		
	}

}
