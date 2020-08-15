package com.zhongxin.utils;

public class Constants {
    // final修饰变量，变量成为常量，常量只能赋值一次。

    // 响应数据回写列
    public static final int RESPONSE_CELL_NUM = 8;
    // 断言数据回写列
    public static final int ASSERT_CELL_NUM = 10;

    public static final String ASSERT_SUCCESS = "PASSED";
    public static final String ASSERT_FAILED = "PASSED";


    // 用例文件路径
    public static final String EXCEL_PATH = "src/test/resources/cases_v3.xlsx";
    public static final String JDC_URL = "jdbc:mysql://api.lemonban.com:3306/futureloan?useUnicode=true&characterEncoding=utf-8";
    public static final String JDBC_USERNAME = "future";
    public static final String JDBC_PASSWORD = "123456";

}
