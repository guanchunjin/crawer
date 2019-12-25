package com.chunjin.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedisPool {


  private static final String HOST = "192.168.56.102";
  private static final int PORT = 6379;
  private JedisPoolConfig poolConfig;
  private JedisPool pool;

  public RedisPool() {
    poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(1000);           // 最大连接数
    poolConfig.setMaxIdle(32);              // 最大空闲连接数
    poolConfig.setMaxWaitMillis(100 * 1000);  // 最大等待时间
    poolConfig.setTestOnBorrow(true);       // 检查连接可用性, 确保获取的redis实例可用
    this.pool = new JedisPool(poolConfig, HOST, PORT);
  }

  public void execute(CallWithJedis caller) {
    try (Jedis jedis = pool.getResource()) {
      caller.call(jedis);
    }


  }

  public static <E> Set<E> union(Set<E> s1, Set<E> s2) {


    Set<E> result = new HashSet<>(s1);
    result.addAll(s2);
    return result;
  }


}



