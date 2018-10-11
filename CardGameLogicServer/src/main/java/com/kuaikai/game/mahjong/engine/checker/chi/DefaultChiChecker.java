package com.kuaikai.game.mahjong.engine.checker.chi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperationFactory;
import com.kuaikai.game.mahjong.engine.paixin.PaiXinHelper;

public class DefaultChiChecker implements ChiChecker {

	protected static final Logger logger = LoggerFactory.getLogger("play");
	protected final MahjongDesk desk;
	protected final MahjongPlayer player;
	
	public DefaultChiChecker(MahjongPlayer player) {
		this.player = player;
		this.desk = player.getGameDesk();
	}
	
	/*
	 * 检查并返回指定玩家的吃牌操作
	 */	
	@Override
	public List<BaseOperation> checkChiOperations(BaseOperation oper) {
		// 先检查该玩家是否具备吃牌的条件
		if(!preCheck(oper)) return null;
		
		// 分析可吃的顺子
		List<List<MJCard>> shunZis = checkShunZis(oper);
		if(shunZis == null || shunZis.isEmpty()) return null;
		
		//if(logger.isDebugEnabled()) logger.debug(player.getLogPrefix().append(";shunZis=").append(shunZis).toString());
		
		// 生成 ChiAction
		List<BaseOperation> result = new ArrayList<BaseOperation>();
		for(List<MJCard> shunZi : shunZis) {
			result.add(OperationFactory.createChiOperation(player, oper, shunZi));
		}
		return result;
	}
	
	protected boolean preCheck(BaseOperation oper) {
		return !player.isBaoTing();
	}

	@Override
	public List<List<MJCard>> checkShunZis(BaseOperation oper) {
		MJCard target = oper.getTarget();
		int card = target.getValue();
		if(!target.isWanTiaoTong()) return null;
		if(target.isAlmighty() && !desk.getSetting().getBool(GameSetting.ALMIGHTY_CHI)) return null;	// 不能吃万能牌
		
		int card2l = Mahjong.getDeltaCardNum(card, -2);
		int card1l = Mahjong.getDeltaCardNum(card, -1);
		int card1g = Mahjong.getDeltaCardNum(card, 1);
		int card2g = Mahjong.getDeltaCardNum(card, 2);
		
		List<List<MJCard>> shunZis = new LinkedList<List<MJCard>>();
		// 万能牌还原可吃时，不转为100
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(player.getMjPlayer().getCardContainer().getHandCards(),
				desk.getCreateRoomParam().getSettingBool(GameSetting.ALMIGHTY_CHI)?-1:desk.getEngine().getAlmightyCardNum());

		if(containsBoth(card2count, card2l, card1l)) shunZis.add(createArr(card2l, card1l));
		if(containsBoth(card2count, card1l, card1g)) shunZis.add(createArr(card1l, card1g));
		if(containsBoth(card2count, card1g, card2g)) shunZis.add(createArr(card1g, card2g));
		return shunZis;
	}

	private boolean containsBoth(Map<Integer, Integer> card2count, int card1, int card2) {
		return card2count.containsKey(card1) && card2count.containsKey(card2);
	}
	
	private List<MJCard> createArr(int card1, int card2) {
		List<MJCard> list = new ArrayList<MJCard>();
		list.add(player.getMjPlayer().getCardContainer().findHandCard(card1));
		list.add(player.getMjPlayer().getCardContainer().findHandCard(card2));
		return list;
	}
	
}
