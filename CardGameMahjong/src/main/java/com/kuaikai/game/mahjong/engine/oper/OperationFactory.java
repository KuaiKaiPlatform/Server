package com.kuaikai.game.mahjong.engine.oper;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.shannxi.LiangDaOperation;
import com.kuaikai.game.mahjong.engine.oper.shannxi.LiangTingOperation;

public class OperationFactory {

	public static MoOperation createMoOperation(MahjongPlayer player, BaseOperation preOperation) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new MoOperation(player, preOperation);
		}
	}

	public static DaOperation createDaOperation(MahjongPlayer player, BaseOperation preOperation) {
		switch(player.getGameDesk().getRule()) {
		case LIANG :
			return new LiangDaOperation(player, preOperation);
		default :
			return new DaOperation(player, preOperation);
		}
	}

	public static ChiOperation createChiOperation(MahjongPlayer player, BaseOperation preOperation, List<MJCard> cards) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new ChiOperation(player, preOperation, cards);
		}
	}
	
	public static PengOperation createPengOperation(MahjongPlayer player, BaseOperation preOperation) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new PengOperation(player, preOperation);
		}
	}

	public static AnGangOperation createAnGangOperation(MahjongPlayer player, BaseOperation preOperation, MJCard target) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new AnGangOperation(player, preOperation, target);
		}
	}

	public static BuGangOperation createBuGangOperation(MahjongPlayer player, BaseOperation preOperation, CardGroup pengGroup, MJCard target) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new BuGangOperation(player, preOperation, pengGroup, target);
		}
	}

	public static DianGangOperation createDianGangOperation(MahjongPlayer player, BaseOperation preOperation) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new DianGangOperation(player, preOperation);
		}
	}

	public static HuOperation createHuOperation(MahjongPlayer player, BaseOperation preOper) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new HuOperation(player, preOper);
		}
	}
	
	public static TingOperation createTingOperation(MahjongPlayer player, BaseOperation preOperation, Map<Integer, DiscardTingCards> discard2TingCards) {
		switch(player.getGameDesk().getRule()) {
		case LIANG :
			return new LiangTingOperation(player, preOperation, discard2TingCards);
		default :
			return new TingOperation(player, preOperation, discard2TingCards);
		}
	}
	
}
