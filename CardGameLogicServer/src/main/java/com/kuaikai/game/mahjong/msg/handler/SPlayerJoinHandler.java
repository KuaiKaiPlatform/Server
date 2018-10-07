package com.kuaikai.game.mahjong.msg.handler;

import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin;

import io.netty.channel.ChannelHandlerContext;

public class SPlayerJoinHandler extends MsgHandler {

	public static final int msgid = 106;
	private SPlayerJoin sPlayerJoin;

	public SPlayerJoinHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public SPlayerJoinHandler(SPlayerJoin.Builder builder) {
		super(null);
		sPlayerJoin = builder.build();
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgid, sPlayerJoin.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%d,data=%s", msgid, sPlayerJoin);
	}

}
