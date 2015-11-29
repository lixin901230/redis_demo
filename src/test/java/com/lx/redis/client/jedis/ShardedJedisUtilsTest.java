package com.lx.redis.client.jedis;

import org.junit.Test;

import redis.clients.jedis.ShardedJedis;

import com.lx.redis.client.jedis.utils.ShardedJedisUtils;

public class ShardedJedisUtilsTest {

	@Test
	public void testCreateShardedJedis() {
		
		ShardedJedis shardedJedis = ShardedJedisUtils.createShardedJedis();
		shardedJedis.set("sharded_jedis_key1", "sharded_jedis_value1");
		System.out.println(shardedJedis.get("sharded_jedis_key1"));
	}
}
