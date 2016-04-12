package com.tp.wb.timertask;

import com.google.gson.Gson;
import com.tp.wb.common.cache.JedisCache;
import com.tp.wb.common.config.JedisConfig;
import com.tp.wb.common.config.WechatConfig;
import com.tp.wb.common.constant.CacheConstant;
import com.tt.wb.util.HttpUtil;
import com.tt.wb.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Tupeng on 2016/4/11.
 */
public class AccessTokenManager {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenManager.class);

    private static final String REQTOKENURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private WechatConfig wechatConfig;
    private JedisCache jedisCache;

    public AccessTokenManager(WechatConfig wechatConfig, JedisCache jedisCache){
        this.wechatConfig = wechatConfig;
        this.jedisCache = jedisCache;
        String appSecret = wechatConfig.getAppSecret();
        String appId = wechatConfig.getAppID();
        generateToken(appSecret, appId);
    }

    @Scheduled(cron = "0 0 */2 * * ?")
    public void updateToken() {
        String appSecret = wechatConfig.getAppSecret();
        String appId = wechatConfig.getAppID();
        generateToken(appSecret, appId);
    }

    /**
     * 生成token
     */
    private void generateToken(String appSecret, String appId){
        String requestUrlWithArgs = REQTOKENURL + "&appid=" + appId + "&secret=" + appSecret;
        String responseEntity = HttpUtil.get(requestUrlWithArgs);
        if(!StringUtil.isEmpty(responseEntity)) {
            Map<String, Object> tokenMap = new Gson().fromJson(responseEntity, Map.class);
            if(null != tokenMap) {
                String accessToken = (String) tokenMap.get("access_token");
                logger.info("generate access_token success : " + accessToken);
                //保存accesstoken
                jedisCache.hashSet(CacheConstant.WECHAT_KEY, CacheConstant.WECHAT_TOKEN_FIELD, accessToken);
            }
        }
    }
}
