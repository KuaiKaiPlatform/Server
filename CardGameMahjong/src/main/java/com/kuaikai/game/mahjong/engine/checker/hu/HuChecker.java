package com.kuaikai.game.mahjong.engine.checker.hu;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

/***
 * 胡牌检查器
 * 
 */
public interface HuChecker {

	/*
	 * 检查并返回指定玩家的胡牌操作
	 */	
	public HuOperation checkHuOperation(BaseOperation oper);
	
	/*
	 * 检查并返回指定玩家的打牌后的听牌列表
	 */
	public Map<Integer, DiscardTingCards> getDiscard2TingCards();
	
	/*
	 * 检查并返回指定玩家的听牌列表
	 */	
	public List<Integer> getTingCards();
	
	/*
	 * 检查指定玩家指定手牌是否听牌
	 */		
	public boolean isTing(List<MJCard> handCards);
	
	/*
	 * 检查是否漏胡：true 可胡；false 不可胡
	 */	
	public boolean louHuCheck(BaseOperation act);
	
	/*
	 * 指定Action（摸，杠，打等），返回可胡牌型
	 */
	public Set<Integer> checkPaiXins(BaseOperation act);
	
	/*
	 * 指定手牌，检查可胡牌型
	 */
	public Set<Integer> checkPaiXins(List<MJCard> handCards, MJCard card);

}
