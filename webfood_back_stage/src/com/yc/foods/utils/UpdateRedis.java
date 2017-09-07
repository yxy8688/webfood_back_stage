package com.yc.foods.utils;


import java.util.Set;



import redis.clients.jedis.Jedis;

public class UpdateRedis {
	
	public void updateRedis(){
		Jedis jedis=new Jedis("192.168.254.128");
		
	
		Set<String> set=jedis.keys("*");
		for(String a:set){
			jedis.del(a);
		}
		System.out.println("运行了。。。");
		
	}

}
