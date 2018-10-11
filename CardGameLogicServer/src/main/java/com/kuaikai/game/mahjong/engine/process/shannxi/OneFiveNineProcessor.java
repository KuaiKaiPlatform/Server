package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;
import com.sy.mahjong.message.room.CreateRoomParam;

public class OneFiveNineProcessor extends DefaultProcessor {
	
	/**
	 * 初始化时调用，改变房间设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		CreateRoomParam params = room.getCreateRoomParam();
		
		params.changeSetting(GameSetting.HU_TAKE_ZHUANG, true);			// 谁胡牌谁坐庄
		params.changeSetting(GameSetting.QU_ZI, true);					// 去掉字牌
		params.changeSetting(GameSetting.ZI_MO_ONLY, true);				// 只炸不胡
		params.changeSetting(GameSetting.TING_ALL_QIANG_GANG, true);	// 听所有牌时可以抢杠胡
		params.changeSetting(GameSetting.GANG_DE_FEN, true);			// 杠牌计分
		params.changeSetting(GameSetting.DIAN_GANG_PAY_ALL, true);		// 点杠包三家
		params.changeSetting(GameSetting.MULTIPLE_HU, true);			// 一炮多响，多人可同时抢杠胡
		params.changeSetting(GameSetting.QIANG_GANG_PAY_ALL, true);		// 抢杠胡包三家
		
		// 自由炮时，开启下注
		int paoZi = params.getSettingInt(GameSetting.PAO_ZI);
		if(paoZi < 0) {
			params.changeSetting(GameSetting.XIA_ZHU, true);
		}
	}
	
	/**
	 * 荒庄时最后一个摸牌的人坐庄
	 */
	@Override
	protected MahjongPlayer dingZhuangHuangZhuang() {
		BaseOperation lastMo = room.getEngine().getOperManager().getLastDoneOperation(OperType.MO);
		return lastMo!=null?lastMo.getPlayer():null;
	}
	
	/**
	 * 返回荒庄时牌池剩余牌数
	 */
	public int getRemainCardNum() {
		return room.getCreateRoomParam().getSettingInt(GameSetting.MA_159);
	}
	
}
