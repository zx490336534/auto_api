package com.zhongxin.homework;

import com.zhongxin.utils.HttpUtils;

public class HomeWork {
    public static void main(String[] args) throws Exception {
        HttpUtils.get("http://api.lemonban.com/futureloan/member/2060127/info");
        HttpUtils.post("http://api.lemonban.com/futureloan/member/withdraw", "{\"member_id\": 2060127, \"amount\": 500}");
        HttpUtils.patch("http://api.lemonban.com/futureloan/member/update", "{\"member_id\": 2060127, \"reg_name\":\"柠檬\" }");
    }
}
