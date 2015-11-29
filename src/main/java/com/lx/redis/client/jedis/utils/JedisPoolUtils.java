package com.lx.redis.client.jedis.utils;

import java.net.URI;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 熟悉 Jedis Api<br/>
 * 
 * Jedis连接池工具类，真实环境<br/>
 * 
 * jedis pool 是基于apache common pool 实现
 * @author lx
 *
 */
public class JedisPoolUtils {

	public static final String DEFAULT_HOST = "127.0.0.1";
	public static final Integer DEFAULT_PORT = 6379;
	public static final int DEFAULT_TIMEOUT = 2000;
	public static final int DEFAULT_DATABASE = 0;
	
	private static JedisPool jedisPool;
	

	/**
	 * 获取一个Jedis对象
	 * @return jedis
	 */
	public static Jedis getJedis() {
		
		if(jedisPool == null) {
			createJedisPool(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_TIMEOUT);
		}
		
		// 确认jedisPool是否创建成功，如果常见jedisPool失败（如：连接redis服务失败），则每隔5秒重连一次，直到连上为止
		while(jedisPool == null) {
			createJedisPool(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_TIMEOUT);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(jedisPool != null) {
				break;
			}
		}
		return jedisPool.getResource();
	}
	
	/**
	 * 获取一个Jedis对象
	 * @return jedis
	 * @throws Exception 
	 */
	public static Jedis getJedis(String host, Integer port, Integer timeout) throws Exception {
		
		if(jedisPool == null) {
			createJedisPool(host, port, timeout);
		}
		return jedisPool.getResource();
	}
	
	/**
	 * 归还一个连接
	 * @param jedis
	 */
	public static void returnResource(Jedis resource) {
		
		if(jedisPool != null) {
			jedisPool.returnResource(resource);
		}
	}
	
	/**
	 * 创建Jedsi连接池配置对象
	 * @return
	 */
	public static JedisPoolConfig createJedisPoolConfig() {
		
		// 创建Jedis连接池配置对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
		// 真实环境中，配置参数将抽取到配置文件中
		poolConfig.setMaxTotal(10);						// 最大连接数
		poolConfig.setMaxWaitMillis(1000);				// 最大阻塞时间（毫秒）
		poolConfig.setMaxIdle(Integer.valueOf(100));	// 空闲连接数
		return poolConfig;
	}

	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param host
	 */
	public static synchronized void createJedisPool(String host) {
		jedisPool = new JedisPool(host);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param uri
	 */
	public static synchronized void createJedisPool(URI uri) {
		jedisPool = new JedisPool(uri);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param uri
	 * @param timeout
	 */
	public static synchronized void createJedisPool(URI uri, Integer timeout) {
		jedisPool = new JedisPool(uri, timeout);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param poolConfig
	 * @param host
	 */
	public static synchronized void createJedisPool(JedisPoolConfig poolConfig, String host) {
		jedisPool = new JedisPool(poolConfig, host);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param poolConfig
	 * @param uri
	 */
	public static synchronized void createJedisPool(JedisPoolConfig poolConfig, URI uri) {
		jedisPool = new JedisPool(poolConfig, uri);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param host
	 * @param port
	 */
	public static synchronized void createJedisPool(String host, Integer port) {
		createJedisPool(host, port, DEFAULT_TIMEOUT, null, DEFAULT_DATABASE, null);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param host
	 * @param port
	 * @param timeout
	 */
	public static synchronized void createJedisPool(String host, Integer port, Integer timeout) {
		createJedisPool(host, port, timeout, null, DEFAULT_DATABASE, null);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param host
	 * @param port
	 * @param timeout
	 * @param password
	 */
	public static synchronized void createJedisPool(String host, Integer port, Integer timeout, String password) {
		createJedisPool(host, port, timeout, password, null, null);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param host
	 * @param port
	 * @param timeout
	 * @param password
	 * @param database
	 */
	public static synchronized void createJedisPool(String host, Integer port, Integer timeout, String password, Integer database) {
		createJedisPool(host, port, timeout, password, database, null);
	}
	
	/**
	 * 创建连接池，在多线程环境下同步初始化创建连接池
	 * @param host
	 * @param port
	 * @param timeout
	 * @param password
	 * @param database
	 * @param clientName
	 */
	public static synchronized void createJedisPool(String host, Integer port, Integer timeout, String password, Integer database, String clientName) {
		
		if(jedisPool == null) {
			JedisPoolConfig poolConfig = createJedisPoolConfig();
			jedisPool = new JedisPool(poolConfig, host, port, timeout, password, database, clientName);
		}
	}

}
