package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import java.util.Map;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangCardContainer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangMJCard;
import com.kuaikai.game.mahjong.engine.paixin.PaiXinHelper;
import com.kuaikai.game.mahjong.engine.process.shannxi.LiangProcessor;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

/**
 * 亮六飞一胡牌检查器
 * 
 */
public class LiangHuChecker extends DefaultHuChecker {
	
	public LiangHuChecker(MahjongPlayer player) {
		super(player);
	}

	/**
	 * 初始化牌型检查器
	 * 
	 ********************************************
	 * 					十三幺					*
	 ********************************************
	 * 					十三不靠					*
	 ********************************************
	 *				七对				|			*
	 *******************************|			*
	 *		|			|	单  钓	将	|			*
	 * 		|			|***********|	清一色	*
	 * 标准胡	|	一条龙	|	坎	张	|			*
	 * 	    |	   		|***********|			*
	 * 		|			|	边	张	|			*
	 ******************************************** 
	 *
	 */
	@Override
	protected void initPaiXinChecker() {
		// 十三幺
		SingleChecker shiSanYao = null;
		if(desk.getSetting().getBool(CardGameSetting.SHI_SAN_YAO)) {
			shiSanYao = new SingleChecker(JieSuan.SHI_SAN_YAO_VALUE, paiXinChecker);
			paiXinChecker.setChecker(shiSanYao);
		}

		// 十三不靠
		SingleChecker shiSanBuKao = null;
		if(desk.getSetting().getBool(CardGameSetting.SHI_SAN_BU_KAO)) {
			shiSanBuKao = new SingleChecker(JieSuan.SHI_SAN_BU_KAO_VALUE, paiXinChecker);
			if(shiSanYao != null) {
				shiSanYao.setNextChecker(shiSanBuKao);
			} else {
				paiXinChecker.setChecker(shiSanBuKao);	
			}
		}
		
		// 其他牌型
		CheckerArray others = new CheckerArray();
		if(shiSanBuKao != null) {
			shiSanBuKao.setNextChecker(others);
		} else if(shiSanYao != null) {
			shiSanYao.setNextChecker(others);
		} else {
			paiXinChecker.setChecker(others);
		}
		
		// 七对
		SingleChecker qiDui = new SingleChecker(JieSuan.QI_DUI_VALUE, paiXinChecker);
		others.addSingleChecker(qiDui);

		// 清一色
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

		// 一条龙
		SingleChecker yiTiaoLong = new SingleChecker(JieSuan.YI_TIAO_LONG_VALUE, paiXinChecker);
		yiTiaoLong.addOrDependency(JieSuan.BIAO_ZHUN_HU_VALUE);
		othersQiDui.addSingleChecker(yiTiaoLong);
		
		// 单钓将
		SingleChecker danDiaoJiang = new SingleChecker(JieSuan.DAN_DIAO_JIANG_VALUE, paiXinChecker);
		othersQiDui.addSingleChecker(danDiaoJiang);
		
		// 坎张
		SingleChecker kanZhang = new SingleChecker(JieSuan.KAN_ZHANG_VALUE, paiXinChecker);
		danDiaoJiang.setNextChecker(kanZhang);
		
		// 坎张
		SingleChecker bianZhang = new SingleChecker(JieSuan.BIAN_ZHANG_VALUE, paiXinChecker);
		kanZhang.setNextChecker(bianZhang);
		
	}

	/*
	 * 只能打出一张亮牌
	 */
	@Override
	public Map<Integer, DiscardTingCards> getDiscard2TingCards() {
		Map<Integer, DiscardTingCards> result = super.getDiscard2TingCards();
		if(result == null || result.isEmpty()) return result;
		
		LiangProcessor processor = (LiangProcessor)desk.getEngine().getProcessor();
		if(!processor.hadDiscardLiang(player)) return result;	// 没打过亮牌

		// 如果打听的牌只有一张亮牌，移除
		LiangCardContainer cardContainer = (LiangCardContainer)player.getCardContainer();
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(cardContainer.getHandCards(), -1);
		
		for(Integer discard : result.keySet()) {
			if(card2count.get(discard) != 1) continue;
			LiangMJCard card = (LiangMJCard)cardContainer.findHandCard(discard);
			if(card.isLiang()) result.remove(discard);
		}
		
		return result;
	}
	
}
