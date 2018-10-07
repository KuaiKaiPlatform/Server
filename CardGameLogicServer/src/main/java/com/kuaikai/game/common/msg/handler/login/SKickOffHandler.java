package com.kuaikai.game.common.msg.handler.login;

import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.SKickOffPB.SKickOff;

//import com.farm.common.tcp.Message;
//import com.farm.server.tcp.MsgHandler;
//import com.farm.server.tcp.protocal.KickOffResPB;

import io.netty.channel.ChannelHandlerContext;

public class SKickOffHandler extends MsgHandler {

	public static final int msgid = 3;
	private SKickOff sKickOff;

	public SKickOffHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public SKickOffHandler(int code) {
		this(null);
		SKickOff.Builder builder = SKickOff.newBuilder();
		builder.setCode(SKickOff.Reason.valueOf(code));
		sKickOff = builder.build();
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgid, sKickOff.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%s,data=%s", msgid, sKickOff);
	}

}
