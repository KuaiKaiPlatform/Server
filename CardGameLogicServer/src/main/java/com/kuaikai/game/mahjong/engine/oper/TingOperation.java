package com.kuaikai.game.mahjong.engine.oper;

import java.util.Map;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class TingOperation extends BaseOperation {

	protected Map<Integer, DiscardTingCards> discard2TingCards; //打一张牌，可以听哪些牌

	public TingOperation(MahjongPlayer player, BaseOperation preOperation, Map<Integer, DiscardTingCards> discard2TingCards) {
		super(player, preOperation);
		this.discard2TingCards = discard2TingCards;
		this.operType = OperType.TING;
	}

	public void setDiscard2TingCards(Map<Integer, DiscardTingCards> discard2TingCards) {
		this.discard2TingCards = discard2TingCards;
	}
	
	@Override
	protected void createCanExecuteOperations() {
		// 起手报听（庄家摸第一张牌前听牌）后判断所有起手操作是否执行完毕，庄家开始摸牌。
/*		if(!roomIns.getBanker().getBoolAttr(Attr.MO_STARTED)) {
			if(roomIns.isAllActionsDoneAfterDeal())	ActionManager.moCheck(roomIns.getBanker());
			return;
		}
		
		BaseOperation action = ActionManager.daCheck(player);
		if(action != null) {
			action.setCard(getCard());
			action.setAutoRun(true);
		}*/
		
		if(target == null) return;	// 起手报听没有 target
		
		for(MahjongPlayer p : desk.getAllPlayers()) {
			if(p.equals(player)) continue;
			// 点杠
			DianGangOperation.check(p, this);
			// 碰
			PengOperation.check(p, this);
			// 胡
			HuOperation.check(p, this);			
		}
		
		MahjongPlayer nextPlayer = desk.getNextPlayer(player);
		// 吃
		ChiOperation.check(nextPlayer, this);
		
		if (!desk.getEngine().getOperManager().hasCanExecuteOperations()) {
			MoOperation.check(nextPlayer, this);
		}
		
	}

	@Override
	protected void executeOperation() {
		player.getMjPlayer().getCardContainer().discard(target);	// 移出手牌
		desk.getEngine().getCardPool().discard(target);				// 放入牌池
		player.getMjPlayer().setBaoTing(true);
	}

	@Override
	protected void postExecute() {
		player.getMjPlayer().refreshTingCards();	// 记下听的牌
		super.postExecute();
	}
	
	/*
	 * 检查是否匹配玩家发送的操作：找到要打出的手牌，设置target
	 */
	@Override
	public boolean match(OperDetail od) {
		if(!matchCommon(od) || !discard2TingCards.containsKey(od.getTarget())) return false;

		MJCard card = player.getMjPlayer().getCardContainer().findHandCard(od.getTarget());
		if(card == null) return false;
		
		this.setTarget(card);
		return true;
	}
	
	@Override
	protected String getDisplayName() {
		return "听";
	}
	
	public static TingOperation check(MahjongPlayer player, BaseOperation preOper) {
		if(!player.getRoom().getCreateRoomParam().getSettingBool(GameSetting.KE_TING)) return null;
		TingOperation operation = player.getMjPlayer().getTingChecker().checkTingOperation(preOper);
		if(preOper != null) preOper.addCanExecuteOperation(operation);
		return operation;
	}
	
}
