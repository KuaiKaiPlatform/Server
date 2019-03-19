package com.kuaikai.game.mahjong.msg;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.msg.pb.SCanOperPB.SCanOper;
import com.kuaikai.game.mahjong.msg.pb.SGameResultPB.SGameResult;
import com.kuaikai.game.mahjong.msg.pb.SOperCardPB.SOperCard;
import com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit;
import com.kuaikai.game.mahjong.msg.pb.SSetResultPB.SSetResult;

public interface MsgCreator {

	public SSetInit.Builder createSSetInit(MahjongDesk desk, MahjongPlayer receiver);

	public SOperCard.Builder createSOperCard(MahjongDesk desk, MahjongPlayer receiver);
	
	public SOperCard.Builder createSOperCard(BaseOperation oper);
	
	public SCanOper.Builder createSCanOper(MahjongDesk desk, MahjongPlayer receiver, List<BaseOperation> canExecuteOperations);
	
	public SSetResult.Builder createSSetResult(MahjongDesk desk, boolean over);
	
	public SGameResult.Builder createSGameResult(MahjongDesk desk, boolean dismiss);
	
}
