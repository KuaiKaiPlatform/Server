package com.kuaikai.game.common.msg;


import io.netty.channel.ChannelHandlerContext;

public interface IMsgHandler {

	public static final String IMSGHANDLER = "IMsgHandler";

	/**
	 * 收到消息
	 * 
	 * @param ctx
	 * @param msg
	 */
	public abstract void onMessageReceived(ChannelHandlerContext ctx, Message msg);

	/**
	 * 连接成功
	 * 
	 * @param ctx
	 */
	public abstract void onConnectSuccess(ChannelHandlerContext ctx, Object... objects);

	/**
	 * 被动触发 连接丢失
	 * 
	 * @param ctx
	 */
	public abstract void onLostConnection(ChannelHandlerContext ctx);

	/**
	 * 主动发起关闭
	 */
	public abstract void closeConnect(ChannelHandlerContext ctx, String reason);

	public abstract void pingMsg(ChannelHandlerContext ctx);
}
