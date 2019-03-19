package com.kuaikai.game.logic.prepare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.conf.PropertyManager;
import com.kuaikai.game.common.mock.ClubMock;
import com.kuaikai.game.common.model.Club;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.common.redis.ClubDeskRedis;
import com.kuaikai.game.common.redis.ClubRedis;
import com.kuaikai.game.hall.msg.DefaultMsgCreator;
import com.kuaikai.game.hall.msg.MsgCreator;
import com.kuaikai.game.hall.msg.MsgId;
import com.kuaikai.game.logic.desk.ClubDeskMatcher;

/**
 * 玩家登录后，进入准备阶段，找到相应的牌桌：
 * 私有竞技场，查找redis中记录的牌桌编号；
 * 大众竞技场，进行牌桌匹配。
 * 
 * DEBUG 模式时，进行大众竞技场匹配。
 * 
 * @author Alear
 *
 */
public class PrepareManager {
	
	private static final Logger logger = LoggerFactory.getLogger(PrepareManager.class);
	
	private static MsgCreator msgCreator;
	
	public static void init() {
		msgCreator = new DefaultMsgCreator();
	}
	
	public static void onUserLogin(int uid) {
		logger.debug("PrepareManager.onUserLogin@start|uid={}", uid);
		int clubId = PlayerArenaRedis.getClubId(uid);
		long deskId = PlayerArenaRedis.getDeskId(uid);
		
		if(clubId == 0) {	// 未找到竞技场
			if(PropertyManager.isDebug()) {
				// DEBUG 模式时，设置为大众亮六飞一竞技场
				//int debugClubId = ClubMock.CLUB_ID_PUB_LIANG;
				int debugClubId = GameRule.LIANG_VALUE;
				//PlayerArenaRedis.putDesk(uid, debugClubId, 0);
				logger.info("PrepareManager.onUserLogin@Club set as {} for debug|uid={}", debugClubId, uid);
			} else {
				logger.warn("PrepareManager.onUserLogin@Club not found|uid={}", uid);
				return;	
			}
		}
		
		Club club = ClubRedis.getClub(clubId);
		if(!club.isPub()) {	// 私有竞技场，检查牌桌是否有效
			if(deskId == 0) {
				logger.warn("PrepareManager.onUserLogin@Private club desk does not exist|clubId={}|deskId={}|uid={}", clubId, deskId, uid);
			}
			return;
		}
		
		// 大众竞技场，开始匹配，生成牌桌号，入座
		deskId = ClubDeskMatcher.match(clubId, uid);
		
		// 发送 SPlayerJoin
		Desk desk = ClubDeskRedis.getDesk(clubId, deskId);
		OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SPlayerJoin, msgCreator.createSPlayerJoin(desk.getPlayerById(uid), desk).build()), uid);
	}
	
}
