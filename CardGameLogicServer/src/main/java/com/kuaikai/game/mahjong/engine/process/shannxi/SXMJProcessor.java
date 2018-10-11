package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;
import com.sy.mahjong.message.room.CreateRoomParam;

public class SXMJProcessor extends DefaultProcessor {
	
	/**
	 * 陕西麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		CreateRoomParam params = room.getCreateRoomParam();
		
		// 荒庄时庄家继续坐庄
		params.changeSetting(GameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 庄家翻倍
		//params.changeSetting(GameSetting.BANKER_DOUBLE, true);
		
		// 杠牌计分
		params.changeSetting(GameSetting.GANG_DE_FEN, true);
		
		// 陕西麻将下炮子
		int paoZi = params.getSettingInt(GameSetting.PAO_ZI);
		if(paoZi == 0) {	// 不带下炮子，庄六闲四：点炮者胡牌分数包三家，庄家输赢分数翻倍；点杠者包杠
			params.changeSetting(GameSetting.DIAN_PAO_PAY_ALL, true);
			params.changeSetting(GameSetting.DIAN_GANG_PAY_ALL, true);
		} else {
			// 点杠三家付
			params.changeSetting(GameSetting.DIAN_GANG_PAYER_ALL, true);
			if(paoZi < 0) {	// 自由炮时，开启下注
				params.changeSetting(GameSetting.XIA_ZHU, true);
			}
		}

		// 漏胡所有
		params.changeSetting(GameSetting.LOU_HU_NUM, -1);

	}
	
}
