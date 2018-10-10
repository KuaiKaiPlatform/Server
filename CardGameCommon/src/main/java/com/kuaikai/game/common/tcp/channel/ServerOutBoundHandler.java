package com.kuaikai.game.common.tcp.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.msg.Message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ServerOutBoundHandler extends ChannelOutboundHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ServerOutBoundHandler.class);

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if (msg instanceof Message) {
			Message message = (Message) msg;
			int totalLength = 4;
			if (message.bytes != null) {
				totalLength += message.bytes.length + 4;
			} else {
				totalLength += 4;
			}
			ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(totalLength);
			byteBuf.writeShortLE(totalLength - 2);
			byteBuf.writeShortLE(message.msgid);
			if (message.bytes != null) {
				byteBuf.writeIntLE(message.bytes.length);
				byteBuf.writeBytes(message.bytes);
			} else {
				byteBuf.writeIntLE(0);
			}
			ctx.writeAndFlush(byteBuf);
			if (logger.isDebugEnabled()) {
				logger.debug("server send buff data,msgid=" + message.msgid + " length=" + totalLength + ",readable="
						+ byteBuf.readableBytes());
			}
		} else {
			super.write(ctx, msg, promise);
		}

	}
}
