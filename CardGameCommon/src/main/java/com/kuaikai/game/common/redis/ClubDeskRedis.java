package com.kuaikai.game.common.redis;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
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
	public static final String CLUB_DESK					= "club.desk.%d.%d";
	
	public static final String FIELD_RULE					= "rule";
	public static final String FIELD_STATUS					= "status";
	public static final String FIELD_CURRENT_SET			= "curSet";

	// 牌桌玩法规则
	public static final String CLUB_DESK_SETTING			= "club.desk.setting.%d.%d";
	
	// 牌桌各座位的uid，座位从1开始编号
	public static final String CLUB_DESK_SEATS				= "club.desk.seats.%d.%d";
	
	// 牌桌各玩家属性
	public static final String CLUB_DESK_PLAYER				= "club.desk.player.%d.%d.%d";
	public static final String FIELD_SEAT					= "seat";
	public static final String FIELD_PREPARED				= "prepared";
	public static final String FIELD_OFFLINE				= "offline";
	public static final String FIELD_BET					= "bet";
	
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
		
		// 玩法、状态、当前局数
		desk.setRule(GameRule.valueOf(CollectionUtils.getMapInt(rMap, FIELD_RULE)));
		desk.setStatus(GameStatus.valueOf(CollectionUtils.getMapInt(rMap, FIELD_STATUS)));
		desk.setCurSet(CollectionUtils.getMapInt(rMap, FIELD_CURRENT_SET));
		
		// 复制俱乐部玩法规则到牌桌
		ClubRuleRedis.getSetting(clubId, desk.getRule(), desk.getClubSetting());
//		RMap<String, String> rMapSetting = ClubRuleRedis.getRMapSetting(clubId, desk.getRule());
//		Map<String, String> setting = rMapSetting.readAllMap();
//		desk.getSetting().putAll(setting);
//		desk.getClubSetting().putAll(setting);
		desk.copyClubSetting();
		
		// 牌桌各玩家
		Map<String, String> seats = getSeats(clubId, deskId);
		for(String pid : seats.values()) {
			desk.addPlayer(getPlayer(clubId, deskId, Integer.parseInt(pid)));
		}
		
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

	public static boolean putStatus(int clubId, long deskId, int status) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		rMap.put(FIELD_STATUS, String.valueOf(status));
		return true;
	}
	
	public static int getStatus(int clubId, long deskId) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		return CollectionUtils.getMapInt(rMap, FIELD_STATUS);
	}
	
	public static boolean putRule(int clubId, long deskId, GameRule rule) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		rMap.put(FIELD_RULE, String.valueOf(rule.getNumber()));
		return true;
	}

	public static GameRule getRule(int clubId, long deskId) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		return GameRule.valueOf(CollectionUtils.getMapInt(rMap, FIELD_RULE));
	}
	
	public static boolean delete(int clubId, long deskId) {
		RMap<String, String> rMap = getRMap(clubId, deskId);
		return rMap.delete();
	}
	
	public static RMap<String, String> getRMapSetting(int clubId, long deskId) {
		String key = String.format(CLUB_DESK_SETTING, clubId, deskId);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	/**
	 * 	设置指定牌桌玩法规则
	 * @param clubRule
	 * @return
	 */
	public static boolean putSetting(int clubId, long deskId, AttrsModel setting) {
		RMap<String, String> rMap = getRMapSetting(clubId, deskId);
		rMap.putAll(setting.getAllStr());
		return true;
	}
	
	/**
	 * 返回指定牌桌玩法规则，牌桌未设置玩法规则则返回俱乐部玩法规则
	 * @param clubId
	 * @param rule
	 * @return
	 */
	public static AttrsModel getSetting(int clubId, long deskId, AttrsModel setting) {
		if(setting == null) setting = new AttrsModel();
		RMap<String, String> rMap = getRMapSetting(clubId, deskId);
		if(rMap.isExists()) {
			setting.putAll(rMap.readAllMap());
		} else {
			GameRule rule = ClubDeskRedis.getRule(clubId, deskId);
			return ClubRuleRedis.getSetting(clubId, rule, setting);
		}
		return setting;
	}

	public static boolean putSettingAttr(int clubId, long deskId, String key, String value) {
		RMap<String, String> rMap = getRMapSetting(clubId, deskId);
		rMap.put(key, value);
		return true;
	}
	
//	public static String getSettingAttr(int clubId, long deskId, String key) {
//		RMap<String, String> rMap = getRMapSetting(clubId, deskId);
//		return rMap.get(key);
//	}
	
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
	
	public static Collection<Integer> getPlayerIds(int clubId, long deskId) {
		Map<String, String> seats = getSeats(clubId, deskId);
		Collection<Integer> result = new LinkedList<Integer>();
		for(String pid : seats.values()) {
			result.add(Integer.parseInt(pid));
		}
		return result;
	}
	
	private static RMap<String, String> getRMapPlayer(int clubId, long deskId, int uid) {
		String key = String.format(CLUB_DESK_PLAYER, clubId, deskId, uid);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static boolean isPlayerSeated(int clubId, long deskId, int uid) {
		return getRMapPlayer(clubId, deskId, uid).isExists();
	}
	
	public static void deletePlayer(int clubId, long deskId, int uid) {
		getRMapPlayer(clubId, deskId, uid).delete();
	}
	
	/**
	 * 	玩家入座（准备）
	 * @param clubId
	 * @param deskId
	 * @param uid
	 * @param seat
	 * @param prepared
	 */
	public static void playerTakeSeat(int clubId, long deskId, int uid, int seat, boolean prepared) {
		Map<String, String> attrs = getRMapPlayer(clubId, deskId, uid);
		attrs.put(FIELD_SEAT, String.valueOf(seat));
		attrs.put(FIELD_PREPARED, String.valueOf(prepared));
	}
	
	/**
	 * 	玩家下注（准备）
	 * @param clubId
	 * @param deskId
	 * @param uid
	 * @param bet
	 */
	public static void playerBet(int clubId, long deskId, int uid, int bet) {
		Map<String, String> attrs = getRMapPlayer(clubId, deskId, uid);
		attrs.put(FIELD_BET, String.valueOf(bet));
		attrs.put(FIELD_PREPARED, Boolean.TRUE.toString());
	}
	
	/**
	 * 	玩家离线状态切换
	 * @param clubId
	 * @param deskId
	 * @param uid
	 * @param offline
	 */
	public static void playerOffline(int clubId, long deskId, int uid, boolean offline) {
		Map<String, String> attrs = getRMapPlayer(clubId, deskId, uid);
		attrs.put(FIELD_OFFLINE, String.valueOf(offline));
	}
	
	public static Player getPlayer(int clubId, long deskId, int uid) {
		Map<String, String> attrs = getRMapPlayer(clubId, deskId, uid);
		
		Player p = new Player();
		p.setSeat(CollectionUtils.getMapInt(attrs, FIELD_SEAT));
		p.setOffline(CollectionUtils.getMapBool(attrs, FIELD_OFFLINE));
		p.setPrepared(CollectionUtils.getMapBool(attrs, FIELD_PREPARED));
		p.setBet(CollectionUtils.getMapInt(attrs, FIELD_BET));
		p.setUser(UserRedis.getUser(uid));
		
		return p;
	}
	
}
