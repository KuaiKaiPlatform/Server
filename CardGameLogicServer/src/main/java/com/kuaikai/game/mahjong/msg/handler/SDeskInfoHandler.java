package com.kuaikai.game.mahjong.msg.handler;

import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.hall.msg.pb.SDeskInfoPB.SDeskInfo;

import io.netty.channel.ChannelHandlerContext;

public class SDeskInfoHandler extends MsgHandler {

	public static final int msgid = 103;
	private SDeskInfo sDesk;

	public SDeskInfoHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public SDeskInfoHandler(SDeskInfo.Builder builder) {
		super(null);
		sDesk = builder.build();
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgid, sDesk.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%d,data=%s", msgid, sDesk);
	}

}
