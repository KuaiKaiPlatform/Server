package com.kuaikai.game.common.redis;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.redisson.api.RLock;

import com.kuaikai.game.common.db.RedissonManager;

public class LockRedis {
	
	public static final String USER_LOCK = "user.lock.%d";
	
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

}
