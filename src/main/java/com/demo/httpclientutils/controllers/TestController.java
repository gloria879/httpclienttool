package com.demo.httpclientutils.controllers;

import com.alibaba.fastjson.JSONObject;
import com.demo.httpclientutils.utils.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class TestController {

    @Autowired
    RestTemplateUtil restTemplateUtil;

    @RequestMapping("/hello")
    public String hello() {
        return "hello springboot!";
    }

    @ResponseBody
    @RequestMapping(value="/test", method = RequestMethod.GET)
    public JSONObject testHttpClient() throws IOException {
        Map<String, Object> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        // 测试restTemplate发送get请求
        String url = "http://ip-api.com/json";
        JSONObject jsonObject = restTemplateUtil.get(url, params, headers);
        for (String key: jsonObject.keySet()) {
            System.out.println(key + " " + jsonObject.get(key));
        }

        return jsonObject;
    }


}
