package com.zhongxin.utils;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    // 存储接口响应对象
    public static Map<String, Object> VARS = new HashMap<>();
    // 存储默认请求头
    public static Map<String, String> DEFAULT_HEADERS = new HashMap<>();

    static {
        // 静态代码：类在加载时会自动加载一次此代码
        DEFAULT_HEADERS.put("X-Lemonban-Media-Type", "lemonban.v2");
        DEFAULT_HEADERS.put("Content-Type", "application/json");
    }
}
