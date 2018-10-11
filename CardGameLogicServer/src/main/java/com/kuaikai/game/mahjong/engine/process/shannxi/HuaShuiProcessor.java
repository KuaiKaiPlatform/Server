package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;
import com.sy.mahjong.message.room.CreateRoomParam;

public class HuaShuiProcessor extends DefaultProcessor {
	
	/**
	 * 划水麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		CreateRoomParam params = room.getCreateRoomParam();
		
		// 荒庄时庄家继续坐庄
		params.changeSetting(GameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 杠牌计分，点杠者包杠
		params.changeSetting(GameSetting.GANG_DE_FEN, true);
		params.changeSetting(GameSetting.DIAN_GANG_PAY_ALL, true);
		
		// 点炮包三家
		params.changeSetting(GameSetting.DIAN_PAO_PAY_ALL, true);

		// 漏胡所有
		params.changeSetting(GameSetting.LOU_HU_NUM, -1);

	}
	
}
