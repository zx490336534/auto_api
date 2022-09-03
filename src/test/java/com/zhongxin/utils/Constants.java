package com.zhongxin.utils;

/**
 * 常量文件
 * final修饰变量，变量成为常量，常量只能赋值一次。
 */
public class Constants {
    /**
     * 响应数据回写列
     */
    public static final int RESPONSE_CELL_NUM = 8;
    /**
     * 断言数据回写列
     */
    public static final int ASSERT_CELL_NUM = 10;

    /**
     * 断言-通过
     */
    public static final String ASSERT_SUCCESS = "PASSED";
    /**
     * 断言-不通过
     */
    public static final String ASSERT_FAILED = "FAILED";

    /**
     * 用例文件路径
     */
    public static final String EXCEL_PATH = "src/test/resources/cases_keyou.xlsx";
    public static final String EXCEL_API_PATH = "src/test/resources/cases_v1.xls";
    /**
     * 数据库连接信息-地址
     */
    public static final String JDBC_URL = "jdbc:mysql://api.lemonban.com:3306/futureloan?useUnicode=true&characterEncoding=utf-8";
    /**
     * 数据库连接信息-账号
     */
    public static final String JDBC_USERNAME = "future";
    /**
     * 数据库连接信息-密码
     */
    public static final String JDBC_PASSWORD = "123456";

}
