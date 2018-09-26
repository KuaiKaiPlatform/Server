package com.kuaikai.game.common.tcp;

import com.kuaikai.game.common.msg.MessageHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
	private int port;
	private MessageHandler messageHandler;

	public Server(int port, MessageHandler messageHandler) {
		this.port = port;
		this.messageHandler = messageHandler;
	}

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private ChannelFuture channelFuture;

	public void start() throws Exception {
		// EventLoopGroup是用来处理IO操作的多线程事件循环器
		// bossGroup 用来接收进来的连接
		bossGroup = new NioEventLoopGroup();
		// workerGroup 用来处理已经被接收的连接
		workerGroup = new NioEventLoopGroup();
		try {
			// 启动 NIO 服务的辅助启动类
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					// 配置 Channel
					.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							// 注册handler
							ch.pipeline().addLast(new ServerInBoundHandler());
							ch.pipeline().addLast(new ServerOutBoundHandler());
							ch.pipeline().addLast(messageHandler);
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true)
					.childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_RCVBUF, 16 * 1024)
					.childOption(ChannelOption.SO_SNDBUF, 16 * 1024);

			// 绑定端口，开始接收进来的连接
			channelFuture = b.bind(port).sync();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void shutDown() throws InterruptedException {
		if (channelFuture != null) {
			channelFuture.channel().closeFuture().sync();
		}
		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
		}
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
		}
	}

}
