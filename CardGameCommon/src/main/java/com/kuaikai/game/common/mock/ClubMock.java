package com.kuaikai.game.common.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kuaikai.game.common.db.RedissonManager;
import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Club;
import com.kuaikai.game.common.model.ClubRule;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.redis.ClubRedis;
import com.kuaikai.game.common.redis.ClubRuleRedis;

public class ClubMock {
	
	public static Map<Integer, Club> clubs = new HashMap<Integer, Club>();
	
	static {
		// 大众竞技场 clubId 等于 game rule 
		
		// 大众亮六飞一
		Club c1 = new Club();
		c1.setId(GameRule.LIANG_VALUE);
		c1.setName("大众亮六飞一");
		c1.setTotal(1135);
		c1.setOwnerId(0);
		clubs.put(c1.getId(), c1);
		
		ClubRule c1Rule = new ClubRule();
		c1Rule.setClubId(c1.getId());
		c1Rule.setRule(GameRule.LIANG);
		AttrsModel setting = new AttrsModel();
		c1Rule.setSetting(setting);
		setting.put(CardGameSetting.TOTAL_SET, 8)
		.put(CardGameSetting.TOTAL_PLAYER, 4)
		.put(CardGameSetting.SHI_SAN_YAO, false)
		.put(CardGameSetting.SHI_SAN_BU_KAO, true)
		.put(CardGameSetting.BASE_RATE, 1);
		c1.addClubRule(c1Rule);
		
		// 大众陕西麻将
		Club c2 = new Club();
		c2.setId(GameRule.SXMJ_VALUE);
		c2.setName("大众陕西麻将");
		c2.setTotal(837);
		c2.setOwnerId(0);
		clubs.put(c2.getId(), c2);
		
		ClubRule c2Rule = new ClubRule();
		c2Rule.setClubId(c2.getId());
		c2Rule.setRule(GameRule.SXMJ);
		setting = new AttrsModel();
		c2Rule.setSetting(setting);
		setting.put(CardGameSetting.TOTAL_SET, 8)
		.put(CardGameSetting.TOTAL_PLAYER, 4)
		.put(CardGameSetting.QU_ZI, true);			// 不带字牌
		
		c2.addClubRule(c2Rule);
		
		// 大众打锅子
		Club c3 = new Club();
		c3.setId(GameRule.GUO_ZI_VALUE);
		c3.setName("大众打锅子");
		c3.setTotal(871);
		c3.setOwnerId(0);
		clubs.put(c3.getId(), c3);
		
		ClubRule c3Rule = new ClubRule();
		c3Rule.setClubId(c3.getId());
		c3Rule.setRule(GameRule.GUO_ZI);
		c3.addClubRule(c3Rule);
		
	}
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("redis.host", "127.0.0.1");
		properties.put("redis.port", "6379");
		properties.put("redis.db", "0");
		
		RedissonManager.getInstance().init(properties);
		
		for(Club club : clubs.values()) {
			initRedis(club);
		}
		
		System.out.println("finished");
	}
	
	private static void initRedis(Club club) {
		ClubRedis.putClub(club);
		ClubRuleRedis.addGameRule(club.getId(), club.getClubRules().get(0).getRule());
		for(ClubRule clubRule : club.getClubRules()) {
			ClubRuleRedis.putSetting(clubRule);	
		}
	}
	
}
