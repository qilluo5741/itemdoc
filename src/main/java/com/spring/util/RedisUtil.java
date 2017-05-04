package com.spring.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	// Redis服务器IP
	private static String address;
	// Redis的端口号
	private static Integer port;
	// 访问密码
	// private static String AUTH = "admin";
	private static JedisPool jedisPool = null;
	private static JedisPoolConfig config;

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		Jedis resource = null;
		try {
			if (jedisPool != null) {
				resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			returnResource(resource);
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
	//创建连接池
	public void initConfig() {
		try {
			jedisPool = new JedisPool(config, address, port, 10000);
		} catch (Exception e) {
			System.out.println("创建redis连接池报错哒！");
			e.printStackTrace();
		}
	}
	//引入配置对象
	public static void setConfig(JedisPoolConfig config) {
		RedisUtil.config = config;
	}

	public static void setAddress(String address) {
		RedisUtil.address = address;
	}

	public static void setPort(Integer port) {
		RedisUtil.port = port;
	}
}