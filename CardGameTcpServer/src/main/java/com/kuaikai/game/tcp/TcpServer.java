package com.kuaikai.game.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class TcpServer extends MessageHandler {

	private static final TcpServer tcpServer = new TcpServer();

	private static final Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);

	public static TcpServer getTcpServer() {
		return tcpServer;
	}

	public void start(int port) throws Exception {
		new Server(port, this).start();
	}

	@Override
	public void onMessageReceived(ChannelHandlerContext ctx, Message msg) {
		try {
			boolean canSendMSg = checkSendMsg(ctx, msg);
			if (!canSendMSg) {
				LOGGER.info(String.format("TcpServer.onMessageReceived@discard msg|msgid=%d", msg.msgid));
				return;
			}
			MsgHandler msgHandler = MessageFactory.createMessage(ctx, msg);
			msgHandler.process();
/*			if (msgHandler != null) {
				MsgThreadPool.getThreadPool().execute(msgHandler);
			}*/
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(
						String.format("TcpServer.onMessageReceived@receive msg|msg=%s|id=%d", msgHandler, msg.msgid));
			}
		} catch (Exception e) {
			LOGGER.error(String.format("TcpServer.onMessageReceived@error occured|msgid=%d", msg.msgid));
		}

	}

	private boolean checkSendMsg(ChannelHandlerContext ctx, Message msg) {
/*		if (msg.msgid != LoginReqHandler.msgid) {// 非登陆消息 检测玩家登陆是否成功
			return OnlineManager.getUid(ctx) != null;
		}*/
		return true;

	}

	@Override
	public void onConnectSuccess(ChannelHandlerContext ctx, Object... objects) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					String.format("TcpServer.onConnectSuccess@connect success|desc=%s", ctx.channel().remoteAddress()));
		}
		AttributeKey<IMsgHandler> msgHandlerKey = AttributeKey.valueOf(IMsgHandler.IMSGHANDLER);
		Attribute<IMsgHandler> attributeMsgHanlder = ctx.channel().attr(msgHandlerKey);
		attributeMsgHanlder.set(this);
	}

	@Override
	public void onLostConnection(ChannelHandlerContext ctx) {
		try {
			Integer uid = OnlineManager.getUid(ctx);
			if (uid != null) {
				OnlineManager.onUserLogOut(uid, "lost client connent");
			}
		} catch (Exception e) {
			LOGGER.error(String.format("TcpServer.onLostConnection@connection lost error"), e);
		}
	}

	@Override
	public void pingMsg(ChannelHandlerContext ctx) {
		// donothing
	}

	@Override
	public void closeConnect(ChannelHandlerContext ctx, String reason) {

	}

}
