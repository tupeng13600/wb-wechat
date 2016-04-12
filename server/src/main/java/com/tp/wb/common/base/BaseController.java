package com.tp.wb.common.base;

import com.tp.wb.common.config.WechatConfig;
import com.tt.wb.util.DecriptUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Tupeng on 2016/4/11.
 */
public class BaseController {

    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 对消息进行验证，是否来自于微信
     * @param signature     微信加密签名
     * @param timestamp     时间戳
     * @param nonce         随机数
     * @param echostr       随机字符串
     * @param response
     */
    protected void signature(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response){
        String[] str = { wechatConfig.getToken(), timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = DecriptUtil.SHA1(bigStr);
        // 确认请求来至微信
        if (digest.equals(signature)) {
            out(response, echostr);
        }
    }
    protected void out(HttpServletResponse response, String value) {
        try {
            response.getWriter().print(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
