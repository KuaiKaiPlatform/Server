package com.kuaikai.game.common.mock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.msg.pb.DialectPB.Dialect;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.redis.GameRuleRedis;

public class GameRuleMock {
	
	public static Map<GameRule, AttrsModel> ruleSettings = new HashMap<GameRule, AttrsModel>();
	public static Map<GameRule, List<Dialect>> ruleDialects = new HashMap<GameRule, List<Dialect>>();
	
	static {
		
		// 亮六飞一
		AttrsModel setting = new AttrsModel();
		setting.put(CardGameSetting.TOTAL_SET, 8)
		.put(CardGameSetting.TOTAL_PLAYER, 4)
		.put(CardGameSetting.SHI_SAN_YAO, false)
		.put(CardGameSetting.SHI_SAN_BU_KAO, true)
		.put(CardGameSetting.BASE_RATE, 1);
		ruleSettings.put(GameRule.LIANG, setting);
		
		List<Dialect> dialects = new LinkedList<Dialect>();
		dialects.add(Dialect.YU_LIN);	// 榆林话
		ruleDialects.put(GameRule.LIANG, dialects);
		
		// 打锅子
		setting = new AttrsModel();
		setting.put(CardGameSetting.TOTAL_DI, 1)			// 1底100点
		.put(CardGameSetting.TOTAL_PLAYER, 4)
		.put(CardGameSetting.BANKER_DOUBLE, true)			// 庄家翻倍
		.put(CardGameSetting.SHI_SAN_BU_KAO, true)			// 十三不靠
		.put(CardGameSetting.GANG_SUI_HU, true);			// 杠随胡走
		ruleSettings.put(GameRule.GUO_ZI, setting);
		
		dialects = new LinkedList<Dialect>();
		dialects.add(Dialect.YU_LIN);	// 榆林话
		ruleDialects.put(GameRule.GUO_ZI, dialects);
		
		// 陕西麻将
		setting = new AttrsModel();
		setting.put(CardGameSetting.TOTAL_SET, 8)
		.put(CardGameSetting.TOTAL_PLAYER, 4)
		.put(CardGameSetting.QU_ZI, true);			// 不带字牌
		ruleSettings.put(GameRule.SXMJ, setting);
		
		dialects = new LinkedList<Dialect>();
		dialects.add(Dialect.YU_LIN);	// 榆林话
		ruleDialects.put(GameRule.SXMJ, dialects);
		
	}
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("redis.host", "127.0.0.1");
		properties.put("redis.port", "6379");
		properties.put("redis.db", "0");
		
		RedissonManager.getInstance().init(properties);
		
		for(Map.Entry<GameRule, AttrsModel> entry : ruleSettings.entrySet()) {
			GameRuleRedis.putSetting(entry.getKey(), entry.getValue());
		}
		
		for(Map.Entry<GameRule, List<Dialect>> entry : ruleDialects.entrySet()) {
			GameRule rule = entry.getKey();
			for(Dialect dialect : entry.getValue()) {
				GameRuleRedis.addDialect(rule, dialect);
			}
		}
		
		System.out.println("finished");
	}
	
}
