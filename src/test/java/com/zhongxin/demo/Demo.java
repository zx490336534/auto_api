package com.zhongxin.demo;

import com.zhongxin.utils.HttpUtils;

public class Demo {
    public static void main(String[] args) throws Exception {
        HttpUtils.get("http://api.lemonban.com/futureloan/loans");
        HttpUtils.get("http://api.lemonban.com/futureloan/loans?pageIndex=1");
        HttpUtils.get("http://api.lemonban.com/futureloan/loans?pageIndex=1&pageSize=1");

        HttpUtils.post("http://api.lemonban.com/futureloan/member/recharge", "{\"member_id\":2060127,\"amount\":1}");
        HttpUtils.post("http://api.lemonban.com/futureloan/member/recharge", "{\"member_id\":2060127,\"amount\":2}");
        HttpUtils.post("http://api.lemonban.com/futureloan/member/recharge", "{\"member_id\":2060127,\"amount\":-1}");
        HttpUtils.post("http://api.lemonban.com/futureloan/member/recharge", "{\"member_id\":2060127,\"amount\":\"aaa\"}");
    }
}
