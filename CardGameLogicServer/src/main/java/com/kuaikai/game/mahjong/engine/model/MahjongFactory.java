package com.kuaikai.game.mahjong.engine.model;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangCardContainer;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangCardGroup;
import com.kuaikai.game.mahjong.engine.model.shaanxi.LiangMJCard;

public class MahjongFactory {
	
	public static MJCard createMJCard(int value, MahjongPlayer player) {
		switch(player.getDesk().getRule()) {
		case LIANG :
			return new LiangMJCard(value, player);
		default :
			return new MJCard(value, player);
		}
	}

	public static CardContainer createCardContainer(MahjongPlayer player) {
		switch(player.getDesk().getRule()) {
		case LIANG :
			return new LiangCardContainer(player);
		default :
			return new CardContainer(player);
		}
	}

	public static CardGroup createCardGroup(MahjongPlayer player, int operType, List<MJCard> cards, MJCard target) {
		switch(player.getDesk().getRule()) {
		case LIANG :
			return new LiangCardGroup(player, operType, cards, target);
		default :
			return new CardGroup(player, operType, cards, target);
		}
	}
	
}
