package com.kuaikai.game.mahjong.engine.oper;

import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;


public class PengOperation extends BaseOperation {

	public PengOperation(MahjongPlayer player, BaseOperation preOperation) {
		super(player, preOperation);
		this.operType = OperType.PENG;
		this.target = preOperation.getTarget();
	}
	
	@Override
	protected void executeOperation() {
		// 取得打出的牌
		MJCard discard = desk.getEngine().getCardPool().takeLastDiscard();
		if(!target.equals(discard)) {
			logger.error("PengOperation.executeOperation@target error|last-discard=%d|target=%d", discard.getValue(), target.getValue());
			return;
		}
		
		// 移除手牌
		CardContainer cardContainer = player.getCardContainer();
		List<MJCard> pengCards = cardContainer.removeHandCards(target.getValue(), 2);
		
		if(pengCards.size() != 2) {
			logger.error("PengOperation.executeOperation@hand cards removed error|card=%d|hands=%s", target.getValue(), pengCards);
			return;
		}
		
		//pengCards.add(target);
		CardGroup cardGroup = MahjongFactory.createCardGroup(player, OperType.PENG, pengCards, target);
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
	public boolean match(OperDetail od) {
		return matchCommon(od) && target.getValue() == od.getTarget();
	}
	
	@Override
	protected String getDisplayName() {
		return "碰";
	}
	
	public static PengOperation check(MahjongPlayer player, BaseOperation preOper) {
		PengOperation operation = player.getPengChecker().checkPengOperation(preOper);
		if(preOper != null) preOper.addCanExecuteOperation(operation);
		return operation;
	}
	
}
