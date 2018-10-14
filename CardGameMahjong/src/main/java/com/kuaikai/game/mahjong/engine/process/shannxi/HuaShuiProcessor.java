package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;

public class HuaShuiProcessor extends DefaultProcessor {
	
	/**
	 * 划水麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		AttrsModel setting = desk.getSetting();
		
		// 荒庄时庄家继续坐庄
		setting.put(CardGameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 杠牌计分，点杠者包杠
		setting.put(CardGameSetting.GANG_DE_FEN, true);
		setting.put(CardGameSetting.DIAN_GANG_PAY_ALL, true);
		
		// 点炮包三家
		setting.put(CardGameSetting.DIAN_PAO_PAY_ALL, true);

		// 漏胡所有
		setting.put(CardGameSetting.LOU_HU_NUM, -1);

	}
	
}
