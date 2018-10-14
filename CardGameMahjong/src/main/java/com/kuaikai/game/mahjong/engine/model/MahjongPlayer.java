package com.kuaikai.game.mahjong.engine.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.calculator.PlayerGameResult;
import com.kuaikai.game.mahjong.engine.checker.CheckerFactory;
import com.kuaikai.game.mahjong.engine.checker.chi.ChiChecker;
import com.kuaikai.game.mahjong.engine.checker.gang.GangChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.HuChecker;
import com.kuaikai.game.mahjong.engine.checker.peng.PengChecker;
import com.kuaikai.game.mahjong.engine.checker.ting.TingChecker;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.RoomAttr;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

public class MahjongPlayer extends GamePlayer {
	
	private boolean baoTing;	// 是否报听
	
	private CardContainer cardContainer;
	private PlayerGameResult gameResult;
	
	private HuChecker huChecker;
	private ChiChecker chiChecker;
	private PengChecker pengChecker;
	private GangChecker gangChecker;
	private TingChecker tingChecker;
	
	// 一局游戏玩家属性
	public static enum SetAttr { // 玩家属性 key
		BET,			// 下注数（陕西麻将下炮子）
		QUE_MEN,		// 缺门：10 万 20 条 30 筒，见 MahjongCard.CardType
		LOU_HU_CARDS,	// 漏胡，当前是否处于漏胡状态，value是不能胡的牌列表，空列表表示不能胡所有牌。
		LOU_PENG_CARDS,	// 漏碰，当前是否处于漏碰状态，value是不能碰的牌列表，空列表表示不能碰所有牌。
		TING_CARDS,		// 当前听牌列表
		MO_STARTED		// 玩家开始摸第一张牌
	}

	public MahjongPlayer(Player player, MahjongDesk desk) {
		super(player, desk);
		this.gameDesk = desk;
		
		this.gameResult = new PlayerGameResult(this);
	}
	
	public MahjongDesk getGameDesk() {
		return (MahjongDesk)gameDesk;
	}

	private void initCheckers() {
		huChecker = CheckerFactory.createHuChecker(this);
		pengChecker = CheckerFactory.createPengChecker(this);
		gangChecker = CheckerFactory.createGangChecker(this);
		
		if(gameDesk.getSetting().getBool(CardGameSetting.KE_CHI))
			chiChecker = CheckerFactory.createChiChecker(this);
		
		if(gameDesk.getSetting().getBool(CardGameSetting.KE_TING))
			tingChecker = CheckerFactory.createTingChecker(this);
	}
	
	public HuChecker getHuChecker() {
		return huChecker;
	}

	public ChiChecker getChiChecker() {
		return chiChecker;
	}

	public PengChecker getPengChecker() {
		return pengChecker;
	}

	public GangChecker getGangChecker() {
		return gangChecker;
	}

	public TingChecker getTingChecker() {
		return tingChecker;
	}

	public CardContainer getCardContainer() {
		return cardContainer;
	}
	
	@Override
	public void onGameStart() {
		// 游戏开始时，初始化checker和card container
		cardContainer = MahjongFactory.createCardContainer(this);
		initCheckers();
		//this.resetPoints();
	}
	
	@Override
	public void onSetStart() {
		cardContainer.clear();
		baoTing = false;
		
		for(SetAttr setAttr : SetAttr.values()) {
			setAttrs.remove(setAttr);
		}
	}

	@Override
	public void onSetEnd() {
		
	}
	
	@Override
	public void onGameEnd() {
		
	}
	
	// 记录新一局的缺门
	public boolean setQueMen(Mahjong.CardType queMen) {
		setAttrs.put(SetAttr.QUE_MEN, queMen);
		return true;
	}

	// 返回当前局的缺门，null 表示还未设置缺门
	public Mahjong.CardType getQueMen() {
		return (Mahjong.CardType)setAttrs.get(SetAttr.QUE_MEN);
	}

	public int getQueMenVal() {
		Mahjong.CardType queMen = (Mahjong.CardType)setAttrs.get(SetAttr.QUE_MEN);
		return queMen == null?0:queMen.getValue();
	}
	
	public boolean isQueMenSet() {
		return setAttrs.contains(SetAttr.QUE_MEN);
	}
	
	/*
	 * 检查玩家手牌是否有缺门牌
	 */
	public boolean hasQueMenHandCard() {
		Mahjong.CardType queMen = getQueMen();
		if(queMen == null) return false;
		for(MJCard card : cardContainer.getHandCards()) {
			if(queMen.equals(card.getCardType())) {
				return true;
			}
		}
		return false;
	}
	
	public PlayerGameResult getGameResult() {
    	return gameResult;
	}
	
/*	public void resetPoints() {
		initPoints(room.getEngine().getCalculator().getPointsSize());	// 重置玩家积分项
		addPoint(Calculator.INDEX_DI, room.getEngine().getCalculator().getInitDiFen());
	}*/
	
	public Set<Integer> getTingCardsWithoutAlmighty() {
		Set<Integer> cards = new HashSet<Integer>();
		List<Integer> tingCards = getTingCards();
		if(tingCards == null || tingCards.isEmpty()) return cards;
		cards.addAll(tingCards);
		if(getGameDesk().getEngine().containsAttr(RoomAttr.ALMIGHTY_CARD) && cards.size() > 1) {
			cards.remove(getGameDesk().getEngine().getAttr(RoomAttr.ALMIGHTY_CARD));
		}
		return cards;
	}

	public void refreshTingCards() {
		setAttrs.put(SetAttr.TING_CARDS, huChecker.getTingCards());	// 记下听的牌
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getTingCards() {
		return (List<Integer>)setAttrs.get(SetAttr.TING_CARDS);
	}

	public boolean isBaoTing() {
		return baoTing;
	}

	public void setBaoTing(boolean baoTing) {
		this.baoTing = baoTing;
	}
	
	public void onPassOperation(BaseOperation oper) {
		switch(oper.getOperType()) {
		case OperType.HU :	// 漏胡
			if(gameDesk.getSetting().getBool(CardGameSetting.NO_LOU_HU)) break;
			HuOperation huOper = (HuOperation)oper;
			if(huOper.isZimo()) break;	// 自摸不漏胡
			addLouHuCard(oper.getPreOperation().getTarget().getValue());
			break;
		case OperType.PENG :	// 漏碰
			if(!gameDesk.getSetting().getBool(CardGameSetting.LOU_PENG)) break;
			addLouPengCard(oper.getPreOperation().getTarget().getValue());
			break;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addLouHuCard(int card) {
		List<Integer> cards = (List<Integer>)setAttrs.get(SetAttr.LOU_HU_CARDS);
		if(cards == null) {
			cards = new LinkedList<Integer>();
			setAttrs.put(SetAttr.LOU_HU_CARDS, cards);
		}
		
		int louHuNum = gameDesk.getSetting().getInt(CardGameSetting.LOU_HU_NUM);
		if(louHuNum >= 0) {	// 漏胡一张或多张
			cards.add(card);
		}
	}

	@SuppressWarnings("unchecked")
	private void addLouPengCard(int card) {
		List<Integer> cards = (List<Integer>)setAttrs.get(SetAttr.LOU_PENG_CARDS);
		if(cards == null) {
			cards = new LinkedList<Integer>();
			setAttrs.put(SetAttr.LOU_PENG_CARDS, cards);
		}
		cards.add(card);
	}
	
	public boolean isHuPlayer() {
		SetResult result = this.getGameDesk().getEngine().getCurrentSetResult();
		return result == null?false:result.checkHuPlayer(this);
	}
	
}
