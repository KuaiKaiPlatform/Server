package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;

public class WeiNanProcessor extends DefaultProcessor {
	
	/**
	 * 渭南麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		AttrsModel setting = desk.getSetting();
		
		// 自由炮时，开启下注
		int paoZi = setting.getInt(CardGameSetting.PAO_ZI);
		if(paoZi < 0) {
			setting.put(CardGameSetting.XIA_ZHU, true);
		}
		
		// 不带字牌
		setting.put(CardGameSetting.QU_ZI, true);
		
		// 一炮多响
		setting.put(CardGameSetting.MULTIPLE_HU, true);
		
		// 杠牌计分，点杠一家付，荒庄不荒杠，暗杠不翻倍
		setting.put(CardGameSetting.GANG_DE_FEN, true);
		setting.put(CardGameSetting.GANG_DE_FEN_HUANG_ZHUANG, true);
		setting.put(CardGameSetting.BASE_RATE_AN_GANG, setting.getInt(CardGameSetting.BASE_RATE));
	}
	
	/**
	 * 荒庄时最后一个摸牌的人坐庄
	 */
	@Override
	protected MahjongPlayer dingZhuangHuangZhuang() {
		BaseOperation lastMo = desk.getEngine().getOperManager().getLastDoneOperation(OperType.MO);
		return lastMo!=null?lastMo.getPlayer():null;
	}
	
}
