package com.kuaikai.game.common.msg;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.utils.ClassUtils;

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
			LOGGER.error("MessageFactory.createMessage@donot find msg|id={}", msg.msgid);
			return null;
		}
		MsgHandler msgHandler = null;
		try {
			msgHandler = constructor.newInstance(ctx);
			msgHandler.decode(msg);
		} catch (Exception e) {
			LOGGER.error("MessageFactory.createMessage@error happened|id={}", msg.msgid, e);
		}
		return msgHandler;
	}

	public static void init(String [] packages) throws Exception {
		for(String packageName : packages) {
			init(packageName);
		}
	}
	
	public static void init(String packageName) throws Exception {
		ChannelHandlerContext channelHandlerContext = null;
		for (String className : ClassUtils.getClassName(packageName, true)) {
			Class<?> class1 = Class.forName(className);
			Type type = class1.getGenericSuperclass();
			if (((Class<?>) type).getName().equals("com.kuaikai.game.common.msg.MsgHandler")) {
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
		//init();
	}
}
