package com.kuaikai.game.common.redis;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.redisson.api.RLock;

import com.kuaikai.game.common.db.RedissonManager;

public class LockRedis {
	
	public static final String USER_LOCK		= "user.lock.%d";
	public static final String CLUB_LOCK		= "club.lock.%d";
	public static final String CLUB_DESK_LOCK	= "club.desk.lock.%d.%d";
	
	/**
	 * 返回玩家锁
	 */
	public static RLock getUserLock(int uid) {
		String key = String.format(USER_LOCK, uid);
		return RedissonManager.getRedission().getLock(key);
	}

	/**
	 * 返回一组排序后的玩家锁
	 */
	public static List<RLock> getUserLocks(List<Integer> uids) {
		Collections.sort(uids);
		List<RLock> locks = new LinkedList<RLock>();
		for (int uid : uids) {
			locks.add(getUserLock(uid));
		}
		return locks;
	}

	/**
	 * 返回俱乐部锁
	 */
	public static RLock getClubLock(int clubId) {
		String key = String.format(CLUB_LOCK, clubId);
		return RedissonManager.getRedission().getLock(key);
	}
	
	/**
	 * 返回俱乐部牌桌锁
	 */
	public static RLock getClubDeskLock(int clubId, long deskId) {
		String key = String.format(CLUB_DESK_LOCK, clubId, deskId);
		return RedissonManager.getRedission().getLock(key);
	}
	
}
