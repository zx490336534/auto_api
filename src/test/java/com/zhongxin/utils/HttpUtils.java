package com.zhongxin.utils;

import com.alibaba.fastjson.JSONObject;
import com.zhongxin.pojo.CaseInfo;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    /**
     * 根据请求参数对象
     * */
    public static void call(CaseInfo caseInfo) {
        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("X-Lemonban-Media-Type", "lemonban.v1");

            String params = caseInfo.getParams();
            String url = caseInfo.getUrl();
            String method = caseInfo.getMethod();
            String contentType = caseInfo.getContentType();

            if ("json".equals(contentType)) {
                headers.put("Content-Type", "application/json");
            } else if ("form".equals(contentType)) {
                params = jsonStr2KeyValueStr(params);
                headers.put("Content-Type", "application/x-www-form-urlencoded");
            }

            if ("post".equals(method)) {
                HttpUtils.post(url, params, headers);
            } else if ("get".equals(method)) {
                HttpUtils.get(url, headers);
            } else if ("patch".equals(method)) {
                HttpUtils.patch(url, params, headers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * json字符串转换成key=value
     * 例如:{"mobilephone":"13877788811","pwd":"12345678"} => mobilephone=13877788811&pwd=12345678
     *
     * @param json Json字符串
     * @return
     */
    private static String jsonStr2KeyValueStr(String json) {
        Map<String, String> map = JSONObject.parseObject(json, Map.class);
        String formParams = "";
        for (String key : map.keySet()) {
            formParams += key + "=" + map.get(key) + "&";
        }
        return formParams.substring(0, formParams.length() - 1);
    }


    /*
     * 发送get请求
     * @param url       接口地址
     * @throws
     * */
    public static void get(String url, Map<String, String> headers) throws Exception {
        HttpGet get = new HttpGet(url);
        setHeaders(headers, get);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        printResponse(response);
    }


    /*
     * 发送一个post请求
     * @param url        接口地址
     * @param params     接口参数
     * @throws
     * */
    public static void post(String url, String params, Map<String, String> headers) throws Exception {
        HttpPost post = new HttpPost(url);
        setHeaders(headers, post);
        StringEntity body = new StringEntity(params, "utf-8");
        post.setEntity(body);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
        printResponse(response);
    }


    /*
     * 发送一个patch请求
     * @param url        接口地址
     * @param params     接口参数
     * @throws
     * */
    public static void patch(String url, String params, Map<String, String> headers) throws Exception {
        HttpPatch patch = new HttpPatch(url);
        setHeaders(headers, patch);
        StringEntity body = new StringEntity(params, "utf-8");
        patch.setEntity(body);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(patch);
        printResponse(response);
    }

    /**
     * 打印响应
     *
     * @param response 响应对象
     * @return
     * @throws IOException
     */
    private static String printResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        Header[] allHeaders = response.getAllHeaders();
        System.out.println(Arrays.toString(allHeaders));
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println(body);
        return body;
    }

    /**
     * 设置请求头
     *
     * @param headers 包含了请求头的Map集合
     * @param request 请求类型
     */
    private static void setHeaders(Map<String, String> headers, HttpRequest request) {
        Set<String> keySet = headers.keySet();
        for (String key : keySet) {
            request.setHeader(key, headers.get(key));
        }
    }
}
