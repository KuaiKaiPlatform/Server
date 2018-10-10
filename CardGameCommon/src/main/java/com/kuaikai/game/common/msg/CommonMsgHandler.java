package com.kuaikai.game.common.msg;

import com.google.protobuf.GeneratedMessage;

import io.netty.channel.ChannelHandlerContext;

public class CommonMsgHandler extends MsgHandler {

	protected int msgId;
	protected GeneratedMessage genMsg;

	public CommonMsgHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public CommonMsgHandler(int msgId, GeneratedMessage genMsg) {
		super(null);
		this.msgId = msgId;
		this.genMsg = genMsg;
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgId, genMsg.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%d,data=%s", msgId, genMsg);
	}

}
