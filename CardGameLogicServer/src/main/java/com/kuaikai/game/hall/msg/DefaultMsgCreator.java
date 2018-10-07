package com.kuaikai.game.hall.msg;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.model.User;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameSettingPB.GameSetting;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo;
import com.kuaikai.game.common.msg.pb.UserInfoPB.UserInfo;
import com.kuaikai.game.hall.msg.pb.SDeskInfoPB.SDeskInfo;
import com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin;

public class DefaultMsgCreator implements MsgCreator {

	public SDeskInfo.Builder createSDeskInfo(Desk desk) {
		SDeskInfo.Builder builder = SDeskInfo.newBuilder();
		for(Player p : desk.getPlayers()) {
			builder.addPlayerInfos(createPlayerInfo(p));
		}
		return builder.setRule(GameRule.GUO_ZI).setSetting(createGameSetting()).setStatus(GameStatus.WAITING);
	}
	
	protected PlayerInfo.Builder createPlayerInfo(Player p) {
		return PlayerInfo.newBuilder().setUserInfo(createUserInfo(p.getUser())).setSeat(p.getSeat())
				.setPrepared(true).addAllPoints(p.getPoints());
	}

	protected UserInfo.Builder createUserInfo(User u) {
		return UserInfo.newBuilder().setUid(u.getId()).setNkn(u.getNickName());
	}
	
	protected GameSetting.Builder createGameSetting() {
		return GameSetting.newBuilder().setTotalSet(8);
	}
	
	public SPlayerJoin.Builder createSPlayerJoin(Player p, Desk desk) {
		return SPlayerJoin.newBuilder().setPlayerInfo(createPlayerInfo(p)).setDeskId(desk.getDeskId());
	}
	
}
