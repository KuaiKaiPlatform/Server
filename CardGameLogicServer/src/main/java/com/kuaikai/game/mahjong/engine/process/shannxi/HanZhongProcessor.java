package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.mahjong.engine.constants.ChairMode;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;
import com.sy.mahjong.message.room.CreateRoomParam;

public class HanZhongProcessor extends DefaultProcessor {
	
	/**
	 * 汉中麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		CreateRoomParam params = room.getCreateRoomParam();
		
		// 首局选座模式为 玩家选座
		params.changeSetting(GameSetting.CHAIR_MODE, ChairMode.PLAYER.name());
		
		// 首局手动准备
		params.changeSetting(GameSetting.MANUAL_READY_TO_BEGIN, true);
		
		// 荒庄时庄家继续坐庄
		params.changeSetting(GameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 自由炮时，开启下注
		int paoZi = params.getSettingInt(GameSetting.PAO_ZI);
		if(paoZi < 0) {
			params.changeSetting(GameSetting.XIA_ZHU, true);
		}
		
		// 杠牌计分，点杠三家付
		params.changeSetting(GameSetting.GANG_DE_FEN, true);
		params.changeSetting(GameSetting.DIAN_GANG_PAYER_ALL, true);
		
		// 不选可算番时，杠随胡走（胡牌才有杠分），点炮者支付所有杠分
		if(!params.getSettingBool(GameSetting.HAN_ZHONG_JIA_FAN)) {
			params.changeSetting(GameSetting.GANG_SUI_HU, true);
			params.changeSetting(GameSetting.DIAN_PAO_PAY_DIAN_GANG, true);
			params.changeSetting(GameSetting.DIAN_PAO_PAY_OTHER_GANG, true);
		} else {
			// 可算番，荒庄不荒杠
			params.changeSetting(GameSetting.GANG_DE_FEN_HUANG_ZHUANG, true);
		}
	}
	
}
