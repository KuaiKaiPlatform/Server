package com.kuaikai.game.mahjong.engine.oper;

import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;

public class ChiOperation extends BaseOperation {
	
	public ChiOperation(MahjongPlayer player, BaseOperation preOperation, List<MJCard> cards) {
		super(player, preOperation);
		this.operType = OperType.CHI;
		this.target = preOperation.getTarget();
		this.cards = cards;
	}

	@Override
	protected void executeOperation() {
		// 取得打出的牌
		MJCard discard = desk.getEngine().getCardPool().takeLastDiscard();
		if(!target.equals(discard)) {
			logger.error("ChiOperation.executeOperation@target error|last-discard=%d|target=%d", discard.getValue(), target.getValue());
			return;
		}
		
		// 移除手牌
		CardContainer cardContainer = player.getCardContainer();
		List<MJCard> hands = cardContainer.getHandCards();
		if(!hands.removeAll(cards)) {
			logger.error("ChiOperation.executeOperation@hand cards removed error|cards=%d|hands=%s", cards, hands);
			return;			
		}
		
		// 组成顺子
		//List<MJCard> list = new ArrayList<MJCard>();
		//list.add(target);
		//list.addAll(cards);
		
		CardGroup cardGroup = MahjongFactory.createCardGroup(player, OperType.CHI, cards, target);
		cardContainer.addCardGroup(cardGroup);
	}
	
	@Override
	protected void createCanExecuteOperations() {
		TingOperation.check(player, this);
		
		if(!desk.getEngine().getOperManager().hasCanExecuteOperations()) {
			AnGangOperation.check(player, this);
			BuGangOperation.check(player, this);
		}
		
		if(!desk.getEngine().getOperManager().hasCanExecuteOperations()) {
			DaOperation.check(player, this);
		}
	}
	
	@Override
	protected void postExecute() {
		super.postExecute();
		player.getSetAttrs().remove(MahjongPlayer.SetAttr.LOU_HU_CARDS);
		player.getSetAttrs().remove(MahjongPlayer.SetAttr.LOU_PENG_CARDS);
	}
	
	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		msg.append("(");
		msg.append(player.getId()).append(",");
		msg.append(getDisplayName()).append(",");
		msg.append(cards).append(",");
		msg.append(target);
		msg.append("),");
		return msg.toString();
	}

	@Override
	public boolean match(OperDetail od) {
		if(target.getValue() != od.getTarget()) return false;
		if(od.getCards() == null) return false;
		if(cards.size() != od.getCards().size()) return false;
		for(MJCard card : cards) {
			if(!od.getCards().contains(card.getValue())) return false;
		}
		
		return matchCommon(od);
	}
	
	@Override
	protected String getDisplayName() {
		return "吃";
	}
	
	public static List<BaseOperation> check(MahjongPlayer player, BaseOperation preOper) {
		if(!player.getGameDesk().getSetting().getBool(GameSetting.KE_CHI)) return null;
		List<BaseOperation> operations = player.getChiChecker().checkChiOperations(preOper);
		if(preOper != null) preOper.addCanExecuteOperations(operations);
		return operations;
	}
	
}
