package com.kuaikai.game.mahjong.engine.oper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

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
	public List<BaseOperation> findCurrentCanExecuteOperations(MahjongPlayer player) {
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
				if(oper.canExecute() && oper.checkOperType(OperType.HU)) huCount++;
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
				if(oper.checkOperType(OperType.HU)) {
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
	
	/*
	 * 检查当前状态是否为跟庄
	 */
	public boolean checkGenZhuang() {
		if(genZhuangChecked) return false;	// 检查过不再检查
		int count = 0, card = 0;
		for(BaseOperation oper : doneOperations) {
			switch(oper.getOperType()) {
			case OperType.DA :
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
			case OperType.MO :
			case OperType.AN_GANG :	// 允许摸和暗杠
				break;
			default :
				genZhuangChecked = true;
				return false;
			}
		}
		return false;
	}
	
}
