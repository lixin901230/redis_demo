package com.lx.redis.client.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.lx.redis.client.jedis.utils.JedisUtils;


public class JedisUtilsTest {

	private static final String HOST = "127.0.0.1";
	public static final int PORT = 6379;
	public static final int TIMEOUT = 2000;
	
	@Test
	public void testCreateJedis() {
		
		Jedis jedis = JedisUtils.createJedis(HOST);
		jedis.set("redis_k1", "redis_v1");
		String v1 = jedis.get("redis_k1");
		System.out.println("port:"+jedis.getClient().getPort()+"-------redis_k1="+v1);
	}
	
	@Test
	public void testCreateJedis2() {
		
		Jedis jedis = JedisUtils.createJedis(HOST, PORT);
		jedis.set("redis_k1", "redis_v2");
		String v1 = jedis.get("redis_k1");
		System.out.println("port:"+jedis.getClient().getPort()+"-------redis_k1="+v1);
	}
	
	@Test
	public void testCreateJedis3() {
		
		Jedis jedis = JedisUtils.createJedis(HOST, PORT, TIMEOUT);
		jedis.set("redis_k1", "redis_v3");
		String v1 = jedis.get("redis_k1");
		System.out.println("port:"+jedis.getClient().getPort()+"-------redis_k1="+v1);
	}
	
	@Test
	public void testCreateJedis4() {
		
		Jedis jedis = JedisUtils.createJedis(HOST, PORT, TIMEOUT, "");
		jedis.set("redis_k1", "redis_v4");
		String v1 = jedis.get("redis_k1");
		System.out.println("port:"+jedis.getClient().getPort()+"-------redis_k1="+v1);
	}

}
