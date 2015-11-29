package com.lx.redis.client.jedis.utils;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisPoolUtils {
	
	/**
	 * 获取分片jedis对象ShardedJedis
	 * @return
	 */
	public static ShardedJedis getShardedJedis() {
		ShardedJedisPool jedisPool = createShardedJedisPool();
		ShardedJedis shardedJedis = jedisPool.getResource();
		return shardedJedis;
	}

	/**
	 * 获取jedis分片连接池对象 ShardedJedisPool
	 * @return
	 */
	public static ShardedJedisPool createShardedJedisPool() {
		
		JedisPoolConfig poolConfig = createJedisPoolConfig();
		List<JedisShardInfo> shards = getJedisShardedInfoList();
		ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shards);
		return pool;
	}
	
	/**
	 * 创建Jedsi连接池配置对象
	 * @return
	 */
	private static JedisPoolConfig createJedisPoolConfig() {
		
		// 创建Jedis连接池配置对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
		// 真实环境中，配置参数将抽取到配置文件中
		poolConfig.setMaxTotal(10);						// 最大连接数
		poolConfig.setMaxWaitMillis(1000);				// 最大阻塞时间（毫秒）
		poolConfig.setMaxIdle(Integer.valueOf(100));	// 空闲连接数
		return poolConfig;
	}
	
	public static List<JedisShardInfo> getJedisShardedInfoList() {
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo si = new JedisShardInfo("localhost", 6379);
        shards.add(si);
        
        si = new JedisShardInfo("localhost", 6380);
        shards.add(si);
        return shards;
	}
}
