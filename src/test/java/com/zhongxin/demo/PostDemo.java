package com.zhongxin.demo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class PostDemo {
    public static void main(String[] args) throws IOException {
        /*
         * 发送post请求
         * 1. 创建请求对象
         * 2. 设置请求方法
         * 3. 设置接口url地址
         * 4. 设置请求头
         * 5. 设置请求体（接口参数）
         * 6. 点击发送
         * 7. 获取响应对象
         * 8. 格式化响应对象（响应状态码，响应头，响应体）
         * */
        // 1+2+3
        HttpPost post = new HttpPost("http://api.lemonban.com/futureloan/member/recharge");
        // 4
        post.setHeader("X-Lemonban-Media-Type", "lemonban.v1");
        post.setHeader("Content-Type", "application/json");
        // 5
        StringEntity body = new StringEntity("{\"member_id\":2060127,\"amount\":1}", "utf-8");
        post.setEntity(body);
        // 6 请求必须由客户端发起（浏览器，jmeter，httpcline），必须创建一个客户端
        HttpClient client = HttpClients.createDefault();
        // execute(HttpUriRequest):多态方法，接受HttpUriRequest所有子实现
        // 7
        HttpResponse response = client.execute(post);
        // 8
        // 响应状态码
        System.out.println(response.getStatusLine().getStatusCode());
        // 响应头
        Header[] allHeaders = response.getAllHeaders();
        System.out.println(Arrays.toString(allHeaders));
        // 响应体
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }
}
