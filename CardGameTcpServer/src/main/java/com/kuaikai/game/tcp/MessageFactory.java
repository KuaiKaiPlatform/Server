package com.kuaikai.game.tcp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farm.common.tcp.Message;
import com.farm.common.utils.ClassUtils;

import io.netty.channel.ChannelHandlerContext;

public class MessageFactory {

	private static Map<Integer, Constructor<MsgHandler>> messages = new HashMap<Integer, Constructor<MsgHandler>>();

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageFactory.class);

	/**
	 * 创建MsgHandler
	 * 
	 * @param ctx
	 * @param msg
	 * @return
	 */
	public static MsgHandler createMessage(ChannelHandlerContext ctx, Message msg) {
		Constructor<MsgHandler> constructor = messages.get(msg.msgid);
		if (constructor == null) {
			LOGGER.error(String.format("MessageFactory.createMessage@donot find msg|id=%d", msg.msgid));
			return null;
		}
		MsgHandler msgHandler = null;
		try {
			msgHandler = constructor.newInstance(ctx);
			msgHandler.decode(msg);
		} catch (Exception e) {
			LOGGER.error(String.format("MessageFactory.createMessage@error happened|id=%d", msg.msgid), e);
		}
		return msgHandler;
	}

	public static void init() throws Exception {
		ChannelHandlerContext channelHandlerContext = null;
		for (String className : ClassUtils.getClassName("com.farm.server.tcp.msg", true)) {
			Class<?> class1 = Class.forName(className);
			Type type = class1.getGenericSuperclass();
			if (((Class<?>) type).getName().equals("com.farm.server.tcp.MsgHandler")) {
				@SuppressWarnings("unchecked")
				Constructor<MsgHandler> constructor = (Constructor<MsgHandler>) class1
						.getConstructor(ChannelHandlerContext.class);
				Object object = constructor.newInstance(channelHandlerContext);
				Field field = class1.getDeclaredField("msgid");
				int msgid = field.getInt(object);
				if (msgid == -1) {
					throw new RuntimeException("do no find msgid," + className);
				}
				messages.put(msgid, constructor);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
	}
}
