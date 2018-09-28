package com.kuaikai.game.common.tcp;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.msg.MessageFactory;
import com.kuaikai.game.common.tcp.channel.TcpServer;
import com.kuaikai.game.common.tcp.channel.ws.WebSocket;
import com.kuaikai.game.common.tcp.channel.ws.WebSocketServer;

public class GameServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameServer.class);

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("conf/log4j.properties");
		//XMLUtil.init();
		// 初始化配置
		//CfgMainManager.init();
		Properties properties = new Properties();
		properties.load(new FileInputStream("conf/conf.properties"));

		//WordFileterUtil.init("conf/wordFilter");

		// 初始化db
		//MySQLManager.getInstance().init(properties);
		// 初始化reids
		//RedissonManager.getInstance().init(properties);
		//GlobalEventManager.init();

		// 初始化GMManager
		//GmManager.getInstance().init(properties);
		// 初始化quartz
		//QuartzManager.init();
		// 需要初始化的manager
		//MainManager.getMainManager().init();
		
		Modules.init(properties.getProperty("module.packages").split(","));
		
		OnlineManager.init();

		MessageFactory.init(properties.getProperty("msg.handler.packages").split(","));
		// 启动socket
		int port = Integer.parseInt(properties.getProperty("port"));
		TcpServer.getTcpServer().start(port);

		// 启动websocket
		int webPort = Integer.parseInt(properties.getProperty("webPort"));
		Constructor<WebSocket> constructor = WebSocket.class.getConstructor();
		new WebSocketServer(constructor).run(webPort);
		// 启动成功后向redis注册服务器 放在最后
		//ServerInfoManager.registerServer(properties);
		LOGGER.info("game server start");
		//System.out.println("Card game server started.");
	}
}
