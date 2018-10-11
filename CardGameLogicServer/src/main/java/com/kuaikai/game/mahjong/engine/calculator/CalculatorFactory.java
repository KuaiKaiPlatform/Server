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
	
	public static Calculator createCalculator(MahjongDesk room) {
		Calculator calculator = null;
		switch(room.getCreateRoomParam().getRule()) {
		case HAN_ZHONG :
			calculator = new HanZhongCalculator(room);
			break;
		case SXMJ :
			calculator = new SXMJCalculator(room);
			break;
		case ONE_FIVE_NINE :
			calculator = new OneFiveNineCalculator(room);
			break;
		case HUA_SHUI :
			calculator = new HuaShuiCalculator(room);
			break;
		case WEI_NAN :
			calculator = new WeiNanCalculator(room);
			break;
		case GUO_ZI :
			calculator = new GuoZiCalculator(room);
			break;
		case LIANG :
			calculator = new LiangCalculator(room);
			break;
		default :
			break;
		}
		return calculator;
	}
	
}
