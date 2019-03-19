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
	
	protected static final Logger logger = LoggerFactory.getLogger("mahjong");
	
	private Set<HuOperation> huOperations = new HashSet<HuOperation>();	// 一炮多响
	private MahjongDesk desk;
	
	public MultipleHuOperations(MahjongDesk desk) {
		this.desk = desk;
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
			logger.error("MultipleHuOperations.executeHu@operation not found|uid={}", player.getId());
			return;
		}
		
		// 只执行胡牌操作，不执行 postExecute，暂不加入done operations
		oper.executeOperation();
		oper.setExecuted();

		// 发送操作结果
		desk.getMessageSender().sendSOperCard(oper);
		
		// 所有人都选择完毕，有人选择胡，进入结算，本局结束
		if(this.isAllReady()) {
			executeAllReady();
		}
		
	}
	
	public void executePass(MahjongPlayer player) {
		HuOperation oper = findHuOperation(player);
		if(oper == null) {
			logger.error("MultipleHuOperations.executePass@operation not found|uid={}", player.getId());
			return;
		}
		
		oper.setPassed();
		
		// 所有人都选择过，不胡
		if(this.isAllPassed()) {
			// 一炮多响结束
			this.clearHuOperations();
			
			// 执行过牌
			desk.getEngine().executePass(player);
			
			// 检查是否进入结算阶段，本局结束
			desk.getEngine().checkJieSuanStage();
			
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
			if(!oper.isPassed()) desk.getEngine().getOperManager().addDoneOperation(oper);
		}
		
		desk.getEngine().postMultipleHu();
		
		// 一炮多响结束
		this.clearHuOperations();
		
		// 进入结算阶段，本局结束
		desk.getEngine().enterJieSuanStage();
		desk.onSetEnd(System.currentTimeMillis());
	}
	
}
