package com.kuaikai.game.common.redis;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.User;
import com.kuaikai.game.common.msg.pb.DialectPB.Dialect;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.utils.CollectionUtils;
import com.kuaikai.game.common.utils.RandomUtils;

/**
 * 玩家相关记录
 * @author Alear
 *
 */
public class UserRedis {

	private static final Logger logger = LoggerFactory.getLogger(UserRedis.class);
	
	public static final String USER					= "user.%d";
	public static final String FIELD_NICKNAME		= "nickname";
	public static final String FIELD_HEAD			= "head";
	public static final String FIELD_SEX			= "sex";
	
	public static final String USER_SETTING			= "user.setting.%d";
	public static final String FIELD_RULE_DIALECT	= "rule.dialect.%d";	// 指定玩法使用的方言
	
	private static RMap<String, String> getRMap(int uid) {
		String key = String.format(USER, uid);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static boolean putUser(User user) {
		RMap<String, String> rMap = getRMap(user.getId());
		rMap.put(FIELD_NICKNAME, user.getNickName());
		rMap.put(FIELD_HEAD, user.getHead());
		rMap.put(FIELD_SEX, String.valueOf(user.getSex()));
		return true;
	}
	
	public static User getUser(int uid) {
		Map<String, String> map = getRMap(uid).readAllMap();
		User user = new User();
		user.setId(uid);
		String nickName = CollectionUtils.getMapStr(map, FIELD_NICKNAME);
		user.setNickName(StringUtils.isEmpty(nickName)?String.valueOf(uid):nickName);
		user.setHead(CollectionUtils.getMapStr(map, FIELD_HEAD));
		user.setSex(map.containsKey(FIELD_SEX)?CollectionUtils.getMapInt(map, FIELD_SEX):RandomUtils.getRandomInt(0, 1));
		return user;
	}

	public static boolean putAttr(int uid, String key, String value) {
		RMap<String, String> rMap = getRMap(uid);
		rMap.put(key, value);
		return true;
	}
	
	public static String getAttr(int uid, String key) {
		RMap<String, String> rMap = getRMap(uid);
		return rMap.get(key);
	}
	
	private static RMap<String, String> getRMapSetting(int uid) {
		String key = String.format(USER_SETTING, uid);
		RedissonClient redissonClient = RedissonManager.getRedission();
		return redissonClient.getMap(key);
	}
	
	public static void putSetting(int uid, String key, String val) {
		RMap<String, String> rMap = getRMapSetting(uid);
		rMap.put(key, val);
	}
	
	public static AttrsModel getAllSetting(int uid) {
		RMap<String, String> rMap = getRMapSetting(uid);
		AttrsModel result = new AttrsModel();
		result.putAll(rMap.readAllMap());
		return result;
	}

	public static String getSetting(int uid, String key) {
		RMap<String, String> rMap = getRMapSetting(uid);
		return rMap.get(key);
	}
	
	public static void putRuleDialect(int uid, GameRule rule, Dialect dialect) {
		RMap<String, String> rMap = getRMapSetting(uid);
		String key = String.format(FIELD_RULE_DIALECT, rule.getNumber());
		rMap.put(key, String.valueOf(dialect.getNumber()));
	}

	public static Dialect getRuleDialect(int uid, GameRule rule) {
		RMap<String, String> rMap = getRMapSetting(uid);
		String key = String.format(FIELD_RULE_DIALECT, rule.getNumber());
		return Dialect.valueOf(Integer.parseInt(rMap.get(key)));
	}
	
}
