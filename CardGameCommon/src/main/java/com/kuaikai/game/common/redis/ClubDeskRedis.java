package com.kuaikai.game.common.redis;

import java.util.Collection;
import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.utils.CollectionUtils;

/**
 *  俱乐部牌桌相关记录
 * @author Alear
 *
 */
public class ClubDeskRedis {

	// 未开始的牌桌
	public static final String CLUB_DESKS_WAITING			= "club.desks.waiting.%d";

	// 正在进行游戏的牌桌
	public static final String CLUB_DESKS_STARTED			= "club.desks.started.%d";
	
	// 牌桌属性：玩法、状态、当前局数等
	public static final String CLUB_DESK			= "club.desk.%d.%d";
	
	public static final String FIELD_RULE			= "rule";
	public static final String FIELD_STATUS			= "status";
	public static final String FIELD_CURRENT_SET	= "curSet";
	
	// 牌桌各座位的uid，座位从1开始编号
	public static final String CLUB_DESK_SEATS				= "club.desk.seats.%d.%d";
	
	// 牌桌各玩家
	public static final String CLUB_DESK_PLAYER				= "club.desk.player.%d.%d.%d";
	public static final String FIELD_SEAT					= "seat";
	
	// 牌桌各玩家各项分数
	public static final String CLUB_DESK_PLAYER_POINTS		= "club.desk.player.points.%d.%d.%d";

	
	private static RScoredSortedSet<Long> getRScoredSortedSetWaiting(int clubId) {
		String key = String.format(CLUB_DESKS_WAITING, clubId);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getScoredSortedSet(key);
	}
	
	/**
	 * 私有竞技场：增加一个等待的牌桌ID
	 * @param clubId
	 * @param deskId
	 * @return
	 */
	public static boolean addWaiting(int clubId, long deskId) {
		RScoredSortedSet<Long> deskIds = getRScoredSortedSetWaiting(clubId);
		return deskIds.add(deskId, deskId);
	}
	
	/**
	 * 私有竞技场：删除一个等待的牌桌ID
	 * @param clubId
	 * @param deskId
	 * @return
	 */
	public static boolean removeWaiting(int clubId, long deskId) {
		RScoredSortedSet<Long> deskIds = getRScoredSortedSetWaiting(clubId);
		return deskIds.remove(deskId);
	}
	
	/**
	 * 私有竞技场：返回等待的牌桌ID
	 * @param clubId
	 * @return
	 */
	public static Collection<Long> getWaitings(int clubId) {
		RScoredSortedSet<Long> deskIds = getRScoredSortedSetWaiting(clubId);
		return deskIds.valueRange(0, -1);
	}
	
	private static RScoredSortedSet<Long> getRScoredSortedSetStarted(int clubId) {
		String key = String.format(CLUB_DESKS_STARTED, clubId);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getScoredSortedSet(key);
	}
	
	/**
	 * 私有竞技场：增加一个进行的牌桌ID
	 * @param clubId
	 * @param deskId
	 * @return
	 */
	public static boolean addStarted(int clubId, long deskId) {
		RScoredSortedSet<Long> deskIds = getRScoredSortedSetStarted(clubId);
		return deskIds.add(deskId, deskId);
	}
	
	/**
	 * 私有竞技场：删除一个进行的牌桌ID
	 * @param clubId
	 * @param deskId
	 * @return
	 */
	public static boolean removeStarted(int clubId, long deskId) {
		RScoredSortedSet<Long> deskIds = getRScoredSortedSetStarted(clubId);
		return deskIds.remove(deskId);
	}
	
	/**
	 * 私有竞技场：返回进行的牌桌ID
	 * @param clubId
	 * @return
	 */
	public static Collection<Long> getStarteds(int clubId) {
		RScoredSortedSet<Long> deskIds = getRScoredSortedSetStarted(clubId);
		return deskIds.valueRange(0, -1);
	}
	
	public static RMap<String, String> getRMap(int clubId, long deskId) {
		String key = String.format(CLUB_DESK, clubId, deskId);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static void putDesk(Desk desk) {
		RMap<String, String> rMap = getRMap(desk.getClubId(), desk.getDeskId());
		rMap.put(FIELD_RULE, String.valueOf(desk.getRule().getNumber()));
		rMap.put(FIELD_STATUS, String.valueOf(desk.getStatus().getNumber()));
		rMap.put(FIELD_CURRENT_SET, String.valueOf(desk.getCurSet()));
	}
	
	public static Desk getDesk(int clubId, long deskId) {
		Desk desk = new Desk(deskId);
		desk.setClubId(clubId);
		
		RMap<String, String> rMap = getRMap(clubId, deskId);
		if(!rMap.isExists()) return desk;
		
		desk.setRule(GameRule.valueOf(CollectionUtils.getMapInt(rMap, FIELD_RULE)));
		desk.setStatus(GameStatus.valueOf(CollectionUtils.getMapInt(rMap, FIELD_STATUS)));
		desk.setCurSet(CollectionUtils.getMapInt(rMap, FIELD_CURRENT_SET));
		
		// 复制俱乐部玩法规则到牌桌
		desk.setSetting(ClubRuleRedis.getSetting(clubId, desk.getRule()));
		return desk;
	}
	
	public static boolean putAttr(int clubId, long deskId, String key, String value) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		rMap.put(key, value);
		return true;
	}
	
	public static String getAttr(int clubId, long deskId, String key) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		return rMap.get(key);
	}
	
	public static boolean delete(int clubId, long deskId) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		return rMap.delete();
	}
	
	
	private static RMap<String, String> getRMapSeats(int clubId, long deskId) {
		String key = String.format(CLUB_DESK_SEATS, clubId, deskId);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static boolean putSeat(int clubId, long deskId, int seat, int uid) {
		RMap<String, String> rMap = getRMapSeats(clubId, deskId);
		rMap.put(String.valueOf(seat), String.valueOf(uid));
		return true;
	}

	public static int putNextSeat(int clubId, long deskId, int uid) {
		RMap<String, String> rMap = getRMapSeats(clubId, deskId);
		rMap.put(String.valueOf(rMap.size()+1), String.valueOf(uid));
		return rMap.size();
	}
	
	public static Map<String, String> getSeats(int clubId, long deskId) {
		RMap<String, String> rMap = getRMapSeats(clubId, deskId);
		return rMap.readAllMap();
	}

	public static boolean deleteSeats(int clubId, long deskId) {
		RMap<String, String> rMap = getRMapSeats(clubId, deskId);
		return rMap.delete();
	}
	
	private static RMap<String, String> getRMapPlayer(int clubId, long deskId, int uid) {
		String key = String.format(CLUB_DESK_PLAYER, clubId, deskId, uid);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static boolean isPlayerSeated(int clubId, long deskId, int uid) {
		return getRMapPlayer(clubId, deskId, uid).isExists();
	}
	
	public static void playerSeat(int clubId, long deskId, int uid, int seat) {
		getRMapPlayer(clubId, deskId, uid).put(FIELD_SEAT, String.valueOf(seat));
	}
	
}
