package com.kuaikai.game.mahjong.msg;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.GameAttrs;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.calculator.PlayerGameResult;
import com.kuaikai.game.mahjong.engine.calculator.PlayerSetResult;
import com.kuaikai.game.mahjong.engine.calculator.ScoreDetail;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer.SetAttr;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperDetail;
import com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo;
import com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat;
import com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType;
import com.kuaikai.game.mahjong.msg.pb.PlayerSetResultPB;
import com.kuaikai.game.mahjong.msg.pb.QueMenPB.QueMen;
import com.kuaikai.game.mahjong.msg.pb.SCanOperPB.SCanOper;
import com.kuaikai.game.mahjong.msg.pb.SGameResultPB.SGameResult;
import com.kuaikai.game.mahjong.msg.pb.SOperCardPB.SOperCard;
import com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit;
import com.kuaikai.game.mahjong.msg.pb.SSetResultPB.SSetResult;
import com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB;
import com.kuaikai.game.mahjong.msg.pb.CardGroupPB;
import com.kuaikai.game.mahjong.msg.pb.DirectionPB.Direction;
import com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB;
import com.kuaikai.game.mahjong.msg.pb.OperDetailPB;
import com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType;
import com.kuaikai.game.mahjong.msg.pb.PlayerGameResultPB;

public class DefaultMsgCreator implements MsgCreator {

	/**
	 * 牌局初始信息
	 */
	@Override
	public SSetInit.Builder createSSetInit(MahjongDesk desk, MahjongPlayer receiver) {
		SSetInit.Builder builder = SSetInit.newBuilder()
				.setCurSet(desk.getCurSet())
				.setRemainCards(desk.getEngine().getCardPool().remainCards())
				.setBankerId(desk.getBankerId())
				.setStage(desk.getEngine().getStage());
		
		// 所有玩家牌局状态
		for(GamePlayer gp : desk.getAllPlayers()) {
			builder.addPlayerSetInfos(this.createPlayerSetInfo((MahjongPlayer)gp, receiver));
		}
		
		// 最近的操作信息
		BaseOperation lastDoneOperation = desk.getEngine().getOperManager().getLastDoneOperation();
		if(lastDoneOperation != null) builder.setLastOperDetail(this.createOperDetail(lastDoneOperation.toOperDetail(receiver)));
		
		// 本人可执行操作
		List<BaseOperation> canExecuteOperations = desk.getEngine().getOperManager().findCurrentCanExecuteOperations(receiver);
		for(BaseOperation oper : canExecuteOperations) {
			builder.addCanOperDetails(createOperDetail(oper.toOperDetail(receiver)));
		}
		
		// 打牌后听的牌
		Map<Integer, DiscardTingCards> discard2TingCards = receiver.getHuChecker().getDiscard2TingCards();
		if(discard2TingCards != null) {
			for(DiscardTingCards dtc : discard2TingCards.values()) {
				builder.addDiscardTingCards(createDiscardTingCards(dtc));	
			}
		}
		
		return builder;
	}
	
	/**
	 * 玩家牌局信息
	 */
	protected PlayerSetInfo.Builder createPlayerSetInfo(MahjongPlayer player, MahjongPlayer receiver) {
		CardContainer cardContainer = player.getCardContainer();
		AttrsModel setAttrs = player.getSetAttrs();
		
		PlayerSetInfo.Builder builder = PlayerSetInfo.newBuilder()
				.setUid(player.getId())
				.setHandCardNum(cardContainer.getHandCards().size())
				.addAllDiscards(cardContainer.getDiscardValues())
				.setDirection(Direction.valueOf(setAttrs.contains(SetAttr.DIRECTION)?setAttrs.getInt(SetAttr.DIRECTION):player.getSeat()))	// 设置方位：无门风属性时，按座位顺序设置
				.addAllPoints(player.getGameResult().getFinalGamePoints().getPoints());
		
		if(player.getGameDesk().getSetting().getBool(CardGameSetting.XIA_ZHU)) {
			builder.setBet(player.getBet());
		}
		
		if(player.getGameDesk().getSetting().getBool(CardGameSetting.DING_QUE)) {
			builder.setQueMen(QueMen.valueOf(setAttrs.getInt(SetAttr.QUE_MEN)));
		}
		
		// 手牌
		if(receiver.equals(player)) {
			builder.addAllHandcards(cardContainer.getHandCardValues());
		}
		
		// 明牌
		List<CardGroup> cardGroups = cardContainer.getCardGroups();
		if(cardGroups != null) {
			for (CardGroup group : cardGroups) {
				builder.addCardGroups(this.createCardGroup(group));
			}
		}
		
		return builder;
	}

