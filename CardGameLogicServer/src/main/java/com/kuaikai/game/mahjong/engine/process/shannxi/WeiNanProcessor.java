package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;
import com.sy.mahjong.message.room.CreateRoomParam;

public class WeiNanProcessor extends DefaultProcessor {
	
	/**
	 * 渭南麻将规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		CreateRoomParam params = room.getCreateRoomParam();
		
		// 自由炮时，开启下注
		int paoZi = params.getSettingInt(GameSetting.PAO_ZI);
		if(paoZi < 0) {
			params.changeSetting(GameSetting.XIA_ZHU, true);
		}
		
		// 不带字牌
		params.changeSetting(GameSetting.QU_ZI, true);
		
		// 一炮多响
		params.changeSetting(GameSetting.MULTIPLE_HU, true);
		
		// 杠牌计分，点杠一家付，荒庄不荒杠，暗杠不翻倍
		params.changeSetting(GameSetting.GANG_DE_FEN, true);
		params.changeSetting(GameSetting.GANG_DE_FEN_HUANG_ZHUANG, true);
		params.changeSetting(GameSetting.BASE_RATE_AN_GANG, params.getSettingInt(GameSetting.BASE_RATE));
	}
	
	/**
	 * 荒庄时最后一个摸牌的人坐庄
	 */
	@Override
	protected MahjongPlayer dingZhuangHuangZhuang() {
		BaseOperation lastMo = room.getEngine().getOperManager().getLastDoneOperation(OperType.MO);
		return lastMo!=null?lastMo.getPlayer():null;
	}
	
}
