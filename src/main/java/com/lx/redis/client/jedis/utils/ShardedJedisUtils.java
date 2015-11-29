package com.lx.redis.client.jedis.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

/**
 * jedis 分片
 * @author lx
 *
 */
public class ShardedJedisUtils {
	
	public static final String DEFAULT_HOST = "127.0.0.1";
	public static final Integer DEFAULT_PORT = 6379;
	public static final Integer DEFAULT_TIMEOUT = 2000;
	
	public static ShardedJedis createShardedJedis() {
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo jsi = createJedisShardInfo("127.0.0.1", 6379);
		shards.add(jsi);
		
//		JedisShardInfo jsi2 = createJedisShardInfo("127.0.0.1", 6380);
//		shards.add(jsi2);
		
		ShardedJedis shardedJedis = new ShardedJedis(shards);
		return shardedJedis;
	}

	/**
	 * 创建Jedis分片对象
	 * @param host
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(String host) {
		JedisShardInfo jedisShard = new JedisShardInfo(host);
		return jedisShard;
	}
	
	/**
	 * 创建Jedis分片对象
	 * @param uri
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(URI uri) {
		JedisShardInfo jedisShard = new JedisShardInfo(uri);
		return jedisShard;
	}
	
	/**
	 * 创建Jedis分片对象
	 * @param host
	 * @param port
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(String host, Integer port) {
		JedisShardInfo jedisShard = new JedisShardInfo(host, port);
		return jedisShard;
	}
	
	/**
	 * 创建Jedis分片对象
	 * @param host
	 * @param name
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(String host, String name) {
		JedisShardInfo jedisShard = new JedisShardInfo(host, name);
		return jedisShard;
	}
	
	/**
	 * 创建Jedis分片对象
	 * @param host
	 * @param port
	 * @param timeout
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(String host, Integer port, Integer timeout) {
		JedisShardInfo jedisShard = new JedisShardInfo(host, port, timeout);
		return jedisShard;
	}
	
	/**
	 * 创建Jedis分片对象
	 * @param host
	 * @param port
	 * @param name
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(String host, Integer port, String name) {
		JedisShardInfo jedisShard = new JedisShardInfo(host, port, name);
		return jedisShard;
	}
	
	/**
	 * 创建Jedis分片对象
	 * @param host
	 * @param port
	 * @param timeout
	 * @param weight
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(String host, Integer port, Integer timeout, Integer weight) {
		JedisShardInfo jedisShard = new JedisShardInfo(host, port, timeout, weight);
		return jedisShard;
	}
	
	/**
	 * 创建Jedis分片对象
	 * @param host
	 * @param port
	 * @param timeout
	 * @param name
	 * @return
	 */
	public static JedisShardInfo createJedisShardInfo(String host, Integer port, Integer timeout, String name) {
		JedisShardInfo jedisShard = new JedisShardInfo(host, port, timeout, name);
		return jedisShard;
	}
}
