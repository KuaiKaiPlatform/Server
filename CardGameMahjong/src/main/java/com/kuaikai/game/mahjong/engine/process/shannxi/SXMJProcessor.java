package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;

public class SXMJProcessor extends DefaultProcessor {
	
	/**
	 * 陕西麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		AttrsModel setting = desk.getSetting();
		
		// 荒庄时庄家继续坐庄
		setting.put(CardGameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 庄家翻倍
		//params.put(GameSetting.BANKER_DOUBLE, true);
		
		// 杠牌计分
		setting.put(CardGameSetting.GANG_DE_FEN, true);
		
		// 陕西麻将下炮子
		int paoZi = setting.getInt(CardGameSetting.PAO_ZI);
		if(paoZi == 0) {	// 不带下炮子，庄六闲四：点炮者胡牌分数包三家，庄家输赢分数翻倍；点杠者包杠
			setting.put(CardGameSetting.DIAN_PAO_PAY_ALL, true);
			setting.put(CardGameSetting.DIAN_GANG_PAY_ALL, true);
		} else {
			// 点杠三家付
			setting.put(CardGameSetting.DIAN_GANG_PAYER_ALL, true);
			if(paoZi < 0) {	// 自由炮时，开启下注
				setting.put(CardGameSetting.XIA_ZHU, true);
			}
		}

		// 漏胡所有
		setting.put(CardGameSetting.LOU_HU_NUM, -1);
		
		// 听所有牌时能点炮胡和抢杠胡
		setting.put(CardGameSetting.TING_ALL_DIAN, true);
		setting.put(CardGameSetting.TING_ALL_QIANG_GANG, true);
		
	}
	
}
