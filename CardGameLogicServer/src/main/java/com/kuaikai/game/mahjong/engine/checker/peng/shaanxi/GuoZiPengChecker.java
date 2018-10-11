package com.kuaikai.game.mahjong.engine.checker.peng.shaanxi;

import com.kuaikai.game.mahjong.engine.checker.peng.DefaultPengChecker;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;

public class GuoZiPengChecker extends DefaultPengChecker {

	public GuoZiPengChecker(MahjongPlayer player) {
		super(player);
	}

	@Override
	protected boolean preCheck(BaseOperation oper) {
		// 报听时打出的牌不可被碰，杠，胡
		if(oper.checkOperType(OperType.TING)) return false;
		
		return super.preCheck(oper);
	}
	
	
}
