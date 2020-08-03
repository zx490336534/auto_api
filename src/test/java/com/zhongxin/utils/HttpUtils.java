package com.zhongxin.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
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

public class HttpUtils {
    /*
     * 发送get请求
     * @param url       接口地址
     * @throws
     * */
    public static void get(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        get.setHeader("X-Lemonban-Media-Type", "lemonban.v1");
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
    public static void post(String url, String params) throws Exception {
        HttpPost post = new HttpPost(url);
        post.setHeader("X-Lemonban-Media-Type", "lemonban.v1");
        post.setHeader("Content-Type", "application/json");
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
    public static void patch(String url, String params) throws Exception {
        HttpPatch patch = new HttpPatch(url);
        patch.setHeader("X-Lemonban-Media-Type", "lemonban.v1");
        patch.setHeader("Content-Type", "application/json");
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
}
