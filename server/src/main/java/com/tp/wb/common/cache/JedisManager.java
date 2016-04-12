package com.tp.wb.common.cache;

import com.tp.wb.common.config.JedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Tupeng on 2016/4/11.
 */
public class JedisManager {

    private static Logger logger = LoggerFactory.getLogger(JedisManager.class);

    private JedisPool jedisPool;

    public  JedisManager(JedisConfig jedisConfig) {
        if(null == jedisConfig) {
            logger.error("jedisPool is null!!!!!!!!!!");
            throw new RuntimeException("jedisPool is null");
        }
        jedisPool = new JedisPool(jedisConfig, jedisConfig.getHost(), jedisConfig.getPort(), jedisConfig.getTimeout(), jedisConfig.getPassword());
    }

    public Jedis getInstance(){
        return jedisPool.getResource();
    }

    public void closePool(){
        if(null != jedisPool) {
            jedisPool.close();
        }
    }

    public void closeJedis(Jedis jedis) {
        if(null != jedis) {
            jedis.close();
        }
    }

}
