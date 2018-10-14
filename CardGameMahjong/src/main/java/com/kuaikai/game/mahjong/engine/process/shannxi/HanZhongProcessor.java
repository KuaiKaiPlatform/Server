package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.constants.ChairMode;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;

public class HanZhongProcessor extends DefaultProcessor {
	
	/**
	 * 汉中麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		AttrsModel setting = desk.getSetting();
		
		// 首局选座模式为 玩家选座
		setting.put(CardGameSetting.CHAIR_MODE, ChairMode.PLAYER.name());
		
		// 首局手动准备
		setting.put(CardGameSetting.MANUAL_READY_TO_BEGIN, true);
		
		// 荒庄时庄家继续坐庄
		setting.put(CardGameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 自由炮时，开启下注
		int paoZi = setting.getInt(CardGameSetting.PAO_ZI);
		if(paoZi < 0) {
			setting.put(CardGameSetting.XIA_ZHU, true);
		}
		
		// 杠牌计分，点杠三家付
		setting.put(CardGameSetting.GANG_DE_FEN, true);
		setting.put(CardGameSetting.DIAN_GANG_PAYER_ALL, true);
		
		// 不选可算番时，杠随胡走（胡牌才有杠分），点炮者支付所有杠分
		if(!setting.getBool(CardGameSetting.HAN_ZHONG_JIA_FAN)) {
			setting.put(CardGameSetting.GANG_SUI_HU, true);
			setting.put(CardGameSetting.DIAN_PAO_PAY_DIAN_GANG, true);
			setting.put(CardGameSetting.DIAN_PAO_PAY_OTHER_GANG, true);
		} else {
			// 可算番，荒庄不荒杠
			setting.put(CardGameSetting.GANG_DE_FEN_HUANG_ZHUANG, true);
		}
	}
	
}
