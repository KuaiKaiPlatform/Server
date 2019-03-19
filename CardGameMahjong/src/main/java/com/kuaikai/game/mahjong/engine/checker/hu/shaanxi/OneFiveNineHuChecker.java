package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

/***
 * 159麻将胡牌检查器
 * 
 */
public class OneFiveNineHuChecker extends DefaultHuChecker {
	
	public OneFiveNineHuChecker(MahjongPlayer player) {
		super(player);
	}

	/**
	 * 初始化牌型检查器
	 * 
	 * 
	 ********************************************
	 * 					四红中
	 ********************************************
	 *					七对
	 ******************************************** 
	 *					标准胡
	 ******************************************** 
	 *
	 *
	 */
	protected void initPaiXinChecker() {
		SingleChecker root = null;
		SingleChecker current = null;
		if(desk.getSetting().getBool(CardGameSetting.HONG_ZHONG_ALMIGHTY)) {
			 SingleChecker almight4 = new SingleChecker(JieSuan.ALMIGHTY_4_VALUE, paiXinChecker);
			 root = almight4;
			 current = almight4;
		}
		
		if(!desk.getSetting().getBool(CardGameSetting.NO_QI_DUI)) {
			SingleChecker qiDui = new SingleChecker(JieSuan.QI_DUI_VALUE, paiXinChecker);
			if(root == null) {
				root = qiDui;
			} else {
				root.setNextChecker(qiDui);
			}
			current = qiDui;
		}

		SingleChecker biaoZhunHu = new SingleChecker(JieSuan.BIAO_ZHUN_HU_VALUE, paiXinChecker);
		if(root == null) {
			root = biaoZhunHu;
		} 
		
		if(current != null) {
			current.setNextChecker(biaoZhunHu);
		}

		paiXinChecker.setChecker(root);
	}
	
}
