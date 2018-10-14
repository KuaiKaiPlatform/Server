package com.kuaikai.game.mahjong.engine.process.shannxi;

import java.util.ArrayList;
import java.util.List;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangMJCard;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;

public class LiangProcessor extends DefaultProcessor {

	public final static int TOTAL_LIANG = 6;	// 亮牌的数量
	
	// 一局游戏玩家属性
	public static enum SetAttr {
		LIANG_CARDS,		// 玩家亮牌列表，用于计算点子，打出的亮牌会从列表中移除，碰杠的亮牌保留
		KOU_TING_MO_CARD	// 玩家扣听前摸得那张牌
	}
	
	@Override
	public void onSetStart() {
		super.onSetStart();
		
		// 清除上一局玩家属性
		for(GamePlayer player : desk.getAllPlayers()) {
			for(SetAttr setAttr : SetAttr.values()) {
				player.getSetAttrs().remove(setAttr);
			}	
		}
	}
	
	/**
	 * 亮六飞一规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		AttrsModel setting = desk.getSetting();
		
		// 荒庄时庄家继续坐庄
		setting.put(CardGameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 可听牌，听牌后可补杠和暗杠
		setting.put(CardGameSetting.KE_TING, true);
		setting.put(CardGameSetting.TING_HOU_BU_GANG, true);
		setting.put(CardGameSetting.TING_HOU_AN_GANG, true);
		
		// 漏胡所有
		setting.put(CardGameSetting.LOU_HU_NUM, -1);
		
		// 点炮包三家
		setting.put(CardGameSetting.DIAN_PAO_PAY_ALL, true);
	}
	
	/**
	 * 亮六飞一发牌后将前六张设为亮牌。
	 * 
	 */
	@Override
	public void deal() {
		super.deal();

		for (GamePlayer p : desk.getAllPlayers()) {
			MahjongPlayer player = (MahjongPlayer)p;
			List<MJCard> cards = player.getCardContainer().getHandCards();
			List<LiangMJCard> liangCards = new ArrayList<LiangMJCard>();
			player.getSetAttrs().put(SetAttr.LIANG_CARDS, liangCards);
			for (int i = 0; i < TOTAL_LIANG; i++) {
				LiangMJCard card = (LiangMJCard)cards.get(i);
				card.setLiang(true);	// 设置亮牌标识
				liangCards.add(card);
			}
		}
	}
	
	/*
	 * 听牌和打牌执行完处理
	 */	
	@Override
	public void onOperationDone(BaseOperation oper) {
		switch(oper.getOperType()) {
		case OperType.TING :	// 听牌后将上一步摸到的牌放入KOU_TING_MO_CARD
			BaseOperation pre = oper.getPreOperation();
			if(pre == null || !pre.checkOperType(OperType.MO)) break;
			oper.getPlayer().getSetAttrs().put(SetAttr.KOU_TING_MO_CARD, pre.getTarget());
			break;
		case OperType.DA :		// 打牌后将打出的亮牌从liangCards移除
			LiangMJCard card = (LiangMJCard)oper.getTarget();
			if(!card.isLiang()) break;
			List<LiangMJCard> liangCards = getLiangCards(oper.getPlayer());
			liangCards.remove(card);
			break;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<LiangMJCard> getLiangCards(MahjongPlayer player) {
		return (List<LiangMJCard>)player.getSetAttrs().get(SetAttr.LIANG_CARDS);
	}
	
	public boolean hadDiscardLiang(MahjongPlayer player) {
		List<LiangMJCard> liangCards = getLiangCards(player);
		return liangCards.size() != TOTAL_LIANG;
	}

	public LiangMJCard getKouTingMoCard(MahjongPlayer player) {
		return (LiangMJCard)player.getSetAttrs().get(SetAttr.KOU_TING_MO_CARD);
	}
	
}
