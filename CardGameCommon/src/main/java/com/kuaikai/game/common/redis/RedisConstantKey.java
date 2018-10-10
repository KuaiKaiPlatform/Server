package com.kuaikai.game.common.redis;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class RedisConstantKey {
	// 渠道对应的app信息
	public static final String CHANNEL_APP_INFO = "channel.app.";

	// 不更新版本(审核版) NO_UPDATE.channelid.version
	public static final String NO_UPDATE = "no.update.channel.";
	// 整包更新 PACKAGE_UPDATE.channelid hget max hget min url
	public static final String PACKAGE_UPDATE = "update.package.channel.";
	// 版本更新 VERSION_UPDATE.channelid
	public static final String VERSION_UPDATE = "update.version.channel.";

	// 服务器信息key
	public static final String SERVER_KEY_INDEX = "server_info.";
	// 服务器在线情况统计key
	public static final String SERVER_ONLINE_SORT = "server_online_sort";

	// 玩家key +uid
	public static final String USER_KEY = "uid.";
	// 玩家仓库物品表 + uid
	public static final String ITEM_KEY = "item.";
	// 玩家仓库物品排序表 + uid
	public static final String ITEM_SORT_KEY = "itemSort.";
	// 玩家所有建筑集合+ uid
	public static final String USER_BUILDINGS = "userBuildings.";
	// 玩家建筑解锁信息 +uid +"." +cfgid
	public static final String USER_UNLOCKBUILDINGS = "userUnlockBuilding.";

	// 玩家正常任务 + uid
	public static final String USER_TASK_NORMAL = "userTaskNormal.";
	// 玩家收集任务 + uid
	public static final String USER_TASK_GATHER = "userTaskGather.";

	public static final String USER_ACHIEVEMENT = "userAchievement.";

	public static final String USER_FRIEND = "friend.";
	public static final String USER_APPLY = "apply.";
	public static final String USER_FRIEND_MSG = "friendmsg.";

	public static final String ONLINE_USER_IN_FEW_DAYS = "onlineUser";
	public static final String USER_LAST_ONLINE_TIME = "lastOnline.";

	public static final String USER_STEAL = "userSteal.";
	public static final String USER_NEED_HELP_INFO = "userNeedHelpInfo.";
	public static final String USER_NOTE_DETAIL = "userNoteDetail.";

	public static final String USER_LOGIN_DATA = "userLogin.";

	public static final String USER_TARGET = "userTarget.";

	public static final String USER_INVITE = "userInvite.";

	public static final String USER_REDGIFT_CACHE = "userRedGiftCache.";
	public static final String USER_REDGIFT_STATISTICS = "userRedGiftStatistics.";

	public static final String USER_BUFF = "userBuff.";

	public static final String FAMILY = "family.";

	public static final String USER_FAMILY = "userFamily.";

	public static final String FAMILY_RANK = "family_rank.";

	public static final String USER_RANK = "user_rank.";

	public static final String WX_MINI_SESSION = "wx.mini.session.%s"; // 微信小程序session
	
	public static final String WX_CUSTOMER_SERVICE_LINK = "wx.customer.service.link"; // 微信客服号图文链接（转移到消息内容）
	
	public static final String WX_CUSTOMER_SERVICE_RESP = "wx.customer.service.resp"; // 微信客服号消息内容（包含 msgtype，图文链接和文本消息）
	
	public static final String GOODS = "goods.%s";
	
	public static final String ORDER = "order.%s";
	
	public static final String ORDER_DAILY_SEQ_ID = "order.daily.seq.id.%s";	// 订单每日流水号
	
	public static final String ORDER_LOCK = "order.lock.%s";
	
	public static final String APPLE_IAP_TRANSACTION = "apple.iap.transaction.%s";
	
	public static final String WX_PAY_CONFIG = "wx.pay.config.%s";		// 微信支付配置，参数为appId
	
	public static final String WX_PAY_TEST = "wx.pay.test.%s";		// 微信支付测试模式，参数为productId
	
	public static final String CHAT_CHANNEL = "chatChannel.";

	public static final String NPC_STEAL = "npcSteal.";

	public static final String GUIDE = "guide.";

	public static final String RECHARGE = "recharge.";

	public static final String SETTINGS = "settings.";

	public static final String NOTIFY = "notify.";

	public static final String USER_SHARE = "userShare.";
	public static final String USER_WITH_DRAW = "userWithDraw.";

	public static void check() throws Exception {
		RedisConstantKey redisConstantKey = new RedisConstantKey();
		Field[] fields = RedisConstantKey.class.getDeclaredFields();
		Set<String> allKeys = new HashSet<String>();
		for (Field field : fields) {
			Object object = field.get(redisConstantKey);
			if (object instanceof String) {
				String value = (String) object;
				if (allKeys.contains(value)) {
					throw new RuntimeException("Redis key constant has the same prefix key,value=" + value);
				}
				allKeys.add(value);
			}
		}
	}

	public static final String USER_LOCK = "userLock.";

	public static final String FAMILY_LOCK = "familyLock";
}
