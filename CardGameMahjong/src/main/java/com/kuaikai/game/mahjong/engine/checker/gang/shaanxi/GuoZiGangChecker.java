package com.kuaikai.game.mahjong.engine.checker.gang.shaanxi;

import com.kuaikai.game.mahjong.engine.checker.gang.DefaultGangChecker;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;

public class GuoZiGangChecker extends DefaultGangChecker {

	public GuoZiGangChecker(MahjongPlayer player) {
		super(player);
	}

	@Override
	protected boolean preCheckDianGang(BaseOperation oper) {
		// 报听时打出的牌不可被碰，杠，胡
		if(oper.checkOperType(OperType.TING)) return false;
		
		return super.preCheckDianGang(oper);
	}
	
}
