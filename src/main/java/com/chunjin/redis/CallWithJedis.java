package com.chunjin.redis;

import redis.clients.jedis.Jedis;

interface CallWithJedis {

  void call(Jedis jedis);


}

