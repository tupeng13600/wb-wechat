package com.tt.wb.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Tupeng on 2016/4/12.
 */
public class HttpUtil {

    /**
     * 获取
     * @param url	post请求的地址
     * @param params	post请求需要携带的参数
     * @return	返回服务器返回的数据内容（不包含头）
     * @throws Exception
     */
    public static String post(String url, List<NameValuePair> params) throws Exception {
        // 创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        // 服务端接收编码(这才是乱码关键所在)
        httpPost.setHeader("Accept-Charset", "UTF-8,utf-8;q=0.7,*;q=0.7");
        // 如果有参数,则设置参数
        if (null != params && !params.isEmpty()) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // 设置链接超时时间
        Builder config = RequestConfig.custom();
        config.setConnectTimeout(3000);
        httpPost.setConfig(config.build());
        // 获取请求返回对象
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        String result = StringUtil.InputStream2String(is);
        if (null != response) {
            // 释放连接
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * get请求
     * @param url 请求地址，后面可以拼装参数
     * @return	返回服务器返回的数据内容（不包含头）
     * @throws Exception
     */
    public static String get(String url) {
        // 创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        // 服务端接收编码(这才是乱码关键所在)
        httpGet.setHeader("Accept-Charset", "UTF-8,utf-8;q=0.7,*;q=0.7");
        // 如果有参数,则设置参数
        // 设置链接超时时间
        Builder config = RequestConfig.custom();
        config.setConnectTimeout(3000);
        httpGet.setConfig(config.build());
        // 获取请求返回对象
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result = "";
        try {
            InputStream is = entity.getContent();
            result = StringUtil.InputStream2String(is);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (null != response) {
            // 释放连接
            httpGet.releaseConnection();
        }
        return result;
    }

}
