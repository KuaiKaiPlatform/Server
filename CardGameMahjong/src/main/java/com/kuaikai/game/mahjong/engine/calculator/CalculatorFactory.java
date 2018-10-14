package com.kuaikai.game.mahjong.engine.calculator;

import com.kuaikai.game.mahjong.engine.calculator.shannxi.GuoZiCalculator;
import com.kuaikai.game.mahjong.engine.calculator.shannxi.HanZhongCalculator;
import com.kuaikai.game.mahjong.engine.calculator.shannxi.HuaShuiCalculator;
import com.kuaikai.game.mahjong.engine.calculator.shannxi.LiangCalculator;
import com.kuaikai.game.mahjong.engine.calculator.shannxi.OneFiveNineCalculator;
import com.kuaikai.game.mahjong.engine.calculator.shannxi.SXMJCalculator;
import com.kuaikai.game.mahjong.engine.calculator.shannxi.WeiNanCalculator;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;

public class CalculatorFactory {
	
	public static Calculator createCalculator(MahjongDesk desk) {
		Calculator calculator = null;
		switch(desk.getRule()) {
		case HAN_ZHONG :
			calculator = new HanZhongCalculator(desk);
			break;
		case SXMJ :
			calculator = new SXMJCalculator(desk);
			break;
		case ONE_FIVE_NINE :
			calculator = new OneFiveNineCalculator(desk);
			break;
		case HUA_SHUI :
			calculator = new HuaShuiCalculator(desk);
			break;
		case WEI_NAN :
			calculator = new WeiNanCalculator(desk);
			break;
		case GUO_ZI :
			calculator = new GuoZiCalculator(desk);
			break;
		case LIANG :
			calculator = new LiangCalculator(desk);
			break;
		default :
			break;
		}
		return calculator;
	}
	
}
