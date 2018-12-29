package com.kuaikai.game.common.redis;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.utils.CollectionUtils;

/**
 *   玩家竞技场相关记录
 * @author Alear
 *
 */
public class PlayerArenaRedis {

	public static final String USER_DESK		= "user.desk.%d";			// 玩家入座或快速开始的竞技场编号和桌号
	public static final String FIELD_CLUB_ID	= "clubId";
	public static final String FIELD_DESK_ID	= "deskId";
	public static final String FIELD_SEAT		= "seat";
	
	private static RMap<String, String> getRMap(int uid) {
		String key = String.format(USER_DESK, uid);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static boolean putClubId(int uid, int clubId) {
		RMap<String, String> rMap = getRMap(uid);
		rMap.put(FIELD_CLUB_ID, String.valueOf(clubId));
		return true;
	}

	public static boolean putDeskId(int uid, long deskId) {
		RMap<String, String> rMap = getRMap(uid);
		rMap.put(FIELD_DESK_ID, String.valueOf(deskId));
		return true;
	}
	
	public static int getClubId(int uid) {
		RMap<String, String> rMap = getRMap(uid);
		return CollectionUtils.getMapInt(rMap, FIELD_CLUB_ID);
	}

	public static long getDeskId(int uid) {
		RMap<String, String> rMap = getRMap(uid);
		return CollectionUtils.getMapLong(rMap, FIELD_DESK_ID);
	}
	
/*	public static Desk getDesk(int uid) {
		RMap<String, String> rMap = getRMap(uid);
		return ClubDeskRedis.getDesk(CollectionUtils.getMapInt(rMap, FIELD_CLUB_ID), CollectionUtils.getMapLong(rMap, FIELD_DESK_ID));
	}*/
	
}
