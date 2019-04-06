package com.kuaikai.game.common.redis;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.msg.pb.DialectPB.Dialect;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;

/**
 * 玩法相关
 * @author Alear
 *
 */
public class GameRuleRedis {

	public static final String RULE						= "rule.%d";				// 玩法基本属性（未使用）
	public static final String RULE_SETTING				= "rule.setting.%d";		// 玩法默认规则
	public static final String RULE_DIALECTS			= "rule.dialects.%d";		// 玩法相关方言有序集合
	
	private static RScoredSortedSet<String> getRScoredSortedSetDialects(GameRule rule) {
		String key = String.format(RULE_DIALECTS, rule.getNumber());
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getScoredSortedSet(key);
	}
	
	/**
	 * 返回指定玩法相关方言
	 * @param rule
	 * @return
	 */
	public static List<Dialect> getDialects(GameRule rule) {
		RScoredSortedSet<String> rScoredSortedSet = getRScoredSortedSetDialects(rule);
		Collection<String> dialects = rScoredSortedSet.valueRange(0, -1);
		List<Dialect> result = new LinkedList<Dialect>();
		for(String dialect : dialects) {
			result.add(Dialect.valueOf(Integer.parseInt(dialect)));
		}
		return result;
	}

	/**
	 * 增加指定玩法的方言
	 * @param dialect
	 * @param rule
	 * @return
	 */
	public static boolean addDialect(GameRule rule, Dialect dialect) {
		RScoredSortedSet<String> rScoredSortedSet = getRScoredSortedSetDialects(rule);
		return rScoredSortedSet.add(dialect.getNumber(), String.valueOf(dialect.getNumber()));
	}
	
	
	public static RMap<String, String> getRMapSetting(GameRule rule) {
		String key = String.format(RULE_SETTING, rule.getNumber());
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	/**
	 * 设置指定玩法的规则
	 * @param rule
	 * @return
	 */
	public static boolean putSetting(GameRule rule, AttrsModel setting) {
		RMap<String, String> rMap = getRMapSetting(rule);
		rMap.putAll(setting.getAllStr());
		return true;
	}
	
	/**
	 * 返回指定玩法的默认规则
	 * @param setting
	 * @param rule
	 * @return
	 */
	public static AttrsModel getSetting(GameRule rule, AttrsModel setting) {
		RMap<String, String> rMap = getRMapSetting(rule);
		if(setting == null) setting = new AttrsModel();
		setting.putAll(rMap.readAllMap());
		return setting;
	}

	public static boolean putSettingAttr(GameRule rule, String key, String value) {
		RMap<String, String> rMap = getRMapSetting(rule);
		rMap.put(key, value);
		return true;
	}
	
	public static String getSettingAttr(GameRule rule, String key) {
		RMap<String, String> rMap = getRMapSetting(rule);
		return rMap.get(key);
	}
	
}
