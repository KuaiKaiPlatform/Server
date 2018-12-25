package com.kuaikai.game.hall.msg;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.hall.msg.pb.SDeskInfoPB.SDeskInfo;
import com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin;

public interface MsgCreator {

	public SDeskInfo.Builder createSDeskInfo(Desk desk);
	
	public SPlayerJoin.Builder createSPlayerJoin(Player p, Desk desk);
	
}
