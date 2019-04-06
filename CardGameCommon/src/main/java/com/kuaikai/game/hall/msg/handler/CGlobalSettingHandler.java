package com.kuaikai.game.hall.msg.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.redis.UserRedis;
import com.kuaikai.game.hall.msg.MsgId;
import com.kuaikai.game.hall.msg.pb.CGlobalSettingPB.CGlobalSetting;

import io.netty.channel.ChannelHandlerContext;

public class CGlobalSettingHandler extends MsgHandler {

	private static final Logger logger = LoggerFactory.getLogger("hall");

	public CGlobalSettingHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public static final int msgid = MsgId.CGlobalSetting;
	private CGlobalSetting msg;

	@Override
	public void process() {
		int uid = getUid();
		UserRedis.putSetting(uid, msg.getKey(), msg.getVal());
		logger.info("CGlobalSettingHandler.process@uid={}|key={}|val={}", uid, msg.getKey(), msg.getVal());
	}

	@Override
	public void decode(Message message) throws Exception {
		// 数据解析
		msg = CGlobalSetting.parseFrom(message.bytes);
	}

	@Override
	public Message encode() throws Exception {
		return null;
	}

	@Override
	public String desc() {
		return String.format("msgid=%d|data=%s", msgid, msg);
	}

}
