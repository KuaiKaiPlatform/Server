package com.kuaikai.game.mahjong.engine.oper.shannxi;

import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangCardContainer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangMJCard;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.DaOperation;
import com.kuaikai.game.mahjong.engine.oper.OperDetail;
import com.kuaikai.game.mahjong.engine.process.shannxi.LiangProcessor;

/**
 * 亮六飞一打牌
 * 
 */
public class LiangDaOperation extends DaOperation {
	
	public LiangDaOperation(MahjongPlayer player, BaseOperation preOperation) {
		super(player, preOperation);
	}
	
	/*
	 * 检查打牌操作是否匹配
	 */
	@Override
	public boolean match(OperDetail od) {
		if(!matchCommon(od)) return false;
		
		// 听牌后只能打最后一张摸的牌
		if(player.isBaoTing()) {
			MJCard card = player.getCardContainer().findLastHandCard();
			if(card == null || card.getValue() != od.getTarget()) return false;
			this.setTarget(card);
			return true;
		}
		
		// 只能打一张亮牌
		if(od.getTarget() > LiangMJCard.VALUE_INCR) {
			LiangProcessor processor = (LiangProcessor)desk.getEngine().getProcessor();
			if(processor.hadDiscardLiang(player)) {
				logger.warn("LiangDaOperation.check@has diacard liang card|user={}|room={}|target={}",
						player.getId(), desk.getKey(), od.getTarget());
				return false;
			}
		}
		
		LiangCardContainer cardContainer = (LiangCardContainer)player.getCardContainer();
		MJCard card = cardContainer.findHandCardByOperValue(od.getTarget());
		if(card == null) return false;
		
		this.setTarget(card);
		return true;
	}
	
}
