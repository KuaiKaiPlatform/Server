package com.kuaikai.game.mahjong.engine.oper;

import java.util.Set;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.msg.pb.CardTypePB.CardType;

/**
 * 打牌
 * 
 */
public class DaOperation extends BaseOperation {
	
	public DaOperation(MahjongPlayer player, BaseOperation preOperation) {
		super(player, preOperation);
		this.operType = OperType.DA;
	}

	@Override
	protected void preExecute() {
		checkLouHu();
		super.preExecute();
	}
	
	@Override
	protected void createCanExecuteOperations() {
		for(GamePlayer gp : desk.getAllPlayers()) {
			if(gp.equals(player)) continue;
			MahjongPlayer p = (MahjongPlayer)gp;
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
		player.getCardContainer().discard(target);	// 移出手牌
		desk.getEngine().getCardPool().discard(target);				// 放入牌池
	}
	
	@Override
	protected void postExecute() {
		player.refreshTingCards();	// 记下听的牌
		super.postExecute();
		if(desk.getSetting().getBool(CardGameSetting.GEN_ZHUANG))
			checkGenZhuang();
	}
	
	private void checkGenZhuang() {
		if(desk.getEngine().getOperManager().checkGenZhuang()) {
			desk.getEngine().onGenZhuang();
		}
	}
	
	private void checkLouHu() {
		// 如果打出的牌自己能胡，并且房间设置为漏胡一张，加到漏胡牌列表中
		if(desk.getSetting().getBool(CardGameSetting.NO_LOU_HU)) return;
		if(desk.getSetting().getInt(CardGameSetting.LOU_HU_NUM) < 0) return;

		Set<Integer> paiXins = player.getHuChecker().checkPaiXins(player.getCardContainer().getHandCards(), target);
		if(paiXins == null || paiXins.isEmpty()) return;
		
		player.addLouHuCard(target.getValue());
	}

	/*
	 * 检查是否匹配玩家发送的操作：找到要打出的手牌，设置target
	 */
	@Override
	public boolean match(OperDetail od) {
		if(!matchCommon(od)) return false;
		
		// 听牌后只能打最后一张摸的牌
		if(player.isBaoTing()) {
			MJCard card = player.getCardContainer().findLastHandCard();
			if(card == null || card.getValue() != od.getTarget()) return false;
			this.setTarget(card);
			return true;
		}
		
		MJCard card = player.getCardContainer().findHandCard(od.getTarget());
		if(card == null) return false;
		
		// 有硬缺限制时，只能打缺门的牌
		if(desk.getSetting().getBool(CardGameSetting.DING_QUE) && player.hasQueMenHandCard()) {
			CardType queMen = player.getQueMen();
			if(!card.getCardType().equals(queMen)) {
/*				logger.warn(String.format("DaOperation.check@can only diacard que men|user=%d|room=%d|target=%d|queMen=%d",
						player.getId(), desk.getRoomid(), od.getTarget(), queMen==null?0:queMen.getValue()));*/
				return false;
			}
		}
		
		this.setTarget(card);
		return true;
	}
	
	@Override
	protected String getDisplayName() {
		return "打";
	}
	
	public static DaOperation check(MahjongPlayer player, BaseOperation preOper) {
		DaOperation operation = OperationFactory.createDaOperation(player, preOper);
		if(preOper != null) preOper.addCanExecuteOperation(operation);
		return operation;
	}
	
}
