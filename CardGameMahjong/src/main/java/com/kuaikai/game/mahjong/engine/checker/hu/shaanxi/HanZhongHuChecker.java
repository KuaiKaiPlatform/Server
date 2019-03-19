package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

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
		SingleChecker qiDui = new SingleChecker(JieSuan.QI_DUI_VALUE, paiXinChecker);
		others.addSingleChecker(qiDui);

		// 清一色
		SingleChecker qingYiSe = new SingleChecker(JieSuan.QING_YI_SE_VALUE, paiXinChecker);
		others.addSingleChecker(qingYiSe);
		
		// 其他牌型（七对）
		CheckerArray othersQiDui = new CheckerArray();
		qiDui.setNextChecker(othersQiDui);
		
		// 标准胡
		SingleChecker biaoZhunHu = new SingleChecker(JieSuan.BIAO_ZHUN_HU_VALUE, paiXinChecker);
		othersQiDui.addSingleChecker(biaoZhunHu);

		// 碰碰胡
		SingleChecker pengPengHu = new SingleChecker(JieSuan.PENG_PENG_HU_VALUE, paiXinChecker);
		othersQiDui.addSingleChecker(pengPengHu);
		
	}

}
