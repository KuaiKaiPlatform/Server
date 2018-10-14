package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

/***
 * 划水麻将胡牌检查器
 * 
 */
public class HuaShuiHuChecker extends DefaultHuChecker {
	
	public HuaShuiHuChecker(MahjongPlayer player) {
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
	 * 标准胡	|		一条龙			|
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
		qingYiSe.addOrDependency(PaiXin.BIAO_ZHUN_HU);
		qingYiSe.addOrDependency(PaiXin.QI_DUI);
		others.addSingleChecker(qingYiSe);
		
		// 其他牌型（七对）
		CheckerArray othersQiDui = new CheckerArray();
		qiDui.setNextChecker(othersQiDui);
		
		// 标准胡
		SingleChecker biaoZhunHu = new SingleChecker(PaiXin.BIAO_ZHUN_HU, paiXinChecker);
		othersQiDui.addSingleChecker(biaoZhunHu);

		// 一条龙
		SingleChecker yiTiaoLong = new SingleChecker(PaiXin.YI_TIAO_LONG, paiXinChecker);
		yiTiaoLong.addOrDependency(PaiXin.BIAO_ZHUN_HU);
		othersQiDui.addSingleChecker(yiTiaoLong);
	}

}
