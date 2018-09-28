package com.kuaikai.game.common.msg.handler.login;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.login.LoginEvent;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.KickOffResPB;
import com.kuaikai.game.common.msg.pb.LoginReqPB;
import com.kuaikai.game.common.msg.pb.LoginResPB;
import com.kuaikai.game.common.tcp.OnlineManager;

//import com.farm.common.db.redis.LockUtils;
//import com.farm.common.db.redis.OnlineRedisManager;
//import com.farm.common.db.redis.UserRedisService;
//import com.farm.common.tcp.Message;
//import com.farm.server.event.TriggerManager;
//import com.farm.server.event.login.LoginEvent;
//import com.farm.server.logic.OnlineManager;
//import com.farm.server.logic.user.UserManager;
//import com.farm.server.tcp.MsgHandler;
//import com.farm.server.tcp.protocal.KickOffResPB;
//import com.farm.server.tcp.protocal.LoginReqPB;
//import com.farm.server.tcp.protocal.LoginResPB;

import io.netty.channel.ChannelHandlerContext;

public class LoginReqHandler extends MsgHandler {

	private static final Logger LOGIN_LOGGER = LoggerFactory.getLogger("login");

	public LoginReqHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public static final int msgid = 577;
	private LoginReqPB.LoginReq loginReq;

	@Override
	public void process() {
		int uid = loginReq.getUid();
		String token = loginReq.getToken();
/*		String storeToken = UserRedisService.getToken(uid);
		if (storeToken == null || !storeToken.equals(token)) {
			// 登陆失败的处理
			writeMsg(new LoginResHandler(LoginResPB.LoginRes.ReturnCode.TOKEN_ERROR_VALUE));
			return;
		}*/
		if (OnlineManager.online(uid)) {
			OnlineManager.kickOff(uid, KickOffResPB.KickOffRes.Reason.LOGIN_IN_OTHER_PLACE_VALUE);
		}
		OnlineManager.onUserLogin(uid, this.ctx);
		// 拿userlock
//		RLock rLock = LockUtils.getUserLock(uid);
//		rLock.lock();
		try {
			// 通知登陆成功
			LoginEvent loginEvent = new LoginEvent(uid);
			TriggerManager.triggerEvent(loginEvent);
		} catch (Exception e) {
			LOGGER.error("LoginReqHandler.process@login listener error|uid={}", uid, e);
		} finally {
			//rLock.unlock();
		}
		//OnlineRedisManager.addLoginUser(uid);
		// 发送登陆成功
		writeMsg(new LoginResHandler(LoginResPB.LoginRes.ReturnCode.SUCCESS_VALUE));
		LOGGER.info("LoginReqHandler.process@user login|uid={}", uid);
//		String miniOpenid = UserManager.getMiniGameOpenid(uid);
//		String openid = UserManager.getOpenid(uid);
		// login log|| uid|openid|miniOpenid|webSocket|timeMill
//		LOGIN_LOGGER.info(
//				String.format("%d|%s|%s|%b|%d", uid, openid, miniOpenid, isWebSocket(), System.currentTimeMillis()));
	}

	@Override
	public void decode(Message message) throws Exception {
		loginReq = LoginReqPB.LoginReq.parseFrom(message.bytes);
	}

	@Override
	public Message encode() throws Exception {
		return null;
	}

	@Override
	public String desc() {
		return String.format("msgid=%d,data=%s", msgid, loginReq);
	}

}
