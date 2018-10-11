package com.kuaikai.game.mahjong.engine.oper;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

/**
 * 一炮多响操作
 * 
 */
public class MultipleHuOperations {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	private Set<HuOperation> huOperations = new HashSet<HuOperation>();	// 一炮多响
	private MahjongDesk room;
	
	public MultipleHuOperations(MahjongDesk room) {
		this.room = room;
	}
	
	public void addHuOperation(HuOperation oper) {
		huOperations.add(oper);
	}

	public void clearHuOperations() {
		huOperations.clear();
	}
	
	public boolean isValid() {
		return huOperations.size() >= 2;
	}
	
	/*
	 * 一炮多响时，所有的胡家都响应了，点击胡或过
	 */
	public boolean isAllReady() {
		for(HuOperation oper : huOperations) {
			if(oper.canExecute()) return false;
		}
		return true;
	}
	
	/*
	 * 一炮多响时，所有的胡家都点击过
	 */
	public boolean isAllPassed() {
		for(HuOperation oper : huOperations) {
			if(!oper.isPassed()) return false;
		}
		return true;
	}
	
	private HuOperation findHuOperation(MahjongPlayer player) {
		for(HuOperation huOper : huOperations) {
			if(player.equals(huOper.getPlayer())) return huOper;
		}
		return null;
	}
	
	public void executeHu(MahjongPlayer player) {
		HuOperation oper = findHuOperation(player);
		if(oper == null) {
			logger.error(room.getLogPrefix().append(";MultipleHuOperations.executeHu;hu operation not found;uid:").append(player.getId()).toString());
			return;
		}
		
		// 只执行胡牌操作，不执行 postExecute，暂不加入done operations
		oper.executeOperation();

		// 发送操作结果
		room.getMessageSender().syncOperCardRes(oper);
		
		// 所有人都选择完毕，有人选择胡，进入结算，本局结束
		if(this.isAllReady()) {
			executeAllReady();
		}
		
	}
	
	public void executePass(MahjongPlayer player) {
		HuOperation oper = findHuOperation(player);
		if(oper == null) {
			logger.error(room.getLogPrefix().append(";MultipleHuOperations.executePass;hu operation not found;uid:").append(player.getId()).toString());
			return;
		}
		
		oper.setPassed();
		
		// 所有人都选择过，不胡
		if(this.isAllPassed()) {
			// 一炮多响结束
			this.clearHuOperations();
			
			// 执行过牌
			room.getEngine().executePass(player);
			
			// 检查是否进入结算阶段，本局结束
			room.getEngine().checkJieSuanStage();
			
			return;
		}
		
		// 所有人都选择完毕，有人选择胡，进入结算，本局结束
		if(this.isAllReady()) {
			executeAllReady();
		}
		
	}
	
	private void executeAllReady() {
		// 增加已完成的操作
		for(BaseOperation oper : huOperations) {
			if(!oper.isPassed()) room.getEngine().getOperManager().addDoneOperation(oper);
		}
		
		room.getEngine().postMultipleHu();
		
		// 一炮多响结束
		this.clearHuOperations();
		
		// 进入结算阶段，本局结束
		room.getEngine().enterJieSuanStage();
		room.handleOnSetEnd();
	}
	
}
