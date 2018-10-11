package com.kuaikai.game.mahjong.engine.checker.peng;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperationFactory;
import com.kuaikai.game.mahjong.engine.oper.PengOperation;
import com.kuaikai.game.mahjong.engine.paixin.PaiXinHelper;

/**
 * 默认的碰牌检查器
 * 
 */
public class DefaultPengChecker implements PengChecker {

	protected static final Logger logger = LoggerFactory.getLogger("play");
	protected final MahjongDesk room;
	protected final MahjongPlayer player;
	
	public DefaultPengChecker(MahjongPlayer player) {
		this.player = player;
		this.room = player.getRoom();
	}
	
	/*
	 * 检查并返回指定玩家的碰牌操作
	 */	
	@Override
	public PengOperation checkPengOperation(BaseOperation oper) {
		// 先检查该玩家是否具备碰牌的条件
		if(!preCheck(oper)) return null;
		
		int cardNum = oper.getTarget().getValue();
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(player.getMjPlayer().getCardContainer().getHandCards(), -1);
		// 检查手里的牌数
		int count = card2count.containsKey(cardNum)?card2count.get(cardNum):0;
		if (count < 2) {
			return null;
		}
		
		int almighty = room.getEngine().getAlmightyCardNum();
		if(cardNum == almighty && !room.getCreateRoomParam().getSettingBool(GameSetting.ALMIGHTY_PENG)) return null;	// 不能碰万能牌
		
		return OperationFactory.createPengOperation(player, oper);
	}
	
	@SuppressWarnings("unchecked")
	protected boolean preCheck(BaseOperation oper) {
		if(player.getMjPlayer().isBaoTing()) return false;														// 报听不再碰
		if(!room.getCreateRoomParam().getSettingBool(GameSetting.LOU_PENG)) return true;						// 无漏碰限制
		if(!player.getMjPlayer().containsAttr(MahjongPlayer.SetAttr.LOU_PENG_CARDS)) return true;					// 玩家当前不处于漏碰状态
		List<Integer> cards = (List<Integer>)player.getMjPlayer().getAttr(MahjongPlayer.SetAttr.LOU_PENG_CARDS);		// 不能碰的牌列表
		return !cards.contains(oper.getTarget().getValue());
	}

}
