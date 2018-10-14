package com.kuaikai.game.mahjong.engine.checker.gang;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardContainer;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.AnGangOperation;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.DianGangOperation;
import com.kuaikai.game.mahjong.engine.oper.OperationFactory;
import com.kuaikai.game.mahjong.engine.paixin.PaiXinHelper;

/**
 * 默认的杠牌检查器
 * 
 */
public class DefaultGangChecker implements GangChecker {

	protected static final Logger logger = LoggerFactory.getLogger("play");
	protected final MahjongDesk desk;
	protected final MahjongPlayer player;
	
	public DefaultGangChecker(MahjongPlayer player) {
		this.player = player;
		this.desk = player.getGameDesk();
	}
	
	/*
	 * 检查并返回指定玩家的补杠操作
	 */	
	@Override
	public List<BaseOperation> checkBuGangOperations(BaseOperation oper) {
		if(!preCheckBuGang(oper)) return null;
		CardContainer cardContainer = player.getCardContainer();
		if(cardContainer.getCardGroups() == null) return null;
		
		List<BaseOperation> result = new LinkedList<BaseOperation>();
		int almighty = desk.getEngine().getAlmightyCardNum();
		for(CardGroup group : cardContainer.getCardGroups()) {
			//if(!group.isValid()) continue;
			if(!group.checkOperType(OperType.PENG)) continue;
			int cardNum = group.getTarget().getValue();
			if(cardNum == almighty && !desk.getSetting().getBool(CardGameSetting.ALMIGHTY_GANG)) continue;	// 不能杠万能牌
			
			MJCard target = cardContainer.findHandCard(cardNum);
			if(target != null) {
				result.add(OperationFactory.createBuGangOperation(player, oper, group, target));
			}
		}
		return result.isEmpty()?null:result;
	}
	
	protected boolean preCheckBuGang(BaseOperation oper) {
		// 听后补杠
		if(player.isBaoTing() && !desk.getSetting().getBool(CardGameSetting.TING_HOU_BU_GANG))  return false;
		
		switch(oper.getOperType()) {
		case OperType.MO :	// 摸后补杠
			return player.equals(oper.getPlayer());
		case OperType.CHI :		// 吃后补杠
			return player.equals(oper.getPlayer()) && desk.getSetting().getBool(CardGameSetting.CHI_HOU_BU_GANG);
		case OperType.PENG :	// 碰后补杠
			return player.equals(oper.getPlayer()) && desk.getSetting().getBool(CardGameSetting.PENG_HOU_BU_GANG);
		}
		
		return false;
	}
	
	/*
	 * 检查并返回指定玩家的暗杠操作（摸、吃、碰后）
	 */	
	@Override
	public List<BaseOperation> checkAnGangOperations(BaseOperation oper) {
		if(!preCheckAnGang(oper)) return null;
		
		List<BaseOperation> result = new LinkedList<BaseOperation>();
		CardContainer cardContainer = player.getCardContainer();
		
		int almighty = desk.getEngine().getAlmightyCardNum();
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(cardContainer.getHandCards(), -1);
		for(Map.Entry<Integer, Integer> entry : card2count.entrySet()) {
			int count = entry.getValue();
			if(count != 4) continue;
			int cardNum = entry.getKey();
			if(cardNum == almighty && !desk.getSetting().getBool(CardGameSetting.ALMIGHTY_GANG)) continue;	// 不能杠万能牌
			AnGangOperation action = OperationFactory.createAnGangOperation(player, oper, cardContainer.findHandCard(cardNum));
			result.add(action);
		}
		return result.isEmpty()?null:result;
	}
	
	protected boolean preCheckAnGang(BaseOperation oper) {
		// 听后暗杠
		if(player.isBaoTing() && !desk.getSetting().getBool(CardGameSetting.TING_HOU_AN_GANG))  return false;
		
		switch(oper.getOperType()) {
		case OperType.MO :	// 摸后暗杠
			return player.equals(oper.getPlayer());
		case OperType.CHI :		// 吃后暗杠
			return player.equals(oper.getPlayer()) && desk.getSetting().getBool(CardGameSetting.CHI_HOU_AN_GANG);
		case OperType.PENG :	// 碰后暗杠
			return player.equals(oper.getPlayer()) && desk.getSetting().getBool(CardGameSetting.PENG_HOU_AN_GANG);
		}
		
		return false;
	}
	
	/*
	 * 检查并返回指定玩家的点杠操作
	 */	
	@Override
	public DianGangOperation checkDianGangOperation(BaseOperation daOper) {
		// 先检查该玩家是否具备杠的条件
		if(!preCheckDianGang(daOper)) return null;
		
		int cardNum = daOper.getTarget().getValue();
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(player.getCardContainer().getHandCards(), -1);
		
		// 检查手里的牌数
		int count = card2count.containsKey(cardNum)?card2count.get(cardNum):0;
		if (count < 3) {
			return null;
		}
		
		int almighty = desk.getEngine().getAlmightyCardNum();
		if(cardNum == almighty && !desk.getSetting().getBool(CardGameSetting.ALMIGHTY_GANG)) return null;	// 不能杠万能牌
		
		return OperationFactory.createDianGangOperation(player, daOper);		
	}
	
	protected boolean preCheckDianGang(BaseOperation oper) {
		// 听后接杠
		if(player.isBaoTing() && !desk.getSetting().getBool(CardGameSetting.TING_HOU_JIE_GANG))  return false;
		
		return (oper.checkOperType(OperType.DA) || oper.checkOperType(OperType.TING)) && !player.equals(oper.getPlayer());
	}
	
}
