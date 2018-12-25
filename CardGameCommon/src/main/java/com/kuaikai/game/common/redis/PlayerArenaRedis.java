package com.kuaikai.game.common.redis;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.Desk;
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
	
	private static RMap<String, String> getRMap(int uid) {
		String key = String.format(USER_DESK, uid);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static boolean putUserDesk(int uid, int clubId, int deskId) {
		RMap<String, String> rMap = getRMap(uid);
		rMap.put(FIELD_CLUB_ID, String.valueOf(clubId));
		rMap.put(FIELD_DESK_ID, String.valueOf(deskId));
		return true;
	}
	
	public static Desk getUserDesk(int uid) {
		RMap<String, String> rMap = getRMap(uid);
		Desk desk = new Desk(CollectionUtils.getMapInt(rMap, FIELD_DESK_ID));
		desk.setClubId(CollectionUtils.getMapInt(rMap, FIELD_CLUB_ID));
		return desk;
	}
	
	
}
