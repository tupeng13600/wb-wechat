package com.tp.wb.common.config;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Tupeng on 2016/4/11.
 */
public class JedisConfig extends JedisPoolConfig {

    private String host;

    private Integer port;

    private Integer timeout;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
