package com.kuaikai.game.common.msg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public abstract class MessageHandler extends ChannelInboundHandlerAdapter implements IMsgHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof Message) {
			Message message = (Message) msg;
			onMessageReceived(ctx, message);
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		onConnectSuccess(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		onLostConnection(ctx);
	}

}
