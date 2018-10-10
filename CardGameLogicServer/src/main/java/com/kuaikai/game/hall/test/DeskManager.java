package com.kuaikai.game.hall.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.desk.DeskStartEvent;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.redis.DeskRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.hall.msg.DefaultMsgCreator;
import com.kuaikai.game.hall.msg.MsgCreator;
import com.kuaikai.game.hall.msg.MsgId;

public class DeskManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeskManager.class);
	
	private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private static MsgCreator msgCreator;
	
	public static void init() {
		msgCreator = new DefaultMsgCreator();
	}
	
	public static void onUserLogin(int uid) {
		readWriteLock.writeLock().lock();
		try {
			Desk desk = DeskRedis.joinDesk(uid);
			OnlineManager.sendMsg(uid, new CommonMsgHandler(MsgId.SDeskInfo, msgCreator.createSDeskInfo(desk).build()));
			OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SPlayerJoin, msgCreator.createSPlayerJoin(desk.getPlayerById(uid), desk).build()), uid);
			if(desk.isFull()) {
				String deskKey = desk.getKey();
				try {
					// 通知牌局开始
					DeskStartEvent startEvent = new DeskStartEvent(deskKey);
					TriggerManager.triggerEvent(startEvent);
				} catch (Exception e) {
					LOGGER.error("DeskManager.onUserLogin@desk start listener error|desk={}", desk, e);
				} finally {
					//rLock.unlock();
				}
			}
		} catch(Exception e) {
			LOGGER.error("DeskManager.onUserLogin@error|uid={}", uid, e);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
}
