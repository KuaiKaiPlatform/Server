package com.kuaikai.game.mahjong.engine.oper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.common.utils.GameThreadPool;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.msg.pb.OperDetailPB;
import com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType;
import com.kuaikai.game.mahjong.msg.handler.COperCardHandler;
import com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard;

public class OperationManager {

	private static final Logger logger = LoggerFactory.getLogger("play");
	
	private MahjongDesk desk;
	
	// 所有已完成操作
	private List<BaseOperation> doneOperations = new ArrayList<BaseOperation>();
	// 上一批执行完的操作，如：杠牌后摸牌。发送后立即清除
	private List<BaseOperation> lastOperations = new ArrayList<BaseOperation>();
	// 当前可执行操作，如：一名玩家的胡、杠、碰，一炮多响的所有胡等。
	private List<BaseOperation> currentCanExecuteOperations = new ArrayList<BaseOperation>();
	// 一炮多响操作
	private MultipleHuOperations multipleHuOperations;
	// 检查过跟庄
	private boolean genZhuangChecked;
	// 定时操作
	private ScheduledFuture future;
	private BaseOperation scheduledOperation;	// 已计划的操作
	
	public OperationManager(MahjongDesk desk) {
		this.desk = desk;
	}
	
	public List<BaseOperation> getLastOperations() {
		return lastOperations;
	}

	private void addLastOperation(BaseOperation operation) {
		lastOperations.add(operation);
	}

	public void clearLastOperations() {
		lastOperations.clear();
	}
	
	public void onSetStart() {
		this.lastOperations.clear();
		this.doneOperations.clear();
		this.currentCanExecuteOperations.clear();
	}
	
	public void addDoneOperation(BaseOperation operation) {
		doneOperations.add(operation);
	}
	
	public BaseOperation getLastDoneOperation() {
		return doneOperations.isEmpty()?null:doneOperations.get(doneOperations.size()-1);
	}

	public BaseOperation getLastDoneOperation(int operType) {
		for(int i=doneOperations.size()-1; i>=0; i--) {
			BaseOperation oper = doneOperations.get(i);
			if(oper.checkOperType(operType)) return oper;
		}
		return null;
	}
	
	public boolean hasCanExecuteOperations() {
		BaseOperation lastDone = this.getLastDoneOperation();
		return lastDone != null && lastDone.hasCanExecuteOperations();
	}
	
	public void clearCurrentCanExecuteOperations() {
		currentCanExecuteOperations.clear();
	}
	
	/*
	 * 在当前可执行操作中查找匹配的操作
	 */
	public BaseOperation findCurrentCanExecuteOperation(OperDetail od) {
		List<BaseOperation> canExecuteOperations = this.getCurrentCanExecuteOperations();
		for(BaseOperation oper : canExecuteOperations) {
			if(oper.match(od)) return oper;
		}
		return null;
	}

	/*
	 * 在当前可执行操作中查找指定玩家的操作
	 */
	public List<BaseOperation> findCurrentCanExecuteOperations(GamePlayer player) {
		List<BaseOperation> canExecuteOperations = this.getCurrentCanExecuteOperations();
		List<BaseOperation> result = new LinkedList<BaseOperation>();
		for(BaseOperation oper : canExecuteOperations) {
			if(player.equals(oper.getPlayer())) {
				result.add(oper);
			}
		}
		return result;
	}
	
	/*
	 * 返回当前可执行操作
	 */
	public List<BaseOperation> getCurrentCanExecuteOperations() {
		if(currentCanExecuteOperations.isEmpty()) {
			currentCanExecuteOperations.addAll(this.findCanExecuteOperationsByPriority());
			
/*			if(logger.isDebugEnabled()) {
				logger.debug(String.format("OperationManager.getCurrentCanExecuteOperations@room=%d|opers=%s",
						room.getRoomid(), currentCanExecuteOperations));			
			}*/
		}
		return currentCanExecuteOperations;
	}
	
	/*
	 * 返回最近一次操作完成后最优先的可执行操作
	 */
	private List<BaseOperation> findCanExecuteOperationsByPriority() {
		BaseOperation lastDoneOperation = getLastDoneOperation();
		if(lastDoneOperation == null || !lastDoneOperation.hasCanExecuteOperations()) return Collections.emptyList();
		List<BaseOperation> canExecuteOperations = lastDoneOperation.getCanExecuteOperations();
		
		// 一炮多响计数
		int huCount = 0;
		if(desk.getSetting().getBool(CardGameSetting.MULTIPLE_HU)) {
			for(BaseOperation oper : canExecuteOperations) {
				if(oper.canExecute() && oper.checkOperType(OperType.HU_VALUE)) huCount++;
			}
		}
		
		if(huCount > 1 && multipleHuOperations == null) {
			multipleHuOperations = new MultipleHuOperations(desk);
		}
		
		List<BaseOperation> result = new LinkedList<BaseOperation>();
		//发送优先级最高的动作，如果多个用户有优先级交叉的动作，则只发送高于交叉动作之前的动作
		//如： A 可胡 可杠 B 可胡， 则只给A发可胡，A选择过之后才给B发可胡，B选择过，再给A发可杠
		// 一炮多响时只发送所有的胡
		boolean selected = false;
		BaseOperation operationTemp = null;
		for(BaseOperation oper : canExecuteOperations) {
			if(!oper.canExecute()) continue;
			if(huCount > 1) { // 一炮多响，只加所有的胡
				if(oper.checkOperType(OperType.HU_VALUE)) {
					result.add(oper);
					multipleHuOperations.addHuOperation((HuOperation)oper);
				}
				continue;
			}
			
			if (operationTemp != null && !oper.getPlayer().equals(operationTemp.getPlayer())) {
				selected = true;
			}
			
			if(!selected) {
				operationTemp = oper;
				result.add(oper);
			}
		}
		
		return result;
	}
	
