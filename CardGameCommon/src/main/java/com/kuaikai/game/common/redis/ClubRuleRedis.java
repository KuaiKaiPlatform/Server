package com.kuaikai.game.common.redis;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.ClubRule;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;

/**
 *   俱乐部玩法相关记录
 * @author Alear
 *
 */
public class ClubRuleRedis {

	public static final String CLUB_RULES				= "club.rules.%d";
	public static final String CLUB_RULE_SETTING		= "club.rule.setting.%d.%d";
	
	private static RScoredSortedSet<String> getRScoredSortedSet(int clubId) {
		String key = String.format(CLUB_RULES, clubId);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getScoredSortedSet(key);
	}
	
	/**
	 *	返回指定俱乐部的玩法列表
	 * @param clubId
	 * @return
	 */
	public static List<GameRule> getGameRules(int clubId) {
		RScoredSortedSet<String> rScoredSortedSet = getRScoredSortedSet(clubId);
		Collection<String> ruleIds = rScoredSortedSet.valueRange(0, -1);
		List<GameRule> result = new LinkedList<GameRule>();
		for(String ruleId : ruleIds) {
			result.add(GameRule.valueOf(Integer.parseInt(ruleId)));
		}
		return result;
	}

	/**
	 * 增加指定俱乐部的玩法
	 * @param clubId
	 * @param rule
	 * @return
	 */
	public static boolean addGameRule(int clubId, GameRule rule) {
		RScoredSortedSet<String> rScoredSortedSet = getRScoredSortedSet(clubId);
		return rScoredSortedSet.add(rule.getNumber(), String.valueOf(rule.getNumber()));
	}
	
	public static RMap<String, String> getRMapSetting(int clubId, GameRule rule) {
		String key = String.format(CLUB_RULE_SETTING, clubId, rule.getNumber());
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	/**
	 * 	设置指定俱乐部玩法的规则
	 * @param clubRule
	 * @return
	 */
	public static boolean putSetting(ClubRule clubRule) {
		RMap<String, String> rMap = getRMapSetting(clubRule.getClubId(), clubRule.getRule());
		rMap.putAll(clubRule.getSetting().getAllStr());
		return true;
	}
	
	/**
	 * 	返回指定俱乐部玩法的规则
	 * @param clubId
	 * @param rule
	 * @return
	 */
	public static AttrsModel getSetting(int clubId, GameRule rule, AttrsModel setting) {
		RMap<String, String> rMap = getRMapSetting(clubId, rule);
		if(setting == null) setting = new AttrsModel();
		setting.putAll(rMap.readAllMap());
		return setting;
	}

	public static boolean putSettingAttr(int clubId, GameRule rule, String key, String value) {
		RMap<String, String> rMap = getRMapSetting(clubId, rule);
		rMap.put(key, value);
		return true;
	}
	
	public static String getSettingAttr(int clubId, GameRule rule, String key) {
		RMap<String, String> rMap = getRMapSetting(clubId, rule);
		return rMap.get(key);
	}
	
}
