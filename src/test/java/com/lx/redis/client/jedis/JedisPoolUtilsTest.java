package com.lx.redis.client.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.lx.redis.client.jedis.utils.JedisPoolUtils;

/**
 * Jedis 连接池工具类
 * @author lx
 *
 */
public class JedisPoolUtilsTest {

	@Test
	public void testGetJedis1() {
		Jedis jedis = JedisPoolUtils.getJedis();
		jedis.set("jedis_pool_k1", "v1");
		System.out.println("Port="+jedis.getClient().getPort()+"----jedis_pool_k1="+jedis.get("jedis_pool_k1"));
	}
	
	@Test
	public void testGetJedis2() {
		Jedis jedis = JedisPoolUtils.getJedis();
		jedis.set("jedis_pool_k1", "v2");
		System.out.println("Port="+jedis.getClient().getPort()+"----jedis_pool_k1="+jedis.get("jedis_pool_k1"));
	}
	
}
