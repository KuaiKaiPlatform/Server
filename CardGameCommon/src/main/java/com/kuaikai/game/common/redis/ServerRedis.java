package com.kuaikai.game.common.redis;

import java.util.Properties;

import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import com.kuaikai.game.common.db.RedissonManager;

public class ServerRedis {

	public static final class ServerInfo {
		public final String ip;
		public final int port;
		public final int webPort;

		public ServerInfo(String ip, int port, int webPort) {
			this.ip = ip;
			this.port = port;
			this.webPort = webPort;
		}
	}

	public static ServerInfo getServerInfo() {
		RedissonClient redissonClient = RedissonManager.getRedission();
		RScoredSortedSet<String> scoredSortedSet = redissonClient
				.getScoredSortedSet(RedisConstantKey.SERVER_ONLINE_SORT);
		String serverid = scoredSortedSet.first();
		return getServerInfoByServerid(serverid);
	}

	/**
	 * 增加或减少服务器在线人数
	 * 
	 * @param serverid
	 * @param num
	 *            可正可负
	 */
	public static void addServerOnlineNum(String serverid, int num) {
		RedissonClient redissonClient = RedissonManager.getRedission();
		RScoredSortedSet<String> scoredSortedSet = redissonClient
				.getScoredSortedSet(RedisConstantKey.SERVER_ONLINE_SORT);
		scoredSortedSet.addScore(serverid, num);
	}

	private static ServerInfo getServerInfoByServerid(String serverid) {
		if (serverid == null) {
			return null;
		}
		RedissonClient redissonClient = RedissonManager.getRedission();
		RMap<String, String> serverInfos = redissonClient.getMap(RedisConstantKey.SERVER_KEY_INDEX + serverid);
		String ip = serverInfos.get("ip");
		String portStr = serverInfos.get("port");
		String webPortStr = serverInfos.get("webPort");
		if (ip == null || portStr == null || webPortStr == null) {
			return null;
		}
		return new ServerInfo(ip, Integer.parseInt(portStr), Integer.parseInt(webPortStr));
	}

	public static void registerServer(Properties properties) {
		String serverid = properties.getProperty("serverid");
		String host = properties.getProperty("host");
		String port = properties.getProperty("port");
		String webPort = properties.getProperty("webPort");
		RedissonClient redissonClient = RedissonManager.getRedission();
		RMap<String, String> serverInfos = redissonClient.getMap(RedisConstantKey.SERVER_KEY_INDEX + serverid);
		serverInfos.put("ip", host);
		serverInfos.put("port", port);
		serverInfos.put("webPort", webPort);
		addServerOnlineNum(serverid, 0);
	}

}
