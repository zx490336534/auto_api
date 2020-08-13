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
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    /**
     * 根据请求参数对象
     */
    public static String call(CaseInfo caseInfo, Map<String, String> headers) {
        String responseBody = "";
        try {
            String params = caseInfo.getParams();
            String url = caseInfo.getUrl();
            String method = caseInfo.getMethod();
            String contentType = caseInfo.getContentType();

            if ("form".equalsIgnoreCase(contentType)) {
                params = jsonStr2KeyValueStr(params);
                headers.put("Content-Type", "application/x-www-form-urlencoded");
            }

            if ("post".equalsIgnoreCase(method)) {
                responseBody = HttpUtils.post(url, params, headers);
            } else if ("get".equalsIgnoreCase(method)) {
                responseBody = HttpUtils.get(url, headers);
            } else if ("patch".equalsIgnoreCase(method)) {
                responseBody = HttpUtils.patch(url, params, headers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBody;
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
    public static String get(String url, Map<String, String> headers) throws Exception {
        HttpGet get = new HttpGet(url);
        setHeaders(headers, get);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        return printResponse(response);
    }


    /*
     * 发送一个post请求
     * @param url        接口地址
     * @param params     接口参数
     * @throws
     * */
    public static String post(String url, String params, Map<String, String> headers) throws Exception {
        HttpPost post = new HttpPost(url);
        setHeaders(headers, post);
        StringEntity body = new StringEntity(params, "utf-8");
        post.setEntity(body);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
        return printResponse(response);
    }


    /*
     * 发送一个patch请求
     * @param url        接口地址
     * @param params     接口参数
     * @throws
     * */
    public static String patch(String url, String params, Map<String, String> headers) throws Exception {
        HttpPatch patch = new HttpPatch(url);
        setHeaders(headers, patch);
        StringEntity body = new StringEntity(params, "utf-8");
        patch.setEntity(body);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(patch);
        return printResponse(response);
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
        logger.info(statusCode);
        Header[] allHeaders = response.getAllHeaders();
        logger.info(Arrays.toString(allHeaders));
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        logger.info(body);
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
