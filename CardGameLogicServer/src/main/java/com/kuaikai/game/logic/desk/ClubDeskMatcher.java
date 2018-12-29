package com.kuaikai.game.logic.desk;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Club;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.redis.ClubDeskRedis;
import com.kuaikai.game.common.redis.ClubRedis;
import com.kuaikai.game.common.redis.LockRedis;

/**
 * 大众竞技场牌桌匹配
 * @author Alear
 *
 */
public class ClubDeskMatcher {
	
	private static final Logger logger = LoggerFactory.getLogger(ClubDeskMatcher.class);

	/**
	 * 匹配过程：
	 * 锁定竞技场；
	 * 找到大众竞技场当前牌桌，若存在并且为等待状态，加入牌桌。
	 * 否则增加一张当前牌桌并入座。
	 * 牌桌坐满后修改牌桌游戏状态为开始
	 * 返回，解锁竞技场。
	 * 
	 * @param clubId
	 * @param uid
	 */
	public static long match(int clubId, int uid) {
		long deskId = 0;
		// 锁定竞技场
		RLock rLockClub = LockRedis.getClubLock(clubId);
		rLockClub.lock();
		
		// 锁定竞技场当前牌桌
//		RLock rLockDesk = LockRedis.getClubDeskLock(clubId, club.getDeskId());
//		rLockDesk.lock();
		
		try {
			// 找到当前牌桌
			Club club = ClubRedis.getClub(clubId);
			Desk desk = ClubDeskRedis.getDesk(clubId, club.getDeskId());
			// 牌桌存在并且为等待状态
			if(desk.exists() && desk.checkStatus(GameStatus.WAITING)) {
				deskId = desk.getDeskId();
			} else {
				// 增加当前牌桌号
				deskId = ClubRedis.incrDeskId(clubId);
			}
			// 入座，牌桌坐满后修改牌桌游戏状态为开始
			int seat = ClubDeskRedis.putNextSeat(clubId, deskId, uid);
			logger.info("ClubDeskMatcher.match@seated|uid={}|clubId={}|deskId={}|seat={}", uid, clubId, deskId, seat);
/*			if(desk.isFull(seat)) {
				ClubDeskRedis.putAttr(clubId, deskId, ClubDeskRedis.FIELD_STATUS, String.valueOf(GameStatus.STARTING_VALUE));
			}*/
		} catch(Exception e) {
			logger.error("ClubDeskMatcher.match@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
//			rLockDesk.unlock();
			rLockClub.unlock();
		}
		return deskId;
		
	}
	
}
