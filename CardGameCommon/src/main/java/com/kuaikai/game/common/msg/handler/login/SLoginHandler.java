package com.kuaikai.game.common.msg.handler.login;

import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.SLoginPB.SLogin;

//import com.farm.common.tcp.Message;
//import com.farm.server.tcp.MsgHandler;
//import com.farm.server.tcp.protocal.LoginResPB;

import io.netty.channel.ChannelHandlerContext;

public class SLoginHandler extends MsgHandler {

	public static final int msgid = 2;
	private SLogin sLogin;

	public SLoginHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public SLoginHandler(int code) {
		this(null);
		SLogin.Builder builder = SLogin.newBuilder();
		builder.setReturnCode(SLogin.ReturnCode.valueOf(code));
		sLogin = builder.build();
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgid, sLogin.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%d,data=%s", msgid, sLogin);
	}

}
