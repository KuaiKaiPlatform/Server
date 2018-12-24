package com.kuaikai.game.common.redis;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.Club;
import com.kuaikai.game.common.utils.CollectionUtils;

/**
 *   竞技场相关记录
 * @author Alear
 *
 */
public class ClubRedis {

	public static final String CLUB				= "club.%d";
	public static final String FIELD_CLUB_ID	= "id";
	public static final String FIELD_NAME		= "name";
	public static final String FIELD_OWNER_ID	= "ownerId";
	public static final String FIELD_TOTAL		= "total";
	
	private static RMap<String, String> getRMap(int clubId) {
		String key = String.format(CLUB, clubId);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static boolean putClub(int clubId, int ownerId) {
		RMap<String, String> rMap = getRMap(clubId);
		rMap.put(FIELD_CLUB_ID, String.valueOf(clubId));
		rMap.put(FIELD_OWNER_ID, String.valueOf(ownerId));
		return true;
	}
	
	public static Club getClub(int clubId) {
		RMap<String, String> rMap = getRMap(clubId);
		Club club = new Club(rMap.readAllMap());
		return club;
	}

	public static int getOwnerId(int clubId) {
		RMap<String, String> rMap = getRMap(clubId);
		return CollectionUtils.getMapInt(rMap, ClubRedis.FIELD_OWNER_ID);
	}
	
}
