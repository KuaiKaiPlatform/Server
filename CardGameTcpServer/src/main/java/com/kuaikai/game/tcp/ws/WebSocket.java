package com.kuaikai.game.tcp.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.tcp.IMsgHandler;
import com.kuaikai.game.tcp.Message;
import com.kuaikai.game.tcp.MessageFactory;
import com.kuaikai.game.tcp.MsgHandler;
import com.kuaikai.game.tcp.OnlineManager;

//import com.farm.common.IMsgHandler;
//import com.farm.common.tcp.Message;
//import com.farm.common.websocket.WebSocketServerHandler;
//import com.farm.server.PingMsgHandler;
//import com.farm.server.logic.OnlineManager;
//import com.farm.server.tcp.MessageFactory;
//import com.farm.server.tcp.MsgHandler;
//import com.farm.server.tcp.MsgThreadPool;
//import com.farm.server.tcp.msg.login.LoginReqHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class WebSocket extends WebSocketServerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocket.class);

	public static final String WEBSOCKET = "webSocket";

	private WebSocketServerHandshaker handShaker;

	@Override
	public void onMessageReceived(ChannelHandlerContext ctx, Message msg) {
		try {
			boolean canSendMSg = checkSendMsg(ctx, msg);
			if (!canSendMSg) {
				LOGGER.info(String.format("WebSocket.onMessageReceived@discard msg|msgid=%d", msg.msgid));
				return;
			}
			MsgHandler msgHandler = MessageFactory.createMessage(ctx, msg);
			msgHandler.process();
//			if (msgHandler != null) {
//				MsgThreadPool.getThreadPool().execute(msgHandler);
//			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(
						String.format("WebSocket.onMessageReceived@receive msg|msg=%s|id=%d", msgHandler, msg.msgid));
			}
		} catch (Exception e) {
			LOGGER.error(String.format("WebSocket.onMessageReceived@error occured|msgid=%d", msg.msgid));
		}

	}

	private boolean checkSendMsg(ChannelHandlerContext ctx, Message msg) {
//		if (msg.msgid != LoginReqHandler.msgid) {// 非登陆消息 检测玩家登陆是否成功
//			return OnlineManager.getUid(ctx) != null;
//		}
		return true;

	}

	@Override
	public void onLostConnection(ChannelHandlerContext ctx) {
		try {
			Integer uid = OnlineManager.getUid(ctx);
			if (uid != null) {
				OnlineManager.onUserLogOut(uid, "lost client webSocket");
			}
		} catch (Exception e) {
			LOGGER.error(String.format("WebSocket.onLostConnection@connection lost error"), e);
		}
	}

	@Override
	public void pingMsg(ChannelHandlerContext ctx) {
//		PingMsgHandler pingMsgHandler = new PingMsgHandler(ctx);
//		MsgThreadPool.getThreadPool().execute(pingMsgHandler);
	}

	@Override
	public void closeConnect(ChannelHandlerContext ctx, String reason) {
		CloseWebSocketFrame closeWebSocketFrame = new CloseWebSocketFrame(1000, reason);
		handShaker.close(ctx.channel(), closeWebSocketFrame);
	}

	@Override
	public void onConnectSuccess(ChannelHandlerContext ctx, Object... objects) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					String.format("WebSocket.onConnectSuccess@connect success|desc=%s", ctx.channel().remoteAddress()));
		}
		if (objects == null || objects.length == 0) {
			return;
		}
		handShaker = (WebSocketServerHandshaker) objects[0];
		AttributeKey<Boolean> attributeKey = AttributeKey.valueOf(WEBSOCKET);
		Attribute<Boolean> attribute = ctx.channel().attr(attributeKey);
		attribute.set(true);

		AttributeKey<IMsgHandler> msgHandlerKey = AttributeKey.valueOf(IMsgHandler.IMSGHANDLER);
		Attribute<IMsgHandler> attributeMsgHanlder = ctx.channel().attr(msgHandlerKey);
		attributeMsgHanlder.set(this);
	}

}
