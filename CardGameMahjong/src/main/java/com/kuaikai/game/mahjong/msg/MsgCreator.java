package com.kuaikai.game.mahjong.msg;

import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit;

public interface MsgCreator {

	public SSetInit.Builder createSSetInit(MahjongDesk desk, GamePlayer receiver);
	
}
