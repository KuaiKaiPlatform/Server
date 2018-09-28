package com.kuaikai.game.mahjong.msg.handler;

import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.mahjong.msg.pb.JoinDeskPB;
import io.netty.channel.ChannelHandlerContext;

public class SJoinDeskHandler extends MsgHandler {

	public static final int msgid = 602;
	private JoinDeskPB.SJoinDesk sJoinDesk;

	public SJoinDeskHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public SJoinDeskHandler(JoinDeskPB.SJoinDesk.Builder builder) {
		super(null);
		sJoinDesk = builder.build();
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgid, sJoinDesk.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%d,data=%s", msgid, sJoinDesk);
	}

}
