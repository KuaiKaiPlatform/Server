package com.kuaikai.game.mahjong.engine.model.shaanxi;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class LiangCardGroup extends CardGroup {

	public LiangCardGroup(MahjongPlayer player, int operType, List<MJCard> cards, MJCard target) {
		super(player, operType, cards, target);
	}

/*	@Override
	public SFSObject toSFSObject() {
		SFSObject result = super.toSFSObject();
		
		// 自己的牌设为亮牌值
		List<Integer> cardList = new ArrayList<Integer>();
		for(MJCard card : cards) {
			LiangMJCard mjCard = (LiangMJCard)card;
			cardList.add(mjCard.getOperValue());
		}
		result.putIntArray("cards", cardList);
		
		// 自己的牌设为亮牌值
		if(player.equals(target.getPlayer())) {
			LiangMJCard mjCard = (LiangMJCard)target;
			result.putInt("target", mjCard.getOperValue());
		}
		
		return result;
	}*/
	
}
