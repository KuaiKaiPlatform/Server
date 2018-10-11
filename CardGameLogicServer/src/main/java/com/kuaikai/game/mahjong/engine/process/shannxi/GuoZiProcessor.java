package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;
import com.sy.mahjong.message.room.CreateRoomParam;

public class GuoZiProcessor extends DefaultProcessor {
	
	/**
	 * 榆林打锅子规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		CreateRoomParam params = room.getCreateRoomParam();
		
		// 荒庄时庄家继续坐庄
		params.changeSetting(GameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 牌库剩14张牌时还未有人胡牌则荒庄
		params.changeSetting(GameSetting.REMAIN_CARD_NUM, 14);
		
		// 可听牌，听牌后可补杠和暗杠
		params.changeSetting(GameSetting.KE_TING, true);
		params.changeSetting(GameSetting.TING_HOU_BU_GANG, true);
		params.changeSetting(GameSetting.TING_HOU_AN_GANG, true);
		
		// 漏胡所有
		params.changeSetting(GameSetting.LOU_HU_NUM, -1);
		
		// 点炮时默认三家付，未听牌则点炮者包三家
		params.changeSetting(GameSetting.DIAN_PAO_PAYER_ALL, true);
		params.changeSetting(GameSetting.QIANG_GANG_PAYER_ALL, true);
		
		// 杠牌计分，放杠包杠：点杠是否包三家由是否听牌决定；杠随胡走：不是胡牌才计杠分，而是未听牌的点炮者支付三家点杠分，客户端选项设置dian_pao_pay_dian_gang，其他情况杠分三家付
		params.changeSetting(GameSetting.GANG_DE_FEN, true);
		
	}
	
	/**
	 * 胡牌时定庄：庄家胡牌则继续坐庄，闲家胡牌，若未听牌的玩家点炮，则下轮坐庄
	 */
	protected MahjongPlayer dingZhuangHu() {
		// 庄家胡牌，继续坐庄
		if(room.getBanker().isHuPlayer()) {
			return room.getBanker();
		}

		// 若未听牌的玩家点炮，则下轮坐庄
		MahjongPlayer dianPlayer = room.getEngine().getCurrentSetResult().getDianPlayer();
		if(dianPlayer != null && !dianPlayer.getMjPlayer().isBaoTing()) {
			room.setBanker(dianPlayer);
			return dianPlayer;
		}
		
		return null;
	}
	
}
