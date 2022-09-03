package com.zhongxin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    /**
     * 连接数据库
     */
    public static Connection getConnection() {
        //定义数据库连接
        String url = Constants.JDBC_URL;
        String user = Constants.JDBC_USERNAME;
        String password = Constants.JDBC_PASSWORD;
        //定义数据库连接对象
        Connection conn = null;
        try {
            //你导入的数据库驱动包， mysql。
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn 数据库连接信息
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


