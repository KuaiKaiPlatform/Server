package com.kuaikai.game.mahjong.engine.oper.shannxi;

import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangCardContainer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperDetail;
import com.kuaikai.game.mahjong.engine.oper.TingOperation;

/**
 * 亮六飞一听牌
 * 
 */
public class LiangTingOperation extends TingOperation {
	
	public LiangTingOperation(MahjongPlayer player, BaseOperation preOperation, Map<Integer, DiscardTingCards> discard2TingCards) {
		super(player, preOperation, discard2TingCards);
	}
	
	/*
	 * 检查听牌操作是否匹配
	 */
	@Override
	public boolean match(OperDetail od) {
		if(!matchCommon(od) || !discard2TingCards.containsKey(od.getTarget())) return false;
		
		LiangCardContainer cardContainer = (LiangCardContainer)player.getMjPlayer().getCardContainer();
		MJCard card = cardContainer.findHandCardByOperValue(od.getTarget());
		if(card == null) return false;
		
		this.setTarget(card);
		return true;
	}
	
}
