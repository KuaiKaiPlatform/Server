package com.kuaikai.game.mahjong.engine.model.shaanxi;

import java.util.ArrayList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class LiangCardContainer extends CardContainer {
	
	public LiangCardContainer(MahjongPlayer player) {
		super(player);
	}

	/*
	 * 返回玩家手牌：亮牌牌值为原牌值加100
	 */
	@Override
	public List<Integer> getHandCardValues() {
		List<Integer> result = new ArrayList<Integer>();
		for(MJCard card : handCards) {
			LiangMJCard mjCard = (LiangMJCard)card;
			result.add(mjCard.getOperValue());
		}
		return result;
	}

	/*
	 * 返回玩家打出的牌：亮牌牌值为原牌值加100
	 */
	@Override
	public List<Integer> getDiscardValues() {
		List<Integer> result = new ArrayList<Integer>();
		for(MJCard card : discards) {
			LiangMJCard mjCard = (LiangMJCard)card;
			result.add(mjCard.getOperValue());
		}
		return result;
	}
	
	/*
	 * 返回玩家指定牌值的手牌：亮牌牌值为原牌值加100
	 */
	public MJCard findHandCardByOperValue(int operValue) {
		for(MJCard card : handCards) {
			LiangMJCard mjCard = (LiangMJCard)card;
			if(mjCard.getOperValue() == operValue) return mjCard;
		}
		return null;
	}
	
}
