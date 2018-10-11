package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class MenQianQing {
	
	/*
	 * 检查是否符合门前清牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum, MahjongPlayer player) {
		if(groupList == null || groupList.isEmpty()) return true;
		
		boolean keAnGang = !player.getRoom().getCreateRoomParam().getSettingBool(GameSetting.MEN_QIAN_QING_NOT_AN_GANG);	// 允许暗杠
		for(CardGroup group : groupList) {
			if(!group.checkOperType(OperType.AN_GANG))	// 不是暗杠
				return false;
			else if(!keAnGang)	// 是暗杠，但不允许
				return false;
		}
		return true;
	}
	
}
