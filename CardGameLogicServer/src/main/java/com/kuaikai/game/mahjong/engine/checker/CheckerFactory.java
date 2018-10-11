package com.kuaikai.game.mahjong.engine.checker;

import com.kuaikai.game.mahjong.engine.checker.chi.ChiChecker;
import com.kuaikai.game.mahjong.engine.checker.chi.DefaultChiChecker;
import com.kuaikai.game.mahjong.engine.checker.gang.DefaultGangChecker;
import com.kuaikai.game.mahjong.engine.checker.gang.GangChecker;
import com.kuaikai.game.mahjong.engine.checker.gang.shaanxi.GuoZiGangChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.DefaultHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.HuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.shaanxi.GuoZiHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.shaanxi.HanZhongHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.shaanxi.HuaShuiHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.shaanxi.LiangHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.shaanxi.OneFiveNineHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.shaanxi.SXMJHuChecker;
import com.kuaikai.game.mahjong.engine.checker.hu.shaanxi.WeiNanHuChecker;
import com.kuaikai.game.mahjong.engine.checker.peng.DefaultPengChecker;
import com.kuaikai.game.mahjong.engine.checker.peng.PengChecker;
import com.kuaikai.game.mahjong.engine.checker.peng.shaanxi.GuoZiPengChecker;
import com.kuaikai.game.mahjong.engine.checker.ting.DefaultTingChecker;
import com.kuaikai.game.mahjong.engine.checker.ting.TingChecker;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class CheckerFactory {
	
	public static ChiChecker createChiChecker(MahjongPlayer player) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new DefaultChiChecker(player);
		}
	}
	
	public static PengChecker createPengChecker(MahjongPlayer player) {
		switch(player.getGameDesk().getRule()) {
		case GUO_ZI :
			return new GuoZiPengChecker(player);
		default :
			return new DefaultPengChecker(player);
		}
	}

	public static TingChecker createTingChecker(MahjongPlayer player) {
		switch(player.getGameDesk().getRule()) {
		default :
			return new DefaultTingChecker(player);
		}
	}
	
	public static GangChecker createGangChecker(MahjongPlayer player) {
		switch(player.getGameDesk().getRule()) {
		case GUO_ZI :
			return new GuoZiGangChecker(player);
		default :
			return new DefaultGangChecker(player);
		}
	}
	
	public static HuChecker createHuChecker(MahjongPlayer player) {
		switch(player.getGameDesk().getRule()) {
		case HAN_ZHONG :
			return new HanZhongHuChecker(player);
		case SXMJ :
			return new SXMJHuChecker(player);
		case ONE_FIVE_NINE :
			return new OneFiveNineHuChecker(player);
		case HUA_SHUI :
			return new HuaShuiHuChecker(player);
		case WEI_NAN :
			return new WeiNanHuChecker(player);
		case GUO_ZI :
			return new GuoZiHuChecker(player);
		case LIANG :
			return new LiangHuChecker(player);
		default :
			return new DefaultHuChecker(player);
		}
	}
	
}
