package com.kuaikai.game.common.tcp.channel;

import com.kuaikai.game.common.msg.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerInBoundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof ByteBuf) {
			ByteBuf byteBuf = (ByteBuf) msg;
			int canReadLength = byteBuf.readableBytes();
			if (canReadLength < 4) {
				return;
			}

			byteBuf.markReaderIndex();
			int tmp = byteBuf.readableBytes();
			if (tmp < 2 + 2 + 4) {// length+msgid+length
				byteBuf.resetReaderIndex();
				return;
			}
			int length = byteBuf.readUnsignedShortLE();
			int msgid = byteBuf.readShortLE();
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
					ByteBuf tmpBuf = byteBuf.readBytes(nowLength);
					tmpBuf.getBytes(0, bytes, 0, nowLength);
					tmpBuf.release();
				}
				Message message = new Message(msgid, bytes);
				ctx.fireChannelRead(message);
			}
			byteBuf.release();
		} else {
			super.channelRead(ctx, msg);
		}
	}
}
