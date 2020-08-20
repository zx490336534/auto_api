package com.zhongxin.utils;

import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.EmailAddressGenerator;
import cn.binarywang.tools.generator.EnglishNameGenerator;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    // 存储接口响应对象
    public static Map<String, Object> VARS = new HashMap<>();
    // 存储默认请求头
    public static Map<String, String> DEFAULT_HEADERS = new HashMap<>();

    static {
        // 静态代码：类在加载时会自动加载一次此代码
//        DEFAULT_HEADERS.put("X-Lemonban-Media-Type", "lemonban.v2");
        DEFAULT_HEADERS.put("Content-Type", "application/json");

        // 把需要参数化的数据存储到VARS
        // 随机手机号码
        VARS.put("${register_mb}", ChineseMobileNumberGenerator.getInstance().generate());
        VARS.put("${register_pwd}", "12345678");
        VARS.put("${amount}", "5000");

        VARS.put("${username}", EnglishNameGenerator.getInstance().generate() + EnglishNameGenerator.getInstance().generate());
        VARS.put("${email}", EmailAddressGenerator.getInstance().generate());
    }
}
