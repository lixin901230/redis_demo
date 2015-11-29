package com.lx.redis.client.jedis.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 通过Jedis客户端API操作Redis 数据
 * 
 * <ul>redis数据格式分为：
 * 	<li>1、string</li>
 * 	<li>2、hash</li>
 * 	<li>3、list</li>
 * 	<li>4、set</li>
 * 	<li>5、sorted set</li>
 * </ul>
 * 
 * @author lx
 *
 */
public class JedisTest {

	public Jedis jedis = null;
	
	@Before
	public void createJedis() {
		jedis = new Jedis("127.0.0.1", 6379, 2000);
	}
	
	/**
	 * jedis 操作字符串
	 */
	@Test
	public void testString() {
		
		/*// 设置一个字符串值
		jedis.set("name", "KeVinLi");
		
		// 取值
		String name = jedis.get("name");
		System.out.println("name="+name);
		
		// 拼接，注意：只有当存在该key时，才拼接待拼接的值到该key对于的值后面，若不存在该key，则直接设置待拼接的值作为该key的值
		jedis.append("name", "is good!");
		String _name = jedis.get("name");
		System.out.println("name append="+_name);
		
		// 删除键name
		long isSuccess = jedis.del("name");
		System.out.println(isSuccess);
		
		// 设置多个键值对
		jedis.mset("name2", "萧红", "sex", "女");
		System.out.println("name2："+jedis.get("name2")+"；sex："+jedis.get("sex"));
		
		// 取出多个键值 mget
		List<String> mget = jedis.mget("name2","sex");
		System.out.println("mget："+mget);
		
		jedis.set("age", "3");	//设置一个数字字符串
		// 将key的值（数字字符串）减去1；注意：若该key不存在，或者该key对应的值是一个错误类型的值（非数字字符串值），则redis会先设置该key的值为0然后再减去1
		jedis.decr("age");
		System.out.println(jedis.get("age"));
		
		// 将key的值（数字字符串）加1；注意：若该key不存在，或者该key对应的值是一个错误类型的值（非数字字符串值），则redis会先设置该key的值为0然后再加1
		jedis.incr("age");
		System.out.println(jedis.get("age"));*/
		
		// 删除所有数据库中的所键-值
//		jedis.flushAll();
		
		// 删除当前选中的数据库的所有键
//		jedis.flushDB();
	}
	
	/**
	 * jedis 操作Hash（MAP）
	 */
	@Test
	public void testMap() {
		
		// 准备数据
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("name", "kevinli");
		userMap.put("age", "25");
		userMap.put("sex", "男");
		
		// 存入map
		jedis.hmset("userMap", userMap);
		
		// 获取整个map集合
		Map<String, String> hgetAll = jedis.hgetAll("userMap");
		System.out.println(hgetAll);
		
		// 获取map中指定的元素值
		String hget = jedis.hget("userMap", "names");
		System.out.println("hget："+hget);
		
		// 取出map集合指定的一个或多个元素值，注意：第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
		List<String> rsmap = jedis.hmget("userMap", "name", "age", "sex");
		System.out.println(rsmap);
		
		// 删除map中的某个值
		jedis.hdel("userMap", "age");
		System.out.println(jedis.hmget("userMap", "age"));	//返回null，因为map中age属性被删除了
		
		// 验证存储的map中是否存在指定的属性元素
		boolean hasNameAttr = jedis.hexists("userMap", "name");
		System.out.println("has name："+hasNameAttr);
		boolean hasAgeAttr = jedis.hexists("userMap", "age");	//age被上面移除了，所以没有属性了age了
		System.out.println("has age："+hasAgeAttr);
		
		// 获取map节点元素个数
		long len = jedis.hlen("userMap");
		System.out.println("userMap："+len);
		
		// 获取map所有的key
		Set<String> hkeys = jedis.hkeys("userMap");
		System.out.println(hkeys);
		
		// 获取map所有的value
		List<String> hvals = jedis.hvals("userMap");
		System.out.println(hvals);
	}
	
	/**
	 * jedis操作List 
	 */
	@Test
	public void testList() {
		
		// 先清空userList
		jedis.del("names");
		System.out.println(jedis.lrange("names", 0, 1));//清空后检查是否清空
		
		// redis存储 list 数据
		jedis.lpush("names", "萧红");
		jedis.lpush("names", "韩威");
		jedis.lpush("names", "崇明");
		jedis.lpush("names", "肖央");
		
		// 获取list存储的元素个数
		Long llen = jedis.llen("names");
		System.out.println(llen);
		
		// 获取list 集合全部元素
		List<String> lrangeAll = jedis.lrange("names", 0, jedis.llen("names"));
		System.out.println(lrangeAll);//
	}
	
	/**
	 * jedis 操作 Set
	 */
	@Test
	public void testSet() {
		
		// redis 存储 set 数据
		jedis.sadd("user", "liuxiaolei");
		jedis.sadd("user", "wangming");
		jedis.sadd("user", "xiaoli");
		jedis.sadd("user", "who");
		
		// 获取所有的value
		Set<String> smembers = jedis.smembers("user");
		System.out.println(smembers);
		
		//判断 who 是否是user集合的元素；1：是，0：不是
		Boolean exis = jedis.sismember("animous", "who");
		System.out.println(exis);
		
		// 随机返回集合中的一个元素
		String randomElement = jedis.srandmember("user");
		System.out.println(randomElement);
		
		// 获取集合中的元素个数，若该key不存在，返回0
		Long setSize = jedis.scard("user");
		System.out.println(setSize);
		
		// 
		Set<String> sdiffSet = jedis.sdiff("user", "xiaoli", "who");
		System.out.println(sdiffSet);
	}
}
