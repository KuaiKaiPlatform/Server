package com.kuaikai.game.mahjong.engine.checker.hu.mode;

import java.util.List;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.constants.RoomAttr;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

public class HuModesChecker {
/*	private static final Logger logger = LoggerFactory.getLogger(HuModes.class);
	private static final Logger loggerPlay = LoggerFactory.getLogger("play");
	*/
/*	public static boolean qiangGang(HuOperation oper) {
		int step = oper.getStep();
		MahjongActionMediator mediator = oper.getRoomInstance().getEngine().getMediator();
		// 略过之前的过和胡（一炮多响）
		for(int i=(step-1); i>=0; i--) {
			IEPlayerAction preAction = mediator.getDoneActionByStep(i);
			if(preAction == null) break;
			if(preAction.getActionType() == ActionType.GUO || preAction.getActionType() == ActionType.HU) {
				step = i;
			} else
				break;
		}
		
		IEPlayerAction last1 = mediator.getDoneActionByStep(step - 1);
		IEPlayerAction last2 = mediator.getDoneActionByStep(step - 2);
		if (last1 == null || last2 == null)
			return false;

		// 判断上一步是杠,并且是补杠
		if (last1.getActionType() == ActionType.GANG
				&& last1.getSubType() == MJPlayType.BU_GANG) {
			return true;
		}
		return false;
	}*/

	public static boolean haiDiLao(HuOperation oper) {
		return oper.isZimo() && oper.getRoom().getEngine().getCardPool().isEmpty();
	}

	public static boolean gangShangHua(HuOperation oper) {
		if(!oper.isZimo()) return false;	// 前一步是摸
		BaseOperation preOper2 = oper.getPreOperation().getPreOperation();
		if(preOper2 == null) return false;
		return OperType.isGang(preOper2.getOperType());	// 前两步是杠
	}

	public static boolean tianHu(HuOperation oper, boolean keAnGang) {	// 天胡：庄家摸第一张牌后自摸，根据参数决定是否可暗杠
		if(!oper.getPlayer().isBanker()) return false;
		if(!oper.isZimo()) return false;		// 前一步是摸牌
		
		BaseOperation preOper2 = oper.getPreOperation().getPreOperation();
		if(!keAnGang)
			return preOper2 == null;	// 是先手摸牌
		
		// 前两步是暗杠
		if(!preOper2.checkOperType(OperType.AN_GANG)) return false;
		
		BaseOperation preOper3 = preOper2.getPreOperation();
		
		// 前三步是摸牌
		if(preOper3 == null || !preOper3.checkOperType(OperType.MO)) return false;
		
		// 是先手摸牌
		return preOper3.getPreOperation() == null;
	}

	public static boolean diHu(HuOperation oper) {	// 地胡：庄家打出的第一张牌，闲家胡牌
		// 闲家胡牌
		if(oper.getPlayer().isBanker()) return false;
		
		// 前一步是庄家打牌
		BaseOperation preOper1 = oper.getPreOperation();
		if(preOper1 == null || !preOper1.checkOperType(OperType.DA) || !preOper1.getPlayer().isBanker()) return false;
		
		// 前两步是庄家先手摸牌
		BaseOperation preOper2 = preOper1.getPreOperation();
		return preOper2 != null && preOper2.checkOperType(OperType.MO) && preOper2.getPlayer().isBanker() && preOper2.getPreOperation() == null;
	}
	
