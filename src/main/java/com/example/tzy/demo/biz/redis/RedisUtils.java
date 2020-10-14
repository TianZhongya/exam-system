package com.example.tzy.demo.biz.redis;

import static com.example.tzy.demo.biz.redis.RedisConstants.SEPARATOR;

/**
 * @author: Tianzy
 * @create: 2020-10-14 22:34
 **/
public class RedisUtils {
    private RedisUtils() {
    }

    public static String key(String... key) {
        return String.join(SEPARATOR, key);
    }
}
