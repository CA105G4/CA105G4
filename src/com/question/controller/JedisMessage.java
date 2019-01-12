package com.question.controller;

import java.util.List;
import redis.clients.jedis.Jedis;

public class JedisMessage {
	public static void saveMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.auth("123456");
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);

		jedis.close();
	}
	
	public static List<String> getMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		System.out.println("key:" + key);
		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.auth("123456");
		List<String> historyData = jedis.lrange(key, 0, jedis.llen(key) - 1);
		jedis.close();
		return historyData;
	}
}