	public static boolean gangHouPao(HuOperation oper) {	// 杠后炮：杠后点出去的炮
		// 判断上一步是打，上二步是摸，上三步是杠 
		BaseOperation pre1 = oper.getPreOperation();
		if(pre1 == null || !pre1.checkOperType(OperType.DA)) return false;
		
		BaseOperation pre2 = pre1.getPreOperation();
		if(pre2 == null || !pre2.checkOperType(OperType.MO)) return false;
		
		BaseOperation pre3 = pre2.getPreOperation();
		if(pre3 == null || OperType.isGang(pre3.getOperType())) return false;
		
		return true;
	}
	
/*	public static boolean gangHouPao(HuOperation huAction) {	// 杠后炮：杠后点出去的胡
		int step = huAction.getStep();
		AbstractActionMediator mediator = huAction.getRoomInstance().getEngine().getMediator();
		
		// 一炮多响可能胡或过
		IEPlayerAction last1 = null;
		while(last1 == null) {
			IEPlayerAction action = mediator.getDoneActionByStep(--step);
			if(action == null) return false;
			switch(action.getActionType()) {
			case ActionType.GUO :
			case ActionType.HU :
				continue;
			}
			last1 = action;
		}

		IEPlayerAction last2 = null;
		while(last2 == null) {
			IEPlayerAction action = mediator.getDoneActionByStep(--step);
			if(action == null) return false;
			switch(action.getActionType()) {
			case ActionType.GUO :
			case ActionType.HU :
				continue;
			}
			last2 = action;
		}
		
		IEPlayerAction last3 = null;
		while(last3 == null) {
			IEPlayerAction action = mediator.getDoneActionByStep(--step);
			if(action == null) return false;
			switch(action.getActionType()) {
			case ActionType.GUO :
			case ActionType.HU :
				continue;
			}
			last3 = action;
		}
		
		// 判断上一步是打，上二步是摸，上三步是杠 
		if (last3.getActionType() == ActionType.GANG
				&& last3.getPlayerUid() != huAction.getPlayerUid()
				&& last2.getActionType() == ActionType.MO
				&& last2.getPlayerUid() != huAction.getPlayerUid()
				&& last1.getActionType() == ActionType.DA
				&& last1.getPlayerUid() != huAction.getPlayerUid()) {
			return true;
		}
		return false;
	}*/
	
	/**
	 * 检查所胡的牌是不是绝张
	 * @param oper
	 * @return
	 */
	public static boolean jueZhang(HuOperation oper) {
		int count = 0; // 明牌计数
		int huCard = oper.getTarget().getValue();
		for(MahjongPlayer player : oper.getRoom().getAllPlayers()) {
			List<CardGroup> cardGroups = player.getMjPlayer().getCardContainer().getCardGroups();
			if(cardGroups == null) continue;
			for(CardGroup group : cardGroups) {
				//if(!group.isValid()) continue;
				for(MJCard card : group.getCards()) {
					if(card.getValue() == huCard) {
						count++;
					}
				}
			}
		}
		
		for(MJCard card : oper.getRoom().getEngine().getCardPool().getDiscards()) {
			if(card.getValue() == huCard) {
				count++;
			}
		}
		return count >= 3;
	}
	
	/**
	 * 检查所胡的牌是不是卡边钓，卡边钓一定是单钓，单钓不一定是卡边钓，如2条是万能牌，3条4条单钓5条，不算卡边钓。
	 * @param action
	 * @return
	 */
	public static boolean kaBianDiao(HuOperation action) {
		return action.containsPaiXin(PaiXin.BIAN_ZHANG) || action.containsPaiXin(PaiXin.KAN_ZHANG) || action.containsPaiXin(PaiXin.DAN_DIAO_JIANG);
	}
	
	/**
	 * 检查所胡的牌是不是单钓
	 * @param operation
	 * @return
	 */
	public static boolean danDiao(MahjongPlayer player) {
		Set<Integer> cards = player.getMjPlayer().getTingCardsWithoutAlmighty();
		return cards != null && cards.size() == 1;
	}

	/**
	 * 检查是不是单钓万能牌
	 * @param operation
	 * @return
	 */
	public static boolean danDiaoAlmighty(MahjongPlayer player) {
		List<Integer> tingCards = (List<Integer>)player.getMjPlayer().getAttr(MahjongPlayer.SetAttr.TING_CARDS);
		if(tingCards == null || tingCards.size() != 1) return false;
		if(!player.getRoom().getEngine().containsAttr(RoomAttr.ALMIGHTY_CARD)) return false;
		
		int almightyCard = player.getRoom().getEngine().getAlmightyCardNum();
		return tingCards.get(0) == almightyCard;
	}
	
	/**
	 * 检查是否听所有牌
	 * @param operation
	 * @return
	 */
	public static boolean tingAll(MahjongPlayer player) {
		List<Integer> tingCards = (List<Integer>)player.getMjPlayer().getAttr(MahjongPlayer.SetAttr.TING_CARDS);
		if(tingCards == null || tingCards.isEmpty()) return false;
		List<Integer> initCardPool = player.getRoom().getEngine().getProcessor().getInitCardPool();
		return tingCards.size() == initCardPool.size();
	}
	
