package com.kuaikai.game.mahjong.engine.oper;

import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class AnGangOperation extends BaseOperation {

	public AnGangOperation(MahjongPlayer player, BaseOperation preOperation, MJCard target) {
		super(player, preOperation);
		this.operType = OperType.AN_GANG;
		this.target = target;
	}

	@Override
	protected void executeOperation() {
		// 移除手牌
		CardContainer cardContainer = player.getCardContainer();
		List<MJCard> gangCards = cardContainer.removeHandCards(target.getValue(), 4);
		
		if(gangCards.size() != 4) {
			logger.error("AnGangOperation.executeAction@hand cards removed error|card=%d|gangCards=%s", target.getValue(), gangCards);
			return;
		}

		if(!gangCards.remove(target)) {
			logger.error("AnGangOperation.executeAction@target card removed error|card=%d|gangCards=%s", target.getValue(), gangCards);
			return;			
		}
		
		CardGroup cardGroup = MahjongFactory.createCardGroup(player, OperType.AN_GANG, gangCards, target);
		cardContainer.addCardGroup(cardGroup);
	}
	
	@Override
	protected void createCanExecuteOperations() {
		MoOperation.check(player, this);
	}
	
	@Override
	protected void postExecute() {
		super.postExecute();
		desk.getEngine().getCalculator().createGangDetail(this);
	}
	
	@Override
	public boolean match(OperDetail od) {
		return matchCommon(od) && target.getValue() == od.getTarget();
	}
	
	@Override
	protected String getDisplayName() {
		return "暗杠";
	}
	
	/*
	 * 检查并返回指定玩家可执行的暗杠操作
	 * 
	 */
	public static List<BaseOperation> check(MahjongPlayer player, BaseOperation preOper) {
		List<BaseOperation> operations = player.getGangChecker().checkAnGangOperations(preOper);
		if(preOper != null) preOper.addCanExecuteOperations(operations);
		return operations;
	}
	
}
