package com.kuaikai.game.card.msg.handler.login;

import com.kuaikai.game.card.msg.pb.LoginResPB;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;

//import com.farm.common.tcp.Message;
//import com.farm.server.tcp.MsgHandler;
//import com.farm.server.tcp.protocal.LoginResPB;

import io.netty.channel.ChannelHandlerContext;

public class LoginResHandler extends MsgHandler {

	public static final int msgid = 578;
	private LoginResPB.LoginRes loginRes;

	public LoginResHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public LoginResHandler(int code) {
		this(null);
		LoginResPB.LoginRes.Builder builder = LoginResPB.LoginRes.newBuilder();
		builder.setReturnCode(LoginResPB.LoginRes.ReturnCode.valueOf(code));
		loginRes = builder.build();
	}

	@Override
	public void process() {
	}

	@Override
	public void decode(Message message) throws Exception {
	}

	@Override
	public Message encode() throws Exception {
		return new Message(msgid, loginRes.toByteArray());
	}

	@Override
	public String desc() {
		return String.format("msgid=%s,data=%s", msgid, loginRes);
	}

}
