package com.demo.httpclientutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.httpclientutils.beans.User;
import com.demo.httpclientutils.utils.HttpClientUtil;
import com.demo.httpclientutils.utils.RestTemplateUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientMain {

    public static void main(String[] args) throws IOException {

        // RestTemplate发送请求测试
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        Map<String, Object> restParams = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

        String url = "http://ip-api.com/json";
//        JSONObject jsonObject = restTemplateUtil.get(url, restParams, headers);
//        for (String key: jsonObject.keySet()) {
//            System.out.println(key + " " + jsonObject.get(key));
//        }
        System.out.println("===============https Get=============================");

        // HttpClient发送请求测试
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Map<String, String> httpParams2 = new HashMap<>();
        Map<String, String> httpHeaders = new HashMap<>();
        String httpUrl = "https://sso.pingan.com.cn";

        String res = httpClientUtil.get(httpUrl, httpParams2, httpHeaders, true);
        System.out.println(res);

        System.out.println("====================http postJson==========================");

//        Map<String, String> httpPostParams = new HashMap<>();
//        String httpPostUrl = "http://x.x.x.x/gologin";
//        User user = new User("chenxiaoli879", "xxxxxx");
//
//        String postRes = httpClientUtil.postJson(httpPostUrl, httpPostParams, headers, JSON.toJSONString(user), false);
//        System.out.println(postRes);

    }
}
