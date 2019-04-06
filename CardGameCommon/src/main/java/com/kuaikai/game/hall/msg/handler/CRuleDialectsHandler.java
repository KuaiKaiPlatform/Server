package com.kuaikai.game.hall.msg.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.DialectPB.Dialect;
import com.kuaikai.game.common.redis.GameRuleRedis;
import com.kuaikai.game.common.redis.UserRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.hall.msg.MsgId;
import com.kuaikai.game.hall.msg.pb.CGlobalSettingPB.CGlobalSetting;
import com.kuaikai.game.hall.msg.pb.CRuleDialectsPB.CRuleDialects;
import com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects;

import io.netty.channel.ChannelHandlerContext;

public class CRuleDialectsHandler extends MsgHandler {

	private static final Logger logger = LoggerFactory.getLogger("hall");

	public CRuleDialectsHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public static final int msgid = MsgId.CRuleDialects;
	private CRuleDialects msg;

	@Override
	public void process() {
		int uid = getUid();
		List<Dialect> dialects = GameRuleRedis.getDialects(msg.getRule());
		SRuleDialects sRuleDialects = SRuleDialects.newBuilder()
			.setRule(msg.getRule())
			.addAllDialects(dialects)
			.build();
		
		OnlineManager.sendMsg(uid, new CommonMsgHandler(MsgId.SRuleDialects, sRuleDialects));
		logger.info("CRuleDialectsHandler.process@uid={}|rule={}|dialects={}", uid, msg.getRule(), dialects);
	}

	@Override
	public void decode(Message message) throws Exception {
		// 数据解析
		msg = CRuleDialects.parseFrom(message.bytes);
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
