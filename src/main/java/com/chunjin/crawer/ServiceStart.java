package com.chunjin.crawer;

import com.chunjin.crawer.task.JPatentMainData;
import redis.clients.jedis.Jedis;
import sun.util.resources.cldr.zh.CurrencyNames_zh_Hans_HK;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;


public class ServiceStart {

  private static final ServiceStart service = new ServiceStart();

  public static ServiceStart getService() {
    return service;
  }

  public void start() {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    pool.execute(new JPatentMainData());
  }

  public static void main(String[] ars) {
    ServiceStart.getService().start();
    Jedis jedis = new Jedis("192.168.56.102", 6379);
    jedis.sadd("ID", "eeee");
    jedis.sadd("ID", "3232");
    System.out.println(jedis.smembers("ID"));
    System.out.println(jedis.sismember("ID", "44"));

  }
}
