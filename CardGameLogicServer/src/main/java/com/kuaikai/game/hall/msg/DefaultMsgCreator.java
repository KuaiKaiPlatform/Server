package com.kuaikai.game.hall.msg;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.model.User;
import com.kuaikai.game.common.msg.pb.DeskInfoPB.DeskInfo;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameSettingPB.GameSetting;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo;
import com.kuaikai.game.common.msg.pb.UserInfoPB.UserInfo;
import com.kuaikai.game.hall.msg.pb.SDeskInfoPB.SDeskInfo;
import com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin;

public class DefaultMsgCreator implements MsgCreator {

	public SDeskInfo.Builder createSDeskInfo(Desk desk) {
		return SDeskInfo.newBuilder().setDesk(createDeskInfo(desk));
	}
	
	public DeskInfo.Builder createDeskInfo(Desk desk) {
		DeskInfo.Builder builder = DeskInfo.newBuilder();
		for(Player p : desk.getPlayers()) {
			builder.addPlayers(createPlayerInfo(p));
		}
		return builder.setDeskId(desk.getDeskId()).setRule(GameRule.GUO_ZI).setSetting(createGameSetting()).setStatus(GameStatus.WAITING);
	}
	
	protected PlayerInfo.Builder createPlayerInfo(Player p) {
		return PlayerInfo.newBuilder().setUser(createUserInfo(p.getUser())).setSeat(p.getSeat())
				.setPrepared(true).addAllPoints(p.getPoints());
	}

	protected UserInfo.Builder createUserInfo(User u) {
		return UserInfo.newBuilder().setUid(u.getId()).setNkn(u.getNickName());
	}
	
	protected GameSetting.Builder createGameSetting() {
		return GameSetting.newBuilder().setTotalSet(8);
	}
	
	public SPlayerJoin.Builder createSPlayerJoin(Player p, Desk desk) {
		return SPlayerJoin.newBuilder().setPlayer(createPlayerInfo(p)).setDeskId(desk.getDeskId());
	}
	
}
