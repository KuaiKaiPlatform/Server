package com.kuaikai.game.common.db;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedissonManager {

	private static final RedissonManager instance = new RedissonManager();
	private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);
	
    private static final long LOCK_WAIT_SECONDS = 3;
    private static final long LOCK_LEASE_SECONDS = 30;
	
	public static RedissonManager getInstance() {
		return instance;
	}

	public static RedissonClient getRedission() {
		return getInstance().getRedisson();
	}

	private RedissonClient redissonClient;

	public void init(Properties props) {
//		try {
//			RedisConstantKey.check();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		String redisHost = props.getProperty("redis.host");
		if (!redisHost.startsWith("http")) {
			redisHost = "http://" + redisHost;
		}
		String redisPort = props.getProperty("redis.port");
		String passWord = props.getProperty("redis.password");
		String redisDb = props.getProperty("redis.db");
		Config config = new Config();
		// 采用string编解码形式
		config.setCodec(new org.redisson.client.codec.StringCodec());
		SingleServerConfig singleServerConfig = config.useSingleServer();
		String address = String.format("%s:%s", redisHost, redisPort);
		singleServerConfig.setAddress(address);
		if (passWord != null && !passWord.equals("")) {
			singleServerConfig.setPassword(passWord);
		}
		singleServerConfig.setDatabase(Integer.parseInt(redisDb));
		redissonClient = Redisson.create(config);
	}

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("redis.host", "127.0.0.1");
		properties.put("redis.port", "6379");
		properties.put("redis.db", "2");
		instance.init(properties);
		
		System.out.println("finish");
	}

	public RedissonClient getRedisson() {
		return redissonClient;
	}

	public void destroy() {
		if (redissonClient != null) {
			redissonClient.shutdown();
		}
	}

	private final String STRING_ENCODE = "utf-8";

	public void addTopicListener(String channel, MessageListener<String> listener) {
    	RTopic<String> topic = redissonClient.getTopic(channel);
    	topic.addListener(listener);
    }

	public void publishTopic(String channel, String message) {
    	RTopic<String> topic = redissonClient.getTopic(channel);
    	topic.publish(message);
    }
	
	public RLock getLock(String lockName) {
		return redissonClient.getLock(lockName);
	}
	
	public boolean tryLock(RLock lock, long wait, long lease) {
		try {
			return lock.tryLock(wait, lease, TimeUnit.SECONDS);
		} catch(InterruptedException e) {
			logger.error(String.format("RedissonManager.tryLock@failed|lockName=%s|wait=%d|lease=%d", lock.getName(), wait, lease), e);
		} finally {
			lock.unlock();
		}
		return false;
	}

	public boolean tryLock(RLock lock) {
		return tryLock(lock, LOCK_WAIT_SECONDS, LOCK_LEASE_SECONDS);
	}
	
	/**
	 * 同步设置
	 * 
	 * @param key
	 * @param value
	 * @throws UnsupportedEncodingException
	 */
	public void set(String key, String value) throws Exception {
		RBinaryStream rBinaryStream = redissonClient.getBinaryStream(key);
		rBinaryStream.set(value.getBytes(STRING_ENCODE));
	}

	/**
	 * 同步获取
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String get(String key) throws Exception {
		RBinaryStream rBinaryStream = redissonClient.getBinaryStream(key);
		byte[] bytes = rBinaryStream.get();
		if (bytes == null) {
			return null;
		}
		return new String(bytes, STRING_ENCODE);
	}

	/**
	 * 删除key
	 * 
	 * @param key
	 * @return
	 */
	public boolean delKey(String key) {
		RBinaryStream rBinaryStream = redissonClient.getBinaryStream(key);
		return rBinaryStream.delete();
	}

	/**
	 * 异步设置
	 * 
	 * @param key
	 * @param value
	 * @return RFuture 调用RFuture.addListener 来监听结果返回
	 * @throws Exception
	 */
	public RFuture<?> asynSet(String key, String value) throws Exception {
		RBinaryStream rBinaryStream = redissonClient.getBinaryStream(key);
		return rBinaryStream.setAsync(value.getBytes(STRING_ENCODE));
	}

	/**
	 * 异步获取
	 * 
	 * @param key
	 * @return RFuture 调用RFuture.addListener 来监听结果返回
	 */
	public RFuture<byte[]> asynGet(String key) {
		RBinaryStream rBinaryStream = redissonClient.getBinaryStream(key);
		return rBinaryStream.getAsync();
	}
}
