package com.kuaikai.game.common.tcp;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.SchedulerException;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.card.msg.handler.login.KickOffResHandler;
import com.kuaikai.game.common.msg.IMsgHandler;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.tcp.ws.WebSocket;

//import com.farm.common.IMsgHandler;
//import com.farm.common.db.redis.LockUtils;
//import com.farm.common.utils.QuartzManager;
//import com.farm.common.utils.TimeUtils;
//import com.farm.server.event.TriggerManager;
//import com.farm.server.event.login.LoginOutEvent;
//import com.farm.server.tcp.MsgHandler;
//import com.farm.server.tcp.MsgThreadPool;
//import com.farm.server.tcp.msg.login.KickOffResHandler;
//import com.farm.server.webSocket.WebSocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class OnlineManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(OnlineManager.class);

	private static Map<Integer, ChannelHandlerContext> sessionMap = new ConcurrentHashMap<Integer, ChannelHandlerContext>();

	private static Map<Integer, Long> pingMap = new ConcurrentHashMap<Integer, Long>();

	public static final String LOGIN_SIGN = "LOGIN";

	public static boolean online(int uid) {
		return sessionMap.containsKey(uid);
	}

	public static void onUserLogin(int uid, ChannelHandlerContext channelHandlerContext) {
		sessionMap.put(uid, channelHandlerContext);
		pingMap.put(uid, System.currentTimeMillis());
		AttributeKey<Integer> attributeKey = AttributeKey.valueOf(OnlineManager.LOGIN_SIGN);
		Attribute<Integer> attribute = channelHandlerContext.channel().attr(attributeKey);
		attribute.set(uid);
	}

	public static boolean isWebSocketLoginUser(int uid) {
		ChannelHandlerContext context = sessionMap.get(uid);
		if (context == null) {// 默认是小游戏登陆玩家
			return true;
		} else {
			AttributeKey<Boolean> attributeKey = AttributeKey.valueOf(WebSocket.WEBSOCKET);
			Attribute<Boolean> attribute = context.channel().attr(attributeKey);
			if (attribute == null || attribute.get() == null) {// 不设置的情况为app
				return false;
			}
			return attribute.get();
		}
	}

	public static void updatePing(int uid, long pingTime) {
		pingMap.put(uid, pingTime);
	}

	public static Integer getUid(ChannelHandlerContext ctx) {
		AttributeKey<Integer> attributeKey = AttributeKey.valueOf(OnlineManager.LOGIN_SIGN);
		Attribute<Integer> attribute = ctx.channel().attr(attributeKey);
		return attribute.get();
	}

	public static void kickOff(int uid, int code) {
		ChannelHandlerContext channelHandlerContext = sessionMap.get(uid);
		if (channelHandlerContext != null) {
			KickOffResHandler kickOffResHandler = new KickOffResHandler(code);
			innerSendMsg(uid, kickOffResHandler, channelHandlerContext);
			onUserLogOut(uid, "server kick off");
		}
	}

	public static void onUserLogOut(int uid, String reason) {
		ChannelHandlerContext channelHandlerContext = sessionMap.remove(uid);
		pingMap.remove(uid);
		if (channelHandlerContext != null) {
			try {
				AttributeKey<IMsgHandler> msgHandlerKey = AttributeKey.valueOf(IMsgHandler.IMSGHANDLER);
				Attribute<IMsgHandler> attribute = channelHandlerContext.channel().attr(msgHandlerKey);
				if (attribute == null || attribute.get() == null) {
					LOGGER.info("OnlineManager.onUserLogOut@do not find IMsgHandler|uid={}|reason={}",
							uid, reason);
				} else {
					attribute.get().closeConnect(channelHandlerContext, reason);
				}
			} catch (Exception e) {
				LOGGER.error(String.format("OnlineManager.onUserLogOut@close connect error|uid=%d", uid));
			}
			// 发送玩家下线通知
//			RLock rLock = LockUtils.getUserLock(uid);
//			rLock.lock();
//			try {
//				// 通知登陆成功
//				LoginOutEvent loginOutEvent = new LoginOutEvent(uid);
//				TriggerManager.triggerEvent(loginOutEvent);
//			} catch (Exception e) {
//				LOGGER.error(String.format("OnlineManager.onUserLogOut@logout listener error|uid=%d", uid), e);
//			} finally {
//				rLock.unlock();
//			}
			LOGGER.info(String.format("OnlineManager.onUserLogOut@user log out|uid=%d|reason=%s", uid, reason));
		}
	}

	public static void sendToAll(Collection<Integer> uids, MsgHandler msgHandler) {
		for (int uid : uids) {
			sendMsg(uid, msgHandler);
		}
	}

	public static void sendToAll(Collection<Integer> uids, MsgHandler msgHandler, int exceptUid) {
		for (int uid : uids) {
			if (uid == exceptUid) {
				continue;
			}
			sendMsg(uid, msgHandler);
		}
	}

	public static void sendToAll(MsgHandler msgHandler) {
		for (Map.Entry<Integer, ChannelHandlerContext> entry : sessionMap.entrySet()) {
			int uid = entry.getKey();
			innerSendMsg(uid, msgHandler, entry.getValue());
		}
	}

	public static void sendToAll(MsgHandler msgHandler, int excpetUid) {
		for (Map.Entry<Integer, ChannelHandlerContext> entry : sessionMap.entrySet()) {
			int uid = entry.getKey();
			if (uid == excpetUid) {
				continue;
			}
			innerSendMsg(uid, msgHandler, entry.getValue());
		}
	}

	public static boolean sendMsg(int uid, MsgHandler msgHandler) {
		ChannelHandlerContext channelHandlerContext = sessionMap.get(uid);
		if (channelHandlerContext != null) {
			return innerSendMsg(uid, msgHandler, channelHandlerContext);
		}
		return false;
	}

	private static boolean innerSendMsg(int uid, MsgHandler msgHandler, ChannelHandlerContext channelHandlerContext) {
		try {
			channelHandlerContext.writeAndFlush(msgHandler.encode());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("server send msg|uid={}|msg={}", uid, msgHandler);
			}
			return true;
		} catch (Exception e) {
			LOGGER.error("OnlineManager.sendMsg@send msg error|msg={}|uid={}", msgHandler.desc(), uid,
					e);
		}
		return false;
	}

	private static final String CHECK_LAST_LOGIN_CRON = "* 0/5 * * * ? ";
	private static final String CHECK_USER_PING = "* 0/2 * * * ? ";// 每隔两分钟检测

	public static void init() throws SchedulerException {
//		QuartzManager.addCronTimeJob(QuartzManager.ONLINE_GROUP, "check_last_login", LastLoginCheckJob.class,
//				CHECK_LAST_LOGIN_CRON, null);
//		QuartzManager.addCronTimeJob(QuartzManager.ONLINE_GROUP, "CHECK_USER_PING", LastLoginCheckJob.class,
//				CHECK_USER_PING, null);
	}

//	public static void checkPingTime() {
//		long curTimes = System.currentTimeMillis();
//		Iterator<Map.Entry<Integer, Long>> iterator = pingMap.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Map.Entry<Integer, Long> entry = iterator.next();
//			int uid = entry.getKey();
//			long pingTime = entry.getValue();
//			if (curTimes - pingTime > 2 * TimeUtils.MINITE_TO_MILL) {
//				MsgThreadPool.getThreadPool().execute(new HandleLogOut(uid));
//			}
//		}
//	}
//
//	private static final class HandleLogOut implements Runnable {
//
//		private int uid;
//
//		public HandleLogOut(int uid) {
//			this.uid = uid;
//		}
//
//		@Override
//		public void run() {
//			try {
//				Long pingTime = pingMap.get(uid);
//				if (pingTime == null) {
//					return;
//				}
//				long curTimes = System.currentTimeMillis();
//				if (curTimes - pingTime < 2 * TimeUtils.MINITE_TO_MILL) {
//					return;
//				}
//				OnlineManager.onUserLogOut(uid, "ping time off line");
//			} catch (Exception e) {
//			}
//		}
//	}

}
