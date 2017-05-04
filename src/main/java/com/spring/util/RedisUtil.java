package com.spring.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	// Redis������IP
	private static String address;
	// Redis�Ķ˿ں�
	private static Integer port;
	// ��������
	// private static String AUTH = "admin";
	private static JedisPool jedisPool = null;
	private static JedisPoolConfig config;

	/**
	 * ��ȡJedisʵ��
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
	 * �ͷ�jedis��Դ
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
	//�������ӳ�
	public void initConfig() {
		try {
			jedisPool = new JedisPool(config, address, port, 10000);
		} catch (Exception e) {
			System.out.println("����redis���ӳر����գ�");
			e.printStackTrace();
		}
	}
	//�������ö���
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