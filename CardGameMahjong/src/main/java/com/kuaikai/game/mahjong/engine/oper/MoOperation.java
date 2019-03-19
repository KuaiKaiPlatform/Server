package com.kuaikai.game.mahjong.engine.oper;

import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardPool;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;
import com.kuaikai.game.mahjong.engine.model.SetResult;

/**
 * 摸牌
 * 
 */
public class MoOperation extends BaseOperation {
	
	public MoOperation(MahjongPlayer player, BaseOperation preOperation) {
		super(player, preOperation);
		this.autoRun = true;
		this.operType = OperType.MO;
	}

	@Override
	protected void executeOperation() {
		CardPool cardPool = desk.getEngine().getCardPool();
		// 牌池没牌了
		if(cardPool.isEmpty()) {
			// 无牌可摸，记录荒庄
			return;
		}
		
		BaseOperation lastDoneAction = desk.getEngine().getOperManager().getLastDoneOperation();
		int card = 0;
		if(lastDoneAction != null && OperType.isGang(lastDoneAction.getOperType())) {	// 杠后摸牌，取牌墩最后一张
			card = cardPool.takeTailCard(1);
		} else {
			card = cardPool.takeNextCard();
		}
		
		MJCard mjCard = MahjongFactory.createMJCard(card, player);
		player.getCardContainer().addHandCard(mjCard);
		this.setTarget(mjCard);
	}
	
	@Override
	protected void createCanExecuteOperations() {
		// 胡
		HuOperation.check(player, this);
		
		// 杠
		BuGangOperation.check(player, this);
		AnGangOperation.check(player, this);
		
		// 听
		TingOperation.check(player, this);
		
		if(desk.getEngine().getOperManager().hasCanExecuteOperations()) return;

		// 打
		DaOperation.check(player, this);

	}
	
	@Override
	protected void postExecute() {
		player.getSetAttrs().remove(MahjongPlayer.SetAttr.LOU_HU_CARDS);
		player.getSetAttrs().remove(MahjongPlayer.SetAttr.LOU_PENG_CARDS);
		player.getSetAttrs().put(MahjongPlayer.SetAttr.MO_STARTED, true);	// 记录玩家开始摸第一张牌);
		super.postExecute();
	}
	
	@Override
	protected String getDisplayName() {
		return "摸";
	}
	
	@Override
	public OperDetail toOperDetail(GamePlayer receiver) {
		OperDetail od = super.toOperDetail(receiver);
		if(!player.equals(receiver)) {
			od.setTarget(0);
		}
		return od;
	}
	
	public static MoOperation execute(MahjongPlayer player, BaseOperation preOperation) {
		MoOperation moOper = MoOperation.check(player, preOperation);
		if(moOper != null) moOper.execute();
		//player.getRoom().getEngine().getOperManager().autoRun();
		return moOper;
	}
	
	/*
	 * 检查并返回指定玩家可执行的摸牌操作
	 * 
	 */
	public static MoOperation check(MahjongPlayer player, BaseOperation preOperation) {
		if(player.getGameDesk().getEngine().getProcessor().checkHuangZhuang()) {
			// 无牌可摸，记录荒庄，准备结算
			SetResult setResult = player.getGameDesk().getEngine().getCurrentSetResult();
			setResult.setHuangZhuang(true);
			player.getGameDesk().getEngine().enterJieSuanStage();
			return null;	
		}
		MoOperation moOper = OperationFactory.createMoOperation(player, preOperation);
		if(preOperation != null) preOperation.addCanExecuteOperation(moOper);
		return moOper;
	}
	
}
