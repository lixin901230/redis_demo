package com.lx.redis.client.jedis.utils;

import redis.clients.jedis.Jedis;

/**
 * 熟悉Jedis Api<br/>
 * 
 * 一个简单的Jedis连接工具类，但不适用于生产
 * @author lx
 *
 */
public class JedisUtils {
	
	public static final int DEFAULT_PORT = 6379;
	public static final int DEFAULT_TIMEOUT = 2000;

	public static Jedis createJedis(String host) {
		Jedis jedis = new Jedis(host);
		return jedis;
	}
	
	public static Jedis createJedis(String host, Integer port) {
		return createJedis(host, port, null, null);
	}
	
	public static Jedis createJedis(String host, Integer port, Integer timeout) {
		return createJedis(host, port, timeout, null);
	}
	
	public static Jedis createJedis(String host, Integer port, String password) throws Exception {
		if(password == null || "".equals(password)) {
			throw new Exception("参数错误，password不能为空");
		}
		return createJedis(host, port, null, password);
	}

	public static Jedis createJedis(String host, Integer port, Integer timeout, String password) {
		
		Jedis jedis = null;
		if(host != null && !"".trim().equals(host)) {
			
			if(port == null) {
				port = DEFAULT_PORT;
			}
			if(timeout == null) {
				timeout = DEFAULT_TIMEOUT;
			}
			jedis = new Jedis(host, port, timeout);
			if(jedis != null && password != null && !"".equals(password.trim())) {
				jedis.auth(password);
			}
		}
		return jedis;
	}
}
