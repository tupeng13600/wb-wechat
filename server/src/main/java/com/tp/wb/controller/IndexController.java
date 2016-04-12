package com.tp.wb.controller;

import com.tp.wb.common.base.BaseController;
import com.tt.wb.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tupeng on 2016/4/11.
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     *
     * @param signature     微信加密签名
     * @param timestamp     时间戳
     * @param nonce         随机数
     * @param echostr       随机字符串
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public void entrance(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) {

        if(!StringUtil.isEmpty(echostr)) {
            //第一次接入对信息进行校验
            this.signature(signature, timestamp, nonce, echostr, response);
        }else {

        }
    }

}
