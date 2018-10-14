package com.kuaikai.game.mahjong.engine.oper;

import java.util.List;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class BuGangOperation extends BaseOperation {

	private CardGroup cardGroup;	// 由碰转为补杠的牌组
	private MJCard pengTarget;		// 被碰的牌
	
	public BuGangOperation(MahjongPlayer player, BaseOperation preOperation, CardGroup cardGroup, MJCard target) {
		super(player, preOperation);
		this.operType = OperType.BU_GANG;
		this.cardGroup = cardGroup;
		this.pengTarget = cardGroup.getTarget();
		this.target = target;
	}

	@Override
	protected void executeOperation() {
		// 找到碰牌，改为补杠
		CardContainer cardContainer = player.getCardContainer();
/*		cardGroup = cardContainer.findPengGroup(target.getCard());
		if(cardGroup == null) {
			logger.error("BuGangOperation.executeAction@peng not found|target=%d", target.getCard());
			return;
		}*/
		
		// 移除手牌，加入补杠
		if(!cardContainer.getHandCards().remove(target)) {
			logger.error("BuGangOperation.executeOperation@hand card not found|target=%d", target.getValue());
			return;
		}
		
		//pengTarget = cardGroup.getTarget();
		cardGroup.setTarget(target);
		cardGroup.getCards().add(pengTarget);
		cardGroup.setOperType(OperType.BU_GANG);
	}
	
	/*
	 * 将补杠恢复为碰（被抢杠胡时）
	 */
	public void undo() {
		if(cardGroup.checkOperType(OperType.PENG)) return;	// 已经恢复为碰，一炮多响抢杠胡时发生
		
		cardGroup.getCards().remove(pengTarget);
		cardGroup.setTarget(pengTarget);
		cardGroup.setOperType(OperType.PENG);
		
		if(calculatorDetail != null) calculatorDetail.setInvalid(true);
	}
	
	@Override
	protected void createCanExecuteOperations() {
		// 补杠判断其他人能否抢杠胡
		if(!desk.getSetting().getBool(CardGameSetting.NO_QIANG_GANG)) {
			for (GamePlayer other : desk.getAllPlayers()) {
				if (other.equals(player)) continue;
				HuOperation.check((MahjongPlayer)other, this);
			}
		}
		
		if (!desk.getEngine().getOperManager().hasCanExecuteOperations()) {
			MoOperation.check(player, this);
		}
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
		return "补杠";
	}
	
	/*
	 * 检查并返回指定玩家可执行的补杠操作
	 * 
	 */
	public static List<BaseOperation> check(MahjongPlayer player, BaseOperation preOper) {
		List<BaseOperation> operations = player.getGangChecker().checkBuGangOperations(preOper);
		if(preOper != null) preOper.addCanExecuteOperations(operations);
		return operations;
	}
	
}
