package com.kuaikai.game.hall.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.User;
import com.kuaikai.game.common.model.ClubDesk;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.hall.msg.DefaultMsgCreator;
import com.kuaikai.game.hall.msg.MsgCreator;
import com.kuaikai.game.mahjong.msg.handler.SDeskInfoHandler;
import com.kuaikai.game.mahjong.msg.handler.SPlayerJoinHandler;

public class DeskManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeskManager.class);
	
	private static Map<Integer, Integer> uid2deskId = new ConcurrentHashMap<Integer, Integer>();
	private static Map<Integer, Desk> desks = new ConcurrentHashMap<Integer, Desk>();
	private static AtomicInteger deskIdInc = new AtomicInteger();
	
	private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private static final long CLUB_ID = 1;
	
	private static MsgCreator msgCreator;
	
	public static void init() {
		msgCreator = new DefaultMsgCreator();
	}
	
	public static void onUserLogin(int uid) {
		readWriteLock.writeLock().lock();
		try {
			Desk desk = joinDesk(uid);
			OnlineManager.sendMsg(uid, new SDeskInfoHandler(msgCreator.createSDeskInfo(desk)));
			OnlineManager.sendToAll(desk.getPids(), new SPlayerJoinHandler(msgCreator.createSPlayerJoin(desk.getPlayerById(uid), desk)), uid);
		} catch(Exception e) {
			LOGGER.error("DeskManager.onUserLogin@error|uid={}", uid, e);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	private static Desk joinDesk(int uid) {
		// 已经在牌桌中
		Integer deskId = uid2deskId.get(uid);
		Desk desk = (deskId != null)?desks.get(deskId):null;
		if(desk != null) return desk;
		
		// 最后一桌
		desk = desks.get(deskIdInc.get());
		if(desk == null || !desk.canJoin()) {
			// 新建一桌
			deskId = deskIdInc.incrementAndGet();
			desk = new ClubDesk(CLUB_ID, deskId);
			desks.put(deskId, desk);
		}
		
		desk.addPlayer(createPlayer(uid, desk.getPids().size()+1));
		uid2deskId.put(uid, desk.getDeskId());
		return desk;
	}
	
	private static Player createPlayer(int uid, int seat) {
		Player p = new Player();
		User u = new User();
		u.setId(uid);
		u.setNickName(String.valueOf(uid));
		p.setUser(u);
		p.addPoint(6);
		p.setSeat(seat);
		return p;
	}
	
}
