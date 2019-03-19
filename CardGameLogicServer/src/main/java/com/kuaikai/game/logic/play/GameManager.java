package com.kuaikai.game.logic.play;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.utils.GameThreadPool;

public class GameManager {

	private static final Logger logger = LoggerFactory.getLogger(GameManager.class);
	
	public static void startAsync(GameDesk gameDesk) {
		GameThreadPool.getThreadPool().execute(new Runnable() {

			@Override
			public void run() {
				GameManager.start(gameDesk);
			}
			
		});
	}
	
	public static void start(GameDesk gameDesk) {
		Desk desk = gameDesk.getDesk();
		RLock rLockDesk = LockRedis.getClubDeskLock(desk.getClubId(), desk.getDeskId());
		rLockDesk.lock();
		
		try {
//			GameDesk gameDesk = GameDeskFactory.create(desk);
//			if(gameDesk == null) {
//				logger.warn("com.kuaikai.game.logic.play.GameManager.start@Unsupported game rule|desk={}", desk);
//				return;
//			}
//			
//			GameDeskManager.add(gameDesk);
			
//			GameDesk gameDesk = GameDeskManager.get(desk.getKey());
//			if(gameDesk == null) {
//				logger.error("com.kuaikai.game.logic.play.GameManager.start@game desk not found|desk={}", desk);
//				return;
//			}
			
			gameDesk.onGameStart(System.currentTimeMillis());
			
			gameDesk.getMessageSender().sendSSetInit(null);
			
/*			if(desk.canStart()) {
				String deskKey = desk.getKey();
				logger.debug("DeskManager.onUserLogin@can start desk={}", deskKey);
				try {
					// 通知牌局开始
					DeskStartEvent startEvent = new DeskStartEvent(deskKey);
					TriggerManager.triggerEvent(startEvent);
				} catch (Exception e) {
					logger.error("DeskManager.onUserLogin@desk start listener error|desk={}", desk, e);
				} finally {
					//rLock.unlock();
				}
			}*/
		} catch(Exception e) {
			logger.error("com.kuaikai.game.logic.play.GameManager.start@error|clubId={}|deskId={}", desk.getClubId(), desk.getDeskId(), e);
		} finally {
			rLockDesk.unlock();
		}
	}
	
}
