package com.kuaikai.game.mahjong.engine.oper;

import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;

public class DianGangOperation extends BaseOperation {

	public DianGangOperation(MahjongPlayer player, BaseOperation preOperation) {
		super(player, preOperation);
		this.operType = OperType.DIAN_GANG;
		this.target = preOperation.getTarget();
	}

	@Override
	protected void executeOperation() {
		// 取得打出的牌
		MJCard discard = desk.getEngine().getCardPool().takeLastDiscard();
		if(!target.equals(discard)) {
			logger.error("DianGangOperation.executeOperation@target error|last-discard=%d|target=%d", discard.getValue(), target.getValue());
			return;
		}
		
		// 移除手牌
		CardContainer cardContainer = player.getCardContainer();
		List<MJCard> gangCards = cardContainer.removeHandCards(target.getValue(), 3);
		
		if(gangCards.size() != 3) {
			logger.error("DianGangOperation.executeOperation@hand cards removed error|card=%d|hands=%s", target.getValue(), gangCards);
			return;
		}
		
		//gangCards.add(target);
		CardGroup cardGroup = MahjongFactory.createCardGroup(player, OperType.DIAN_GANG, gangCards, target);
		cardContainer.addCardGroup(cardGroup);
	}

	@Override
	protected void createCanExecuteOperations() {
		MoOperation.check(player, this);
	}

	@Override
	protected void postExecute() {
		super.postExecute();
		
		player.getSetAttrs().remove(MahjongPlayer.SetAttr.LOU_HU_CARDS);
		player.getSetAttrs().remove(MahjongPlayer.SetAttr.LOU_PENG_CARDS);
		
		desk.getEngine().getCalculator().createGangDetail(this);
	}
	
	@Override
	public boolean match(OperDetail od) {
		return matchCommon(od) && target.getValue() == od.getTarget();
	}
	
	@Override
	protected String getDisplayName() {
		return "点杠";
	}
	
	/*
	 * 检查并返回指定玩家可执行的点杠操作
	 * 
	 */
	public static DianGangOperation check(MahjongPlayer player, BaseOperation preOper) {
		DianGangOperation operation = player.getGangChecker().checkDianGangOperation(preOper);
		if(preOper != null) preOper.addCanExecuteOperation(operation);
		return operation;
	}
	
}
