package com.kuaikai.game.common.tcp.channel.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.tcp.channel.IChannelConnection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public abstract class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> implements IChannelConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

	/**
	 * 全局websocket
	 */
	private WebSocketServerHandshaker handshaker;

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
		// 如果http解码失败 则返回http异常 并且判断消息头有没有包含Upgrade字段(协议升级)
		if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
			sendHttpResponse(ctx, request,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		// 构造握手响应返回
		WebSocketServerHandshakerFactory ws = new WebSocketServerHandshakerFactory("", null, false);
		handshaker = ws.newHandshaker(request);
		if (handshaker == null) {
			// 版本不支持
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), request);
			onConnectSuccess(ctx, handshaker);
		}
	}

	/**
	 * websocket帧
	 * 
	 * @param ctx
	 * @param frame
	 */
	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
		// 判断是否关闭链路指令
		if (frame instanceof CloseWebSocketFrame) {
			onLostConnection(ctx);
			return;
		}
		// 判断是否Ping消息 -- ping/pong心跳包
		if (frame instanceof PingWebSocketFrame) {
			PingWebSocketFrame pingWebSocketFrame = (PingWebSocketFrame) frame.copy();
			ctx.channel().write(pingWebSocketFrame);
			pingMsg(ctx);
			return;
		}
		/*
		 * //本程序仅支持文本消息， 不支持二进制消息 if(!(frame instanceof TextWebSocketFrame)){
		 * throw new UnsupportedOperationException(
		 * String.format("%s frame types not supported",
		 * frame.getClass().getName())); }
		 */

		// 支持二进制消息
		if (frame instanceof BinaryWebSocketFrame) {
			BinaryWebSocketFrame img = (BinaryWebSocketFrame) frame;
			ByteBuf byteBuf = img.content();
			int canReadLength = byteBuf.readableBytes();
			if (canReadLength < 4) {
				return;
			}

			byteBuf.markReaderIndex();
			int length = byteBuf.readUnsignedShort();
			int msgid = byteBuf.readShort();
			// int nowLength = length - 2;
			int nowLength = length - 6;
			byteBuf.readInt();
			// -------------------------
			int nowCanRead = byteBuf.readableBytes();
			if (nowLength > nowCanRead) {
				byteBuf.resetReaderIndex();
				return;
			} else {
				byte[] bytes = null;
				if (nowLength > 0) {
					bytes = new byte[nowLength];
					byteBuf.readBytes(nowLength).getBytes(0, bytes, 0, nowLength);
				}
				Message message = new Message(msgid, bytes);
				onMessageReceived(ctx, message);
			}
			return;
		}
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
		// 返回应答给客户端
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
		}
		// 如果是非Keep-Alive，关闭连接
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	/**
	 * 异常 出错
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// cause.printStackTrace();
		// ctx.close();
		// onLostConnection(ctx);
		LOGGER.error("WebSocketServerHandler.exceptionCaught@exception occured", cause);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 普通HTTP接入
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) { // websocket帧类型 已连接
			// BinaryWebSocketFrame CloseWebSocketFrame
			// ContinuationWebSocketFrame
			// PingWebSocketFrame PongWebSocketFrame TextWebScoketFrame

			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

}