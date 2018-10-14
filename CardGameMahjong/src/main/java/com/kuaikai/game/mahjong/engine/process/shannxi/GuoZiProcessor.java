package com.kuaikai.game.mahjong.engine.process.shannxi;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.process.DefaultProcessor;

public class GuoZiProcessor extends DefaultProcessor {
	
	/**
	 * 榆林打锅子规则设置
	 */
	@Override
	protected void initSetting() {
		super.initSetting();
		
		AttrsModel setting = desk.getSetting();
		
		// 荒庄时庄家继续坐庄
		setting.put(CardGameSetting.HUANG_ZHUANG_CONTINUE, true);
		
		// 牌库剩14张牌时还未有人胡牌则荒庄
		setting.put(CardGameSetting.REMAIN_CARD_NUM, 14);
		
		// 可听牌，听牌后可补杠和暗杠
		setting.put(CardGameSetting.KE_TING, true);
		setting.put(CardGameSetting.TING_HOU_BU_GANG, true);
		setting.put(CardGameSetting.TING_HOU_AN_GANG, true);
		
		// 漏胡所有
		setting.put(CardGameSetting.LOU_HU_NUM, -1);
		
		// 点炮时默认三家付，未听牌则点炮者包三家
		setting.put(CardGameSetting.DIAN_PAO_PAYER_ALL, true);
		setting.put(CardGameSetting.QIANG_GANG_PAYER_ALL, true);
		
		// 杠牌计分，放杠包杠：点杠是否包三家由是否听牌决定；杠随胡走：不是胡牌才计杠分，而是未听牌的点炮者支付三家点杠分，客户端选项设置dian_pao_pay_dian_gang，其他情况杠分三家付
		setting.put(CardGameSetting.GANG_DE_FEN, true);
		
	}
	
	/**
	 * 胡牌时定庄：庄家胡牌则继续坐庄，闲家胡牌，若未听牌的玩家点炮，则下轮坐庄
	 */
	protected MahjongPlayer dingZhuangHu() {
		// 庄家胡牌，继续坐庄
		if(desk.getBanker().isHuPlayer()) {
			return desk.getBanker();
		}

		// 若未听牌的玩家点炮，则下轮坐庄
		MahjongPlayer dianPlayer = desk.getEngine().getCurrentSetResult().getDianPlayer();
		if(dianPlayer != null && !dianPlayer.isBaoTing()) {
			desk.setBanker(dianPlayer);
			return dianPlayer;
		}
		
		return null;
	}
	
}
