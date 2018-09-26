package com.kuaikai.game.card.msg.handler.login;

import com.kuaikai.game.card.msg.pb.KickOffResPB;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;

//import com.farm.common.tcp.Message;
//import com.farm.server.tcp.MsgHandler;
//import com.farm.server.tcp.protocal.KickOffResPB;

import io.netty.channel.ChannelHandlerContext;

public class KickOffResHandler extends MsgHandler {

	public static final int msgid = 579;
	private KickOffResPB.KickOffRes kickOffRes;

	public KickOffResHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public KickOffResHandler(int code) {
		this(null);
		KickOffResPB.KickOffRes.Builder builder = KickOffResPB.KickOffRes.newBuilder();
		builder.setCode(KickOffResPB.KickOffRes.Reason.valueOf(code));
		kickOffRes = builder.build();
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgid, kickOffRes.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%s,data=%s", msgid, kickOffRes);
	}

}