	public boolean isMultipleHu() {
		return multipleHuOperations != null && multipleHuOperations.isValid();
	}
	
	public MultipleHuOperations getMultipleHuOperations() {
		return multipleHuOperations;
	}

	public void autoRun() {
		List<BaseOperation> canExecuteOperations = this.getCurrentCanExecuteOperations();
		for(BaseOperation oper : canExecuteOperations) {
			if(oper.isAutoRun()) {
				oper.execute();
				break;
			}
		}
	}

	public void onOperationDone(BaseOperation oper) {
		addDoneOperation(oper);
		addLastOperation(oper);
		clearCurrentCanExecuteOperations();
	}
	
	public void onOperationEnd(BaseOperation oper) {
		lastOperations.clear();
	}
	
	public void scheduleOperation() {
		int delaySeconds = desk.getSetting().getInt(CardGameSetting.OPER_DELAY_SECONDS);
		if(delaySeconds > 0) scheduleOperation(delaySeconds*1000);
	}
	
	public void scheduleOperation(int deplayMilliSeconds) {
		if(deplayMilliSeconds <= 0) return;
		
		// 找到第一个可执行的操作
		List<BaseOperation> canExecuteOperations = getCurrentCanExecuteOperations();
		BaseOperation scheduledOper = null;
		for(BaseOperation canExecuteOperation : canExecuteOperations) {
			if(!canExecuteOperation.canExecute()) continue;
			scheduledOper = canExecuteOperation;
			break;
		}
		
		if(scheduledOper == null) {
			logger.warn("OperationManager.scheduleOperation@no operation scheduled|deskKey={}|lastOper={}", desk.getDesk().getKey(), this.getLastDoneOperation());
			return;
		}
		
		// 已经计划过的，不再重复计划
		if(scheduledOper.equals(scheduledOperation)) {
			logger.warn("OperationManager.scheduleOperation@operation has been scheduled|deskKey={}|lastOper={}|scheduledOper={}",
					desk.getDesk().getKey(), this.getLastDoneOperation(), scheduledOper);
			return;
		}
		scheduledOperation = scheduledOper;
		
		// 报听的玩家，1秒后自动执行其操作
		if(scheduledOper.getPlayer().isBaoTing())
			deplayMilliSeconds = 1000;
		
		OperDetailPB.OperDetail.Builder operDetail = OperDetailPB.OperDetail.newBuilder()
				.setOperType(OperType.valueOf(scheduledOper.getOperType()))
				.setUid(scheduledOper.getPlayer().getId())
				.setTarget(scheduledOper.getTarget()==null?0:scheduledOper.getTarget().getValue());
		if(scheduledOper.checkOperType(OperType.DA_VALUE)) {
			operDetail.setTarget(scheduledOper.getPlayer().getCardContainer().findLastHandCard().getValue());
		} else if(scheduledOper.checkOperType(OperType.TING_VALUE)) {
			TingOperation tingOperation = (TingOperation)scheduledOper;
			operDetail.setTarget(tingOperation.getDiscard());
		}
		
		if(scheduledOper.getCards() != null) {
			for(MJCard card: scheduledOper.getCards()) {
				operDetail.addCards(card.getValue());
			}	
		}
		
		COperCard cOperCard = COperCard.newBuilder().setOperDetail(operDetail).build();
		COperCardHandler cOperCardHandler = new COperCardHandler(scheduledOper.getPlayer().getId(), cOperCard);
		
		if(future != null && !future.isCancelled() && !future.isDone()) {
			future.cancel(false);
		}
		future = GameThreadPool.getScheduler().schedule(
				cOperCardHandler,
				deplayMilliSeconds,
				TimeUnit.MILLISECONDS);
		
	}
	
	/*
	 * 检查当前状态是否为跟庄
	 */
	public boolean checkGenZhuang() {
		if(genZhuangChecked) return false;	// 检查过不再检查
		int count = 0, card = 0;
		for(BaseOperation oper : doneOperations) {
			switch(oper.getOperType()) {
			case OperType.DA_VALUE :
				int discard = oper.getTarget().getValue();
				if(card == 0) 
					card = discard;
				else {
					if(card != discard) {
						genZhuangChecked = true;
						return false;
					}
				}
					
				if(++count == desk.getPlayerNum()) {
					genZhuangChecked = true;
					return true;
				}
				break;
			case OperType.MO_VALUE :
			case OperType.AN_GANG_VALUE :	// 允许摸和暗杠
				break;
			default :
				genZhuangChecked = true;
				return false;
			}
		}
		return false;
	}
	
}
