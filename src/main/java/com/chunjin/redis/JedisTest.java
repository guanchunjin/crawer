package com.chunjin.redis;

public class JedisTest {

  public static void main(String[] args) {
    RedisPool redis = new RedisPool();
    redis.execute(jedis -> {

    });
  }
}
