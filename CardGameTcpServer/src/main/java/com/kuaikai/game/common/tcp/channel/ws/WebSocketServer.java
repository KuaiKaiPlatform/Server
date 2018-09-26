package com.kuaikai.game.common.tcp.channel.ws;

import java.lang.reflect.Constructor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.utils.SslUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

	private Constructor<? extends WebSocketServerHandler> constructor;

	public WebSocketServer(Constructor<? extends WebSocketServerHandler> constructor) {
		this.constructor = constructor;
	}

	public void run(int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();

			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// HttpServerCodec将请求和应答消息编码或解码为HTTP消息
							// 通常接收到的http是一个片段，如果想要完整接受一次请求所有数据，我们需要绑定HttpObjectAggregator
							// 然后就可以收到一个FullHttpRequest完整的请求信息了
							// ChunkedWriteHandler
							// 向客户端发送HTML5文件，主要用于支持浏览器和服务器进行WebSocket通信
							// WebSocketServerHandler自定义Handler
							SSLContext sslContext = SslUtil.createSSLContext("JKS", "conf/wss.jks", "1539159440023"); /// SslUtil自定义类
							final SSLEngine sslEngine = sslContext.createSSLEngine();
							sslEngine.setUseClientMode(false); // 是否使用客户端模式
							sslEngine.setNeedClientAuth(false);// 是否需要验证客户端
							ch.pipeline().addLast("ssl", new SslHandler(sslEngine));
							ch.pipeline().addLast("http-codec", new HttpServerCodec())
									.addLast("aggregator", new HttpObjectAggregator(65536)) // 定义缓冲大小
									.addLast("http-chunked", new ChunkedWriteHandler())
									.addLast("binaryhandler", new WebSocketOutBoundHandler())
									.addLast("handler", constructor.newInstance());
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true)
					.childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_RCVBUF, 16 * 1024)
					.childOption(ChannelOption.SO_SNDBUF, 16 * 1024);

			b.bind(port).sync();
		} catch (Exception e) {
			LOGGER.error("WebSocketServer.run@bind error", e);
			
		}
	}

}
