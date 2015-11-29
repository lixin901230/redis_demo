package com.lx.redis.client.jedis;

import org.junit.Test;

import redis.clients.jedis.ShardedJedis;

import com.lx.redis.client.jedis.utils.ShardedJedisPoolUtils;

public class ShardedJedisPoolUtilsTest {

	@Test
	public void testCreateShardedJedisPool() {
		
		ShardedJedis shardedJedis = ShardedJedisPoolUtils.getShardedJedis();
		shardedJedis.set("k1", "v1");
		System.out.println(shardedJedis.get("k1"));
	}
}
