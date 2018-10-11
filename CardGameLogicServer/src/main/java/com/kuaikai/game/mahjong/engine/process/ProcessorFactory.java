package com.kuaikai.game.mahjong.engine.process;

import com.kuaikai.game.mahjong.engine.process.shannxi.GuoZiProcessor;
import com.kuaikai.game.mahjong.engine.process.shannxi.HanZhongProcessor;
import com.kuaikai.game.mahjong.engine.process.shannxi.HuaShuiProcessor;
import com.kuaikai.game.mahjong.engine.process.shannxi.LiangProcessor;
import com.kuaikai.game.mahjong.engine.process.shannxi.OneFiveNineProcessor;
import com.kuaikai.game.mahjong.engine.process.shannxi.SXMJProcessor;
import com.kuaikai.game.mahjong.engine.process.shannxi.WeiNanProcessor;
import com.sy.game.common.constants.GameRule;

public class ProcessorFactory {

	public static IMahjongProcessor createProcessor(int rule) {
		return createProcessor(GameRule.getRule(rule));
	}
	
	public static IMahjongProcessor createProcessor(GameRule rule) {
		IMahjongProcessor processor;
		switch(rule) {
		case HAN_ZHONG :
			processor = new HanZhongProcessor();
			break;
		case SXMJ :
			processor = new SXMJProcessor();
			break;
		case ONE_FIVE_NINE :
			processor = new OneFiveNineProcessor();
			break;
		case HUA_SHUI :
			processor = new HuaShuiProcessor();
			break;
		case WEI_NAN :
			processor = new WeiNanProcessor();
			break;
		case GUO_ZI :
			processor = new GuoZiProcessor();
			break;
		case LIANG :
			processor = new LiangProcessor();
			break;
		default :
			processor = new DefaultProcessor();
			break;
		}
		return processor;
	}
	
}
