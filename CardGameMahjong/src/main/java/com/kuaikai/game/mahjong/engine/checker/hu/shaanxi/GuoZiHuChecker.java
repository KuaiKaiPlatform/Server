package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;

/**
 * 打锅子胡牌检查器
 * 
 * @author clc
 */
public class GuoZiHuChecker extends DefaultHuChecker {

	public GuoZiHuChecker(MahjongPlayer player) {
		super(player);
	}
	
	/**
	 * 初始化牌型检查器
	 * 
	 ********************************************
	 * 					十三不靠
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
		// 十三不靠
		SingleChecker shiSanBuKao = null;
		if(desk.getSetting().getBool(CardGameSetting.SHI_SAN_BU_KAO)) {
			shiSanBuKao = new SingleChecker(PaiXin.SHI_SAN_BU_KAO, paiXinChecker);
			paiXinChecker.setChecker(shiSanBuKao);
		}
		
		// 其他牌型
		CheckerArray others = new CheckerArray();
		if(shiSanBuKao != null) {
			shiSanBuKao.setNextChecker(others);
		} else {
			paiXinChecker.setChecker(others);
		}
		
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

	@Override
	public boolean preCheck(BaseOperation oper) {
		// 报听时打出的牌不可被碰，杠，胡
		if(oper.checkOperType(OperType.TING)) return false;
		
		// 报听才能胡
		if(!player.isBaoTing()) return false;
		
		MJCard target = oper.getTarget();
		if(target.isWanTiaoTong() && target.getRemain() <= 2) return false;		// 点数1和2不能胡
		
		// 接炮时，胡牌点数大于5
		if(!player.equals(oper.getPlayer())) {
			if(target.isWanTiaoTong() && target.getRemain() <= 5) return false;
		} 

		return true;
	}
	
	/*
	 * 点数>5方可听牌，未做限制
	 */
/*	@Override
	public Map<Integer, DiscardTingCards> getDiscard2TingCards() {
		Map<Integer, DiscardTingCards> result = super.getDiscard2TingCards();
		if(result == null || result.isEmpty()) return result;
		
		// 移除点数<=5的打听牌
		return result;
	}*/
	
}
