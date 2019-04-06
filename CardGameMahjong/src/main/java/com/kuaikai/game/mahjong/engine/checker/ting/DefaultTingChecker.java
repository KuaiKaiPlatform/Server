package com.kuaikai.game.mahjong.engine.checker.ting;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperationFactory;
import com.kuaikai.game.mahjong.engine.oper.TingOperation;

public class DefaultTingChecker implements TingChecker {

	protected static final Logger logger = LoggerFactory.getLogger("play");
	protected final MahjongDesk desk;
	protected final MahjongPlayer player;
	
	public DefaultTingChecker(MahjongPlayer player) {
		this.player = player;
		this.desk = player.getGameDesk();
	}
	
	/*
	 * 检查并返回指定玩家的听牌操作
	 */	
	@Override
	public TingOperation checkTingOperation(BaseOperation oper) {
		// 先检查该玩家是否具备听牌的条件
		if(!preCheck(oper)) return null;
		
		// 分析打牌后可听的牌
		Map<Integer, DiscardTingCards> discard2TingCards = player.getHuChecker().getDiscard2TingCards();
		if(discard2TingCards == null || discard2TingCards.isEmpty()) return null;
		
		return OperationFactory.createTingOperation(player, oper, discard2TingCards);
	}
	
	protected boolean preCheck(BaseOperation oper) {
		return !player.isBaoTing();
	}
	
}