	/**
	 * 返回胡牌玩家打过万能牌的张数
	 * @param huAction
	 * @return
	 */
/*	public static int almightyDa(HuOperation huAction) {
		int count = 0;
		Player p = huAction.getPlayer();
		int almighty = MJGameUtil.getAlmightyCardNum(huAction.getRoomInstance());
        List<MJCard> outCards = huAction.getRoomInstance().getEngine().getOutCardPool();
        for (MJCard c : outCards) {
            if(c.getCardNum() == almighty && c.getUid() == p.getid()) {
                count++;
            }
        }
		return count;
	}*/

	/**
	 * 返回胡牌玩家是否碰过万能牌
	 * @param huAction
	 * @return
	 */
/*	public static boolean almightyPeng(HuOperation huAction) {
		Player p = huAction.getPlayer();
		List<CardGroup> groups = p.getHandCards().getOpencards();
		for(CardGroup group : groups) {
			if(!group.isValid()) continue;
			if(group.getGType() != MJPlayType.PENG) continue;
			if(group.getCardsList().get(0).isAlmighty(huAction.getRoomInstance())) return true;
		}
		return false;
	}*/

	/**
	 * 返回胡牌玩家是否杠过万能牌
	 * @param huAction
	 * @return
	 */
/*	public static boolean almightyGang(HuOperation huAction) {
		Player p = huAction.getPlayer();
		List<CardGroup> groups = p.getHandCards().getOpencards();
		for(CardGroup group : groups) {
			if(!group.isValid()) continue;
			switch(group.getGType()) {
			case MJPlayType.AN_GANG :
			case MJPlayType.BU_GANG :
			case MJPlayType.DIAN_GANG :
				if(group.getCardsList().get(0).isAlmighty(huAction.getRoomInstance())) return true;
				break;
			default :
				break;
			}
		}
		return false;
	}*/
	
	/**
	 * 返回胡牌玩家手牌是否不含万能牌，如果胡了万能牌，由ignoreHuCard决定是否略过
	 * @param huAction
	 * @return
	 */
/*	public static boolean almightyNone(HuOperation huAction) {
		Player p = huAction.getPlayer();
		int almighty = MJGameUtil.getAlmightyCardNum(huAction.getRoomInstance());
		List<MJCard> cards = p.getHandCards().getHandCards();
		
		boolean ignoreHuCard = huAction.getRoomInstance().getSettingBool(GameSetting.ALMIGHTY_NONE_IGNORE_HU_CARD);
		if(huAction.getCard() != almighty) ignoreHuCard = false;
		for(MJCard c : cards) {
			if(c.getCardNum() == almighty) {
				if(ignoreHuCard) {
					ignoreHuCard = false;
					continue;
				}
				return false;
			}
		}
		return true;
	}*/

	/**
	 * 返回胡牌玩家手牌是否还原万能牌（不做万能牌使用）
	 * @param huAction
	 * @return
	 */
/*	public static boolean almightyVoid(HuOperation huAction) {
		int almighty = MJGameUtil.getAlmightyCardNum(huAction.getRoomInstance());
		Player p = huAction.getPlayer();
		List<Integer> cards = PaiXinHelper.toCardsList(p.getHandCards().getHandCards());
		
		if(cards.size() % 3 != 2) {	// 胡牌执行前，未加入他人打的牌
			cards.add(huAction.getCard());
		}
		if(!cards.contains(almighty)) return false;
		
		Collections.sort(cards);
		boolean result = false;
		if(huAction.containsPaiXin(PaiXin.SHI_SAN_BU_KAO)) {
			result = ShiSanBuKao.check(cards) > 0 || result;
		}
		if(huAction.containsQiDuiPaiXin()) {
			Map<Integer, Integer> cardCount = PaiXinHelper.countCards(cards);
			result = QiDui.check(cardCount) >= 0 || result;
		}
		if(huAction.containsPaiXin(PaiXin.BIAO_ZHUN_HU)) {
			result = BiaoZhunHu.check(cards) || result;
		}
		return result;
	}*/

	
}
