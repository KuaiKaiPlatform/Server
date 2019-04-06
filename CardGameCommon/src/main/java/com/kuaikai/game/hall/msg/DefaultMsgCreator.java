package com.kuaikai.game.hall.msg;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.model.User;
import com.kuaikai.game.common.msg.pb.DeskInfoPB.DeskInfo;
import com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq;
import com.kuaikai.game.common.msg.pb.GameSettingPB.GameSetting;
import com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo;
import com.kuaikai.game.common.msg.pb.UserInfoPB.UserInfo;
import com.kuaikai.game.common.play.CardGameSetting;
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
		return builder
				.setUniq(DeskUniq.newBuilder().setDeskId(desk.getDeskId()).setClubId(desk.getClubId()))
				.setRule(desk.getRule())
				.setSetting(createGameSetting(desk))
				.setStatus(desk.getStatus())
				.setBankerId(desk.getBankerId())
				.setCurSet(desk.getCurSet());
	}
	
	protected PlayerInfo.Builder createPlayerInfo(Player p) {
		return PlayerInfo.newBuilder()
				.setUser(createUserInfo(p.getUser()))
				.setSeat(p.getSeat())
				.setPrepared(p.isPrepared())
				.setOffline(p.isOffline())
				.addAllPoints(p.getPoints())
				.setBet(p.getBet());
	}

	protected UserInfo.Builder createUserInfo(User u) {
		return UserInfo.newBuilder()
				.setUid(u.getId())
				.setNkn(u.getNickName())
				.setHead(u.getHead())
				.setSex(u.getSex());
	}
	
	protected GameSetting.Builder createGameSetting(Desk desk) {
		return GameSetting.newBuilder()
				.setJson(desk.getClubSetting().toJson())
				.setOperDelaySeconds(desk.getSetting().getInt(CardGameSetting.OPER_DELAY_SECONDS));
	}
	
	public SPlayerJoin.Builder createSPlayerJoin(Player p, Desk desk) {
		return SPlayerJoin.newBuilder()
				.setPlayer(createPlayerInfo(p))
				.setUniq(DeskUniq.newBuilder().setDeskId(desk.getDeskId()).setClubId(desk.getClubId()));
	}
	
}
