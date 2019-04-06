package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;

public class OneFiveNineProcessor extends DefaultProcessor {
	
	/**
	 * 初始化时调用，改变房间设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		AttrsModel setting = desk.getSetting();
		
		setting.put(CardGameSetting.HU_TAKE_ZHUANG, true);			// 谁胡牌谁坐庄
		setting.put(CardGameSetting.QU_ZI, true);					// 去掉字牌
		setting.put(CardGameSetting.ZI_MO_ONLY, true);				// 只炸不胡
		setting.put(CardGameSetting.TING_ALL_QIANG_GANG, true);		// 听所有牌时可以抢杠胡
		setting.put(CardGameSetting.GANG_DE_FEN, true);				// 杠牌计分
		setting.put(CardGameSetting.DIAN_GANG_PAY_ALL, true);		// 点杠包三家
		setting.put(CardGameSetting.MULTIPLE_HU, true);				// 一炮多响，多人可同时抢杠胡
		setting.put(CardGameSetting.QIANG_GANG_PAY_ALL, true);		// 抢杠胡包三家
		
		// 自由炮时，开启下注
		int paoZi = setting.getInt(CardGameSetting.PAO_ZI);
		if(paoZi < 0) {
			setting.put(CardGameSetting.XIA_ZHU, true);
		}
	}
	
	/**
	 * 荒庄时最后一个摸牌的人坐庄
	 */
	@Override
	protected MahjongPlayer dingZhuangHuangZhuang() {
		BaseOperation lastMo = desk.getEngine().getOperManager().getLastDoneOperation(OperType.MO);
		return lastMo!=null?lastMo.getPlayer():null;
	}
	
	/**
	 * 返回荒庄时牌池剩余牌数
	 */
	public int getRemainCardNum() {
		return desk.getSetting().getInt(CardGameSetting.MA_159);
	}
	
}
