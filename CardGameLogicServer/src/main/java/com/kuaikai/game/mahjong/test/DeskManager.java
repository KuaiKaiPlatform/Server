package com.kuaikai.game.mahjong.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.mahjong.msg.handler.SJoinDeskHandler;
import com.kuaikai.game.mahjong.msg.pb.DirectionPB;
import com.kuaikai.game.mahjong.msg.pb.JoinDeskPB.SJoinDesk;
import com.kuaikai.game.mahjong.msg.pb.PlayerInfoPB.PlayerInfo;

public class DeskManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeskManager.class);
	
	private static Map<Integer, Integer> uid2deskId = new ConcurrentHashMap<Integer, Integer>();
	private static Map<Integer, Desk> desks = new ConcurrentHashMap<Integer, Desk>();
	private static Desk currentDesk;
	private static AtomicInteger deskIdInc = new AtomicInteger();
	
	private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public static void onUserLogin(int uid) {
		readWriteLock.writeLock().lock();
		try {
			Desk desk = joinDesk(uid);
			SJoinDesk.Builder builder = createSJoinDesk(uid, desk);
			OnlineManager.sendMsg(uid, new SJoinDeskHandler(builder));
		} catch(Exception e) {
			LOGGER.error("DeskManager.onUserLogin@error|uid={}", uid, e);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	public static Desk joinDesk(int uid) {
		Integer deskId = uid2deskId.get(uid);
		Desk desk = (deskId != null)?desks.get(deskId):null;
		if(desk != null) return desk;
		
		if(currentDesk == null) {
			deskId = deskIdInc.incrementAndGet();
			currentDesk = new Desk(deskId);
			desks.put(deskId, currentDesk);
		} else {
			deskId = currentDesk.getId();
		}
		
		currentDesk.addPlayer(uid);
		uid2deskId.put(uid, deskId);
		desk = currentDesk;
		
		if(currentDesk.isFull()) {
			currentDesk = null;
		}
		return desk;
	}
	
	private static SJoinDesk.Builder createSJoinDesk(int uid, Desk desk) {
		SJoinDesk.Builder builder = SJoinDesk.newBuilder();
		for(int i=0; i < desk.getPids().size(); i++) {
			builder.addPlayerInfos(createPlayerInfo(desk.getPids().get(i), i+1));
		}
		return builder;
	}
	
	private static PlayerInfo.Builder createPlayerInfo(int pid, int index) {
		PlayerInfo.Builder builder = PlayerInfo.newBuilder();
		builder.setDirection(DirectionPB.Direction.valueOf(index));
		return builder;
	}
	
}
