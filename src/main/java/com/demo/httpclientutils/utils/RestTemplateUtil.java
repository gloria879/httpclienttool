package com.demo.httpclientutils.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestTemplateUtil {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求
     * @param url 请求url
     * @param queryParams 请求参数
     * @return
     * @throws IOException
     */
    public JSONObject get(String url, Map<String, Object> queryParams) throws IOException {
        return get(url, queryParams, new HashMap<>(1));
    }

    /**
     * get请求
     * @param url
     * @param queryParams
     * @param headerParams 请求头，headers.put("token", "xxxxxxxxxxxxxxxxxx")
     * @return
     * @throws IOException
     */
    public JSONObject get(String url, Map<String, Object> queryParams, Map<String, String> headerParams) throws IOException {
        String tempUrl = setParamsByAppendUrl(queryParams, url);
        HttpHeaders headers = new HttpHeaders();
        headerParams.forEach(headers::add);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(tempUrl, HttpMethod.GET, httpEntity, String.class);
        return JSONObject.parseObject(response.getBody());
    }

    /**
     * post
     * @param url
     * @param json 参数
     * @param headerParams 请求头
     * @return
     */
    public JSONObject post(String url, String json, Map<String, String> headerParams) {
        HttpHeaders headers = new HttpHeaders();
        headerParams.forEach(headers::add);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return JSONObject.parseObject(response.getBody());
    }

    /**
     * 处理get请求参数
     * @param queryParams
     * @param url
     * @return
     */
    private String setParamsByAppendUrl(Map<String, Object> queryParams, String url) {
        // ?name=a&passwd=b
        if (queryParams == null || queryParams.isEmpty()) {
            return url;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                stringBuilder.append("&").append(entry.getKey()).append("=");
                stringBuilder.append(entry.getValue());
            }
            if (!url.contains("?")) {
                stringBuilder.deleteCharAt(0).insert(0, "?");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url + stringBuilder;
    }
}
