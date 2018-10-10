package com.kuaikai.game.common.msg;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.GeneratedMessage;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.common.tcp.channel.ws.WebSocket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public abstract class MsgHandler implements Runnable {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MsgHandler.class);

	protected ChannelHandlerContext ctx;
	protected GeneratedMessage genMsg;

	public MsgHandler(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void run() {
		try {
			process();
		} catch (Exception e) {
			LOGGER.error(String.format("MsgHandler.run@error occured|desc=%s|uid=%s", desc(), getUid()), e);
		}
	}

	protected void writeMsg(MsgHandler msgHandler) {
		try {
			if (ctx != null) {
				ctx.writeAndFlush(msgHandler.encode());
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(String.format("server send msg|uid=%s|msg=%s", getUid(), msgHandler));
				}
			}
		} catch (Exception e) {
			LOGGER.error(String.format("MsgHandler.writeMsg@error occured|desc=%s|msgDesc=%s|uid=%s", desc(),
					msgHandler.desc(), getUid()), e);
		}
	}

	protected Integer getUid() {
		Attribute<Object> attribute = ctx.channel().attr(AttributeKey.valueOf(OnlineManager.LOGIN_SIGN));
		Object object = attribute.get();
		if (object == null) {
			return 0;
		}
		if (object instanceof Integer) {
			return (Integer) object;
		}
		return 0;
	}

	public boolean isWebSocket() {
		return ctx.channel().hasAttr(AttributeKey.valueOf(WebSocket.WEBSOCKET));
	}

	public String getIp() {
		if (ctx == null) {
			return "127.0.0.1";
		} else {
			try {
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
				String clientIP = insocket.getAddress().getHostAddress();
				return clientIP;
			} catch (Exception e) {
			}
		}
		return "127.0.0.1";
	}

	/**
	 * 消息逻辑处理
	 * 
	 * @throws Exception
	 */
	public abstract void process();

	/**
	 * 消息解码
	 * 
	 * @param message
	 */
	public abstract void decode(Message message) throws Exception;

	/**
	 * 消息编码
	 * 
	 * @return
	 */
	public abstract Message encode() throws Exception;

	/**
	 * 具体内容
	 * 
	 * @return
	 */
	public abstract String desc();

	@Override
	public String toString() {
		return desc();
	}

}
