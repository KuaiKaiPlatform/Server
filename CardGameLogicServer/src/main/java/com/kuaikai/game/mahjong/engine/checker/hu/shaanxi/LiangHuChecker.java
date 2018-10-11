package com.kuaikai.game.mahjong.engine.checker.hu.shaanxi;

import java.util.Map;

import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangCardContainer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangMJCard;
import com.kuaikai.game.mahjong.engine.paixin.PaiXinHelper;
import com.kuaikai.game.mahjong.engine.process.shannxi.LiangProcessor;

/***
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
		if(room.getCreateRoomParam().getSettingBool(GameSetting.SHI_SAN_YAO)) {
			shiSanYao = new SingleChecker(PaiXin.SHI_SAN_YAO, paiXinChecker);
			paiXinChecker.setChecker(shiSanYao);
		}

		// 十三不靠
		SingleChecker shiSanBuKao = null;
		if(room.getCreateRoomParam().getSettingBool(GameSetting.SHI_SAN_BU_KAO)) {
			shiSanBuKao = new SingleChecker(PaiXin.SHI_SAN_BU_KAO, paiXinChecker);
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
		
		// 单钓将
		SingleChecker danDiaoJiang = new SingleChecker(PaiXin.DAN_DIAO_JIANG , paiXinChecker);
		othersQiDui.addSingleChecker(danDiaoJiang);
		
		// 坎张
		SingleChecker kanZhang = new SingleChecker(PaiXin.KAN_ZHANG , paiXinChecker);
		danDiaoJiang.setNextChecker(kanZhang);
		
		// 坎张
		SingleChecker bianZhang = new SingleChecker(PaiXin.BIAN_ZHANG , paiXinChecker);
		kanZhang.setNextChecker(bianZhang);
		
	}

	/*
	 * 只能打出一张亮牌
	 */
	@Override
	public Map<Integer, DiscardTingCards> getDiscard2TingCards() {
		Map<Integer, DiscardTingCards> result = super.getDiscard2TingCards();
		if(result == null || result.isEmpty()) return result;
		
		LiangProcessor processor = (LiangProcessor)room.getEngine().getProcessor();
		if(!processor.hadDiscardLiang(player)) return result;	// 没打过亮牌

		// 如果打听的牌只有一张亮牌，移除
		LiangCardContainer cardContainer = (LiangCardContainer)player.getMjPlayer().getCardContainer();
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(cardContainer.getHandCards(), -1);
		
		for(Integer discard : result.keySet()) {
			if(card2count.get(discard) != 1) continue;
			LiangMJCard card = (LiangMJCard)cardContainer.findHandCard(discard);
			if(card.isLiang()) result.remove(discard);
		}
		
		return result;
	}
	
}
