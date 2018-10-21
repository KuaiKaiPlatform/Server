package com.kuaikai.game.mahjong.msg;

import java.util.List;

import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo;
import com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit;
import com.kuaikai.game.mahjong.msg.pb.CardGroupPB;
import com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType;

public class DefaultMsgCreator implements MsgCreator {

	/**
	 * 牌局初始信息
	 */
	@Override
	public SSetInit.Builder createSSetInit(MahjongDesk desk, GamePlayer receiver) {
		SSetInit.Builder builder = SSetInit.newBuilder();
		builder.setCurSet(desk.getCurSet()).setRemainCards(desk.getEngine().getCardPool().remainCards()).setBankerId(desk.getBankerId()).setStage(desk.getEngine().getStage());
		for(GamePlayer gp : desk.getAllPlayers()) {
			builder.addPlayerSetInfos(this.createPlayerSetInfo((MahjongPlayer)gp, receiver));
		}
		return builder;
	}
	
	/**
	 * 玩家牌局信息
	 */
	protected PlayerSetInfo.Builder createPlayerSetInfo(MahjongPlayer player, GamePlayer receiver) {
		CardContainer cardContainer = player.getCardContainer();
		PlayerSetInfo.Builder builder = PlayerSetInfo.newBuilder().setUid(player.getId()).setHandCardNum(cardContainer.getHandCards().size())
				.addAllDiscards(cardContainer.getDiscardValues());
		// 手牌
		if(receiver.equals(player)) {
			builder.addAllHandcards(cardContainer.getHandCardValues());
		}
		
		// 明牌
		List<CardGroup> cardGroups = cardContainer.getCardGroups();
		if(cardGroups != null) {
			for (CardGroup group : cardGroups) {
				builder.addCardGroups(this.createCardGroup(group));
			}
		}
		
		return builder;
	}

	/**
	 * 玩家明牌
	 */
	protected CardGroupPB.CardGroup.Builder createCardGroup(CardGroup group) {
		return CardGroupPB.CardGroup.newBuilder().setOperType(OperType.valueOf(group.getOperType())).addAllCards(group.getCardValues()).setTarget(group.getTarget().getValue());
	}
	
}
