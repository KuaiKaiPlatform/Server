package com.kuaikai.game.mahjong.engine.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.logic.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.calculator.PlayerGameResult;
import com.kuaikai.game.mahjong.engine.checker.CheckerFactory;
import com.kuaikai.game.mahjong.engine.checker.chi.ChiChecker;
import com.kuaikai.game.mahjong.engine.checker.gang.GangChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.HuChecker;
import com.kuaikai.game.mahjong.engine.checker.peng.PengChecker;
import com.kuaikai.game.mahjong.engine.checker.ting.TingChecker;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.RoomAttr;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

public class MahjongPlayer extends GamePlayer {
	
	private MahjongDesk desk;
	private boolean baoTing;	// 是否报听
	
	private CardContainer cardContainer;
	private PlayerGameResult gameResult;
	private Map<Object, Object> attrs = new HashMap<Object, Object>();	// 玩家属性
	
	private HuChecker huChecker;
	private ChiChecker chiChecker;
	private PengChecker pengChecker;
	private GangChecker gangChecker;
	private TingChecker tingChecker;
	
	// 一局游戏玩家属性
	private AttrsModel setAttrs;
	
	// 一局游戏玩家属性
	public static enum SetAttr { // 玩家属性 key
		QUE_MEN,		// 缺门：10 万 20 条 30 筒，见 MahjongCard.CardType
		LOU_HU_CARDS,	// 漏胡，当前是否处于漏胡状态，value是不能胡的牌列表，空列表表示不能胡所有牌。
		LOU_PENG_CARDS,	// 漏碰，当前是否处于漏碰状态，value是不能碰的牌列表，空列表表示不能碰所有牌。
		TING_CARDS,		// 当前听牌列表
		MO_STARTED		// 玩家开始摸第一张牌
	}

	public MahjongPlayer(Player player, MahjongDesk desk) {
		super(player);
		this.desk = desk;
		
		this.gameResult = new PlayerGameResult(this);
	}
	
	public MahjongDesk getDesk() {
		return desk;
	}

	private void initCheckers() {
		huChecker = CheckerFactory.createHuChecker(this);
		pengChecker = CheckerFactory.createPengChecker(this);
		gangChecker = CheckerFactory.createGangChecker(this);
		
		if(desk.getSetting().getBool(GameSetting.KE_CHI))
			chiChecker = CheckerFactory.createChiChecker(this);
		
		if(desk.getSetting().getBool(GameSetting.KE_TING))
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
	
	public void onGameStart() {
		// 游戏开始时，初始化checker和card container
		cardContainer = MahjongFactory.createCardContainer(this);
		initCheckers();
		//this.resetPoints();
	}
	
	public void onSetStart() {
		cardContainer.clear();
		baoTing = false;
		
		for(SetAttr setAttr : SetAttr.values()) {
			setAttrs.remove(setAttr);
		}
	}
	
	public AttrsModel getSetAttrs() {
		return setAttrs;
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
		return attrs.containsKey(SetAttr.QUE_MEN);
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
		if(desk.getEngine().containsAttr(RoomAttr.ALMIGHTY_CARD) && cards.size() > 1) {
			cards.remove(desk.getEngine().getAttr(RoomAttr.ALMIGHTY_CARD));
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
			if(desk.getSetting().getBool(GameSetting.NO_LOU_HU)) break;
			HuOperation huOper = (HuOperation)oper;
			if(huOper.isZimo()) break;	// 自摸不漏胡
			addLouHuCard(oper.getPreOperation().getTarget().getValue());
			break;
		case OperType.PENG :	// 漏碰
			if(!desk.getSetting().getBool(GameSetting.LOU_PENG)) break;
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
		
		int louHuNum = desk.getSetting().getInt(GameSetting.LOU_HU_NUM);
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
	
}
