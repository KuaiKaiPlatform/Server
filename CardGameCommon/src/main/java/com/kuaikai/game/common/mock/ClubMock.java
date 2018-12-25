package com.kuaikai.game.common.mock;

import java.util.HashMap;
import java.util.Map;

import com.kuaikai.game.common.model.Club;
import com.kuaikai.game.common.model.ClubRule;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.play.CardGameSetting;

public class ClubMock {

	// 大众亮六飞一 clubId
	public static final int CLUB_ID_PUB_LIANG = 1;
	
	public static Map<Integer, Club> clubs = new HashMap<Integer, Club>();
	
	static {
		// 竞技场，ID 1-100000 为大众
		Club c1 = new Club();
		c1.setId(CLUB_ID_PUB_LIANG);
		c1.setName("大众亮六飞一");
		c1.setTotal(1135);
		c1.setOwnerId(0);
		clubs.put(c1.getId(), c1);
		
		ClubRule c1Rule = new ClubRule();
		c1Rule.setClubId(c1.getId());
		c1Rule.setRule(GameRule.LIANG);
		c1Rule.getSetting()
		.put(CardGameSetting.TOTAL_SET, 8)
		.put(CardGameSetting.TOTAL_PLAYER, 4)
		.put(CardGameSetting.SHI_SAN_YAO, false)
		.put(CardGameSetting.SHI_SAN_BU_KAO, true)
		.put(CardGameSetting.BASE_RATE, 1);
		c1.addClubRule(c1Rule);
		
	}
	
	
}
