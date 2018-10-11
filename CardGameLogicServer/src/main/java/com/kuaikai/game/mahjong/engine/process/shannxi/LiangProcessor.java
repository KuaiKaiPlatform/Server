package com.kuaikai.game.mahjong.engine.process.shannxi;

import java.util.ArrayList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangMJCard;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;
import com.sy.mahjong.message.room.CreateRoomParam;

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
		for(MahjongPlayer player : room.getAllPlayers()) {
			for(SetAttr setAttr : SetAttr.values()) {
				player.getMjPlayer().removeAttr(setAttr);
			}	
		}
	}
	
	/**
	 * 亮六飞一规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		CreateRoomParam params = room.getCreateRoomParam();
		
		// 荒庄时庄家继续坐庄
		params.changeSetting(GameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 可听牌，听牌后可补杠和暗杠
		params.changeSetting(GameSetting.KE_TING, true);
		params.changeSetting(GameSetting.TING_HOU_BU_GANG, true);
		params.changeSetting(GameSetting.TING_HOU_AN_GANG, true);
		
		// 漏胡所有
		params.changeSetting(GameSetting.LOU_HU_NUM, -1);
		
		// 点炮包三家
		params.changeSetting(GameSetting.DIAN_PAO_PAY_ALL, true);
	}
	
	/**
	 * 亮六飞一发牌后将前六张设为亮牌。
	 * 
	 */
	@Override
	public void deal() {
		super.deal();
		
		List<MahjongPlayer> players = room.getAllPlayers();
		for (MahjongPlayer player : players) {
			List<MJCard> cards = player.getMjPlayer().getCardContainer().getHandCards();
			List<LiangMJCard> liangCards = new ArrayList<LiangMJCard>();
			player.getMjPlayer().putAttr(SetAttr.LIANG_CARDS, liangCards);
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
			oper.getPlayer().getMjPlayer().putAttr(SetAttr.KOU_TING_MO_CARD, pre.getTarget());
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
		return (List<LiangMJCard>)player.getMjPlayer().getAttr(SetAttr.LIANG_CARDS);
	}
	
	public boolean hadDiscardLiang(MahjongPlayer player) {
		List<LiangMJCard> liangCards = getLiangCards(player);
		return liangCards.size() != TOTAL_LIANG;
	}

	public LiangMJCard getKouTingMoCard(MahjongPlayer player) {
		return (LiangMJCard)player.getMjPlayer().getAttr(SetAttr.KOU_TING_MO_CARD);
	}
	
}
