package com.kuaikai.game.mahjong.engine.sender.shaanxi;

import java.util.ArrayList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangMJCard;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperDetail;
import com.kuaikai.game.mahjong.engine.sender.MessageSender;
import com.sy.mahjong.core.RoomExtension;

public class LiangMessageSender extends MessageSender {

	public LiangMessageSender(RoomExtension roomExt) {
		super(roomExt);
	}
	
	/**
	 * 玩家牌桌信息
	 */
	@Override
	protected SFSObject getGameInfo(MahjongPlayer player) {
		SFSObject sfsObject = super.getGameInfo(player);
		sfsObject.putIntArray("liangCards", getLiangCards(player));
		return sfsObject;
	}
	
	/*
	 * 返回手牌中的所有亮牌值
	 */
	protected List<Integer> getLiangCards(MahjongPlayer player) {
		List<Integer> liangCards = new ArrayList<Integer>();
		for(MJCard card : player.getMjPlayer().getCardContainer().getHandCards()) {
			LiangMJCard mjCard = (LiangMJCard)card;
			if(mjCard.isLiang()) liangCards.add(mjCard.getOperValue());
		}
		return liangCards;
	}
	
	/*
	 * OperDetail 中的亮牌牌值
	 */
	@Override
	protected OperDetail getOperDetail(BaseOperation oper, MahjongPlayer receiver) {
		OperDetail od = oper.toOperDetail(receiver);
		
		// 打、听、暗杠、补杠，发送target的亮牌值，碰、点杠、胡不发送亮牌值
		if(oper.getTarget() != null) {
			switch(oper.getOperType()) {
			case OperType.DA :
			case OperType.TING :
			case OperType.AN_GANG :
			case OperType.BU_GANG :
				LiangMJCard target = (LiangMJCard)oper.getTarget();
				od.setTarget(target.getOperValue());
				break;
			}	
		}
		
		List<MJCard> cards = oper.getCards();
		if(cards == null) return od;
		
		// 用于操作的牌发送亮牌值
		od.clearCards();
		for(MJCard card : cards) {
			LiangMJCard mjCard = (LiangMJCard)card;
			od.addCard(mjCard.getOperValue());		
		}	
		return od;
	}
	
}