	/**
	 * 玩家明牌
	 */
	protected CardGroupPB.CardGroup.Builder createCardGroup(CardGroup group) {
		return CardGroupPB.CardGroup.newBuilder()
				.setOperType(OperType.valueOf(group.getOperType()))
				.addAllCards(group.getCardValues())
				.setTarget(group.getTarget().getValue());
	}
	
	/**
	 * 操作细节
	 */
	protected OperDetailPB.OperDetail.Builder createOperDetail(OperDetail od) {
		return OperDetailPB.OperDetail.newBuilder()
				.setUid(od.getUid())
				.setOperType(OperType.valueOf(od.getOperType()))
				.addAllCards(od.getCards())
				.setTarget(od.getTarget())
				.setRemainCards(od.getRemainCards());
	}
	
	/**
	 * 打牌后的听牌列表
	 */
	protected DiscardTingCardsPB.DiscardTingCards.Builder createDiscardTingCards(DiscardTingCards dtc) {
		return DiscardTingCardsPB.DiscardTingCards.newBuilder()
				.setDiscard(dtc.getDiscard())
				.addAllTingCards(dtc.getTingCards());
	}
	
	/**
	 * 玩家操作
	 */
	@Override
	public SOperCard.Builder createSOperCard(MahjongDesk desk, MahjongPlayer receiver) {
		SOperCard.Builder builder = SOperCard.newBuilder();
		
		// 刚完成的操作
		List<BaseOperation> lastOperations = desk.getEngine().getOperManager().getLastOperations();
		for(BaseOperation oper : lastOperations) {
			builder.addOperDetails(createOperDetail(oper.toOperDetail(receiver)));
		}
		
		// 可执行操作
		List<BaseOperation> canExecuteOperations = desk.getEngine().getOperManager().getCurrentCanExecuteOperations();
		if(canExecuteOperations != null) {
			for(BaseOperation canExecuteOperation : canExecuteOperations) {
				if(!canExecuteOperation.canExecute()) continue;
				if(!receiver.equals(canExecuteOperation.getPlayer())) continue;	// 可执行操作只发送给操作者
				builder.addCanOperDetails(createOperDetail(canExecuteOperation.toOperDetail(receiver)));
			}
		}
		
		// 打牌后听的牌
		Map<Integer, DiscardTingCards> discard2TingCards = receiver.getHuChecker().getDiscard2TingCards();
		if(discard2TingCards != null) {
			for(DiscardTingCards dtc : discard2TingCards.values()) {
				builder.addDiscardTingCards(createDiscardTingCards(dtc));	
			}
		}
		
		return builder;
	}
	
	/**
	 *	指定操作
	 */
	@Override
	public SOperCard.Builder createSOperCard(BaseOperation oper) {
		return SOperCard.newBuilder()
				.addOperDetails(createOperDetail(oper.toOperDetail(null)));
	}
	
	/**
	 * 过牌后的玩家操作
	 */
	@Override
	public SCanOper.Builder createSCanOper(MahjongDesk desk, MahjongPlayer receiver, List<BaseOperation> canExecuteOperations) {
		SCanOper.Builder builder = SCanOper.newBuilder();
		
		// 可执行操作
		for(BaseOperation canExecuteOperation : canExecuteOperations) {
			if(!canExecuteOperation.canExecute()) continue;
			if(!receiver.equals(canExecuteOperation.getPlayer())) continue;	// 可执行操作只发送给操作者
			builder.addCanOperDetails(createOperDetail(canExecuteOperation.toOperDetail(receiver)));
		}
		
		return builder;
	}

