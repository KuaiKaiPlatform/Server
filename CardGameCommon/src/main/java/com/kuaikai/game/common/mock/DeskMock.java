package com.kuaikai.game.common.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.model.User;
import com.kuaikai.game.common.play.CardGameSetting;

public class DeskMock {
	
	private static final Logger logger = LoggerFactory.getLogger(DeskMock.class);
	
	private static Map<Integer, Integer> uid2deskId = new ConcurrentHashMap<Integer, Integer>();
	private static Map<String, Desk> desks = new ConcurrentHashMap<String, Desk>();
	private static AtomicInteger deskIdInc = new AtomicInteger();
	
	private static final int CLUB_ID = 1;
	
	public static Desk joinDesk(int uid) {
		// 已经在牌桌中
		Integer deskId = uid2deskId.get(uid);
		Desk desk = (deskId != null)?desks.get(deskId + "-" + CLUB_ID):null;
		if(desk != null) return desk;
		
		// 最后一桌
		desk = desks.get(deskIdInc.get() + "-" + CLUB_ID);
		if(desk == null || !desk.canJoin()) {
			// 新建一桌
			deskId = deskIdInc.incrementAndGet();
			desk = createDesk(deskId);
			desks.put(desk.getKey(), desk);
		}
		
		desk.addPlayer(createPlayer(uid, desk.getPids().size()+1));
		uid2deskId.put(uid, desk.getDeskId());
		return desk;
	}
	
	private static Desk createDesk(int deskId) {
		Desk desk = new Desk(deskId);
		desk.setClubId(CLUB_ID);
		desk.getSetting().put(CardGameSetting.TOTAL_SET, 8).put(CardGameSetting.TOTAL_PLAYER, 4).put(CardGameSetting.MIN_PLAYER, 4);
		return desk;
	}
	
	private static Player createPlayer(int uid, int seat) {
		Player p = new Player();
		User u = new User();
		u.setId(uid);
		u.setNickName(String.valueOf(uid));
		p.setUser(u);
		p.addPoint(6);
		p.addPoint(3);
		p.setSeat(seat);
		p.setPrepared(true);
		return p;
	}
	
	public static Desk getDesk(String deskKey) {
		return desks.get(deskKey);
	}
	
}
