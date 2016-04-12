package com.tp.wb.common.cache;

import com.tp.wb.common.config.JedisConfig;
import com.tp.wb.common.constant.CacheConstant;
import redis.clients.jedis.Jedis;

/**
 * Created by Tupeng on 2016/4/11.
 */
public class JedisCache extends JedisManager {

    public JedisCache(JedisConfig jedisConfig) {
        super(jedisConfig);
    }


    /**
     * 添加一条数据，若key不存在则创建，field存在则覆盖
     * @param key
     * @param field
     * @param value
     */
    public void hashSet(String key, String field, String value) {
        Jedis jedis = null;
        try{
            jedis = this.getInstance();
            jedis.hset(key, field, value);
        }finally {
            if(null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 获取指定的值
     * @param key
     * @param field
     * @return
     */
    public String hashGet(String key, String field) {
        Jedis jedis = null;
        try{
            jedis = this.getInstance();
            return jedis.hget(key, field);
        }finally {
            if(null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 删除指定的field，不存在的field将自动被忽略
     * @param key
     * @param fields
     */
    public void hashDel(String key, String...fields){
        Jedis jedis = null;
        try{
            jedis = this.getInstance();
            jedis.hdel(key, fields);
        }finally {
            if(null != jedis) {
                jedis.close();
            }
        }
    }

}