	/**
	 * 一局比赛结果
	 */
	@Override
	public SSetResult.Builder createSSetResult(MahjongDesk desk, boolean over) {
		boolean huang = desk.getEngine().getCurrentSetResult().isHuangZhuang();
		SSetResult.Builder builder = SSetResult.newBuilder()
				.setCurSet(desk.getCurSet())
				.setHuang(huang)
				.setOver(over)
				.setEndTime(desk.getAttrs().getLong(GameAttrs.SET_END_TIME));
		
		if(!huang)
			builder.setHuCard(desk.getEngine().getOperManager().getLastDoneOperation().getTarget().getValue());
		
		if(!over)
			builder.setNextBankerId(desk.getBankerId());
		
		for(PlayerSetResult psr: desk.getEngine().getCalculator().getPlayerSetResults()) {
			builder.addPlayerSetResults(this.createPlayerSetResult(psr));
		}
		
		return builder;
	}
	
	/**
	 * 一局一名玩家比赛结果
	 */
	protected PlayerSetResultPB.PlayerSetResult.Builder createPlayerSetResult(PlayerSetResult psr) {
		PlayerSetResultPB.PlayerSetResult.Builder builder = PlayerSetResultPB.PlayerSetResult.newBuilder()
				.setPlayerSetInfo(this.createPlayerSetInfo(psr.getPlayer(), psr.getPlayer()))
				.addAllPoints(psr.getGamePoints().getPoints())
				.setZimo(psr.isZimo())
				.setDianPao(psr.isDianPao())
				.setJiePao(psr.isJiePao())
				.setBaoTing(psr.getPlayer().isBaoTing());
		
		for(ScoreDetail sd : psr.getScoreDetails()) {
			builder.addScoreDetails(this.createScoreDetail(sd));
		}
		
		return builder;
	}
	
	protected ScoreDetailPB.ScoreDetail.Builder createScoreDetail(ScoreDetail sd) {
		ScoreDetailPB.ScoreDetail.Builder builder = ScoreDetailPB.ScoreDetail.newBuilder()
				.setMainType(JieSuanPB.JieSuan.valueOf(sd.getMainType()))
				.setScore(sd.getScore());
		
		if(sd.getSubTypes() != null) {
			for(int subType : sd.getSubTypes()) {
				builder.addSubTypes(JieSuanPB.JieSuan.valueOf(subType));
			}	
		}
		
		return builder;
	}
	
	/**
	 * 一场比赛结果
	 */
	@Override
	public SGameResult.Builder createSGameResult(MahjongDesk desk, boolean dismiss) {
		SGameResult.Builder builder = SGameResult.newBuilder()
				.setFinalSet(desk.getCurSet())
				.setEndTime(desk.getAttrs().getLong(GameAttrs.END_TIME))
				.setDismiss(dismiss);
		
		for(GamePlayer player: desk.getAllPlayers()) {
			MahjongPlayer p = (MahjongPlayer)player;
			builder.addPlayerGameResults(this.createPlayerGameResult(p));
		}
		
		return builder;
	}
	
	protected PlayerGameResultPB.PlayerGameResult.Builder createPlayerGameResult(MahjongPlayer p) {
		PlayerGameResult pgr = p.getGameResult();
		PlayerGameResultPB.PlayerGameResult.Builder builder = PlayerGameResultPB.PlayerGameResult.newBuilder()
				.setUid(p.getId())
				.addAllPoints(pgr.getFinalGamePoints().getPoints());
		
		for(Map.Entry<Object, Object> entry : pgr.getStats().entrySet()) {
			builder.addPlayerStats(PlayerStat.newBuilder()
					.setStatType((PlayerStatType)entry.getKey())
					.setVal((Integer)entry.getValue())
					.build());
		}
		
		return builder;
	}
	
}
