package com.kuaikai.game.mahjong.engine.oper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.calculator.common.CommonCalculatorDetail;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public abstract class BaseOperation implements Comparable<BaseOperation> {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	protected static final int STATUS_CAN_EXECUTE		= 0;			// 可执行
	protected static final int STATUS_EXECUTED			= 1;			// 已执行
	protected static final int STATUS_PASSED			= 2;			// 已过
	
	protected MahjongDesk desk;
	protected MahjongPlayer player;
	protected BaseOperation preOperation;				// 前置操作
	protected List<BaseOperation> canExecuteOperations;	// 可执行操作
	
	protected int priority;
	protected int status;
	protected boolean autoRun = false;
	
	protected int operType;				// 操作类型（摸、打、吃、碰、杠、听、胡等）
	protected List<MJCard> cards;		// 用于操作的牌，如：二万和四万，用于吃三万
	protected MJCard target;			// 目标牌，如被打的八条、被吃的三万等
	protected int remainCards;			// 操作执行后的剩余牌数
	
	protected CommonCalculatorDetail calculatorDetail;
	
	public BaseOperation(MahjongPlayer player, BaseOperation preOperation) {
		this.player = player;
		this.desk = player.getGameDesk();
		this.preOperation = preOperation;
	}
	
	public MahjongPlayer getPlayer() {
		return player;
	}
	
	/*
	 * 前置操作的玩家：吃、碰、杠、胡的目标玩家
	 */
	public MahjongPlayer getPrePlayer() {
		return preOperation == null?null:preOperation.getPlayer();
	}

	public MahjongDesk getDesk() {
		return desk;
	}

	public void setTarget(MJCard target) {
		this.target = target;
	}

	public MJCard getTarget() {
		return target;
	}

	public List<MJCard> getCards() {
		return cards;
	}

	public BaseOperation getPreOperation() {
		return preOperation;
	}

	public List<BaseOperation> getCanExecuteOperations() {
		if(canExecuteOperations == null) return Collections.emptyList();
		Collections.sort(canExecuteOperations);
		return canExecuteOperations;
	}

	public void addCanExecuteOperation(BaseOperation oper) {
		if(oper == null) return;
		if(canExecuteOperations == null) canExecuteOperations = new LinkedList<BaseOperation>();
		canExecuteOperations.add(oper);
	}

	public void addCanExecuteOperations(Collection<BaseOperation> opers) {
		if(opers == null) return;
		if(canExecuteOperations == null) canExecuteOperations = new LinkedList<BaseOperation>();
		canExecuteOperations.addAll(opers);
	}
	
	public boolean hasCanExecuteOperations() {
		return canExecuteOperations != null && !canExecuteOperations.isEmpty();
	}
	
	public boolean checkOperType(int operType) {
		return this.operType == operType;
	}

	public int getOperType() {
		return operType;
	}
	
	public boolean isAutoRun() {
		return autoRun;
	}

	public CommonCalculatorDetail getCalculatorDetail() {
		return calculatorDetail;
	}

	public void setCalculatorDetail(CommonCalculatorDetail calculatorDetail) {
		this.calculatorDetail = calculatorDetail;
	}

	/*
	 * 检查是否匹配玩家发送的操作：默认只检查player和operType，碰、杠、听额外检查target，吃额外检查target和cards
	 */
	public boolean match(OperDetail od) {
		return matchCommon(od);
	}

	protected boolean matchCommon(OperDetail od) {
		return player.getId() == od.getUid() && checkOperType(od.getOperType());
	}
	
	public void execute() {
		preExecute(); // 执行前处理
		executeOperation();
		logAction();
		postExecute();	// 执行后处理
	}
	
	public void logAction() {
/*		logger.info(roomIns.getLogPrefix().append(";[step=").append(roomIns.getEngine().getMediator().getCurrentStep()).append(";atype=").append(ActionType.toActionName(getActionType()))
				.append(";uid=").append(getPlayerUid()).append(";from=").append(getFromUid()).append(";stype=").append(getSubType()).append(";card=").append(getCard())
				.append("];handcards").append(PaiXinHelper.toCardsList(getPlayer().getHandCards().getHandCards())).toString());*/
	}
	
	protected void postExecute() {
		setExecuted();
		remainCards = desk.getEngine().getCardPool().remainCards();
		desk.getEngine().onOperationDone(this);

		createCanExecuteOperations();
		desk.getEngine().getOperManager().autoRun();
	}
	
	public boolean canExecute() {
		return status == STATUS_CAN_EXECUTE;
	}

	public void setExecuted() {
		status = STATUS_EXECUTED;
	}

	public void setPassed() {
		status = STATUS_PASSED;
		// 过牌后检查漏胡、漏碰等
		player.onPassOperation(this);
	}

	public boolean isPassed() {
		return status == STATUS_PASSED;
	}
	
	protected void preExecute() {
		
	}
	
	protected abstract String getDisplayName();
	protected abstract void executeOperation();
	protected abstract void createCanExecuteOperations();
	
	public OperDetail toOperDetail(GamePlayer receiver) {
		OperDetail operDetail = new OperDetail();
		operDetail.setUid(player.getId());
		if(target != null) operDetail.setFromUid(target.getPlayer()!=null?target.getPlayer().getId():0);
		operDetail.setOperType(operType);
		if(cards != null) {
			for(MJCard card : cards) {
				operDetail.addCard(card.getValue());		
			}
		}
		operDetail.setTarget(target!=null?target.getValue():0);
		operDetail.setRemainCards(remainCards);
		return operDetail;
	}
	
	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		msg.append("(");
		msg.append(player.getId()).append(",");
		msg.append(getDisplayName()).append(",");
		msg.append(target).append(",");
		msg.append(cards);
		msg.append("),");
		return msg.toString();
	}

	@Override
	public int compareTo(BaseOperation o) {
		// 先比较操作优先级
		if(this.getOperType() != o.getOperType()) return o.getOperType() - this.getOperType();
		// 再比较座序
		if(preOperation == null) return 0;
		return this.getPlayer().getDelta(preOperation.getPlayer()) - o.getPlayer().getDelta(preOperation.getPlayer());
	}
	
}
