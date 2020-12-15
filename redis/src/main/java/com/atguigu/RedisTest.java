package com.atguigu;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author hxd  Email：llphxdgg@gmail.com
 * @description
 * @create 2020-12-16 2:43
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("hxd03", 6379);
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }


        jedis.close();
    }
}
