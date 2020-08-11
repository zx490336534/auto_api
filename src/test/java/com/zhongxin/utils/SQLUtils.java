package com.zhongxin.utils;

import com.zhongxin.pojo.Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SQLUtils {
    public static void main(String[] args) throws Exception {
        scalarHandler();
    }

    public static Object getSingleResult(String sql) {
        if (StringUtils.isBlank(sql)) {
            return null;
        }
        // 1. 定义返回值
        Object result = null;
        try {
            // 2. 创建DBUtils sql语句操作类
            Connection conn = JDBCUtils.getConnection();
            // 3. 获取数据库连接
            QueryRunner runner = new QueryRunner();
            // 4. 创建ScalarHandler，针对单行单列的数据
            ScalarHandler handler = new ScalarHandler();
            // 5. 执行sql语句
            result = runner.query(conn, sql, handler);
            // 6. 关闭数据库连接
            JDBCUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void scalarHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "select count(*) from member where id=2073699";
        Connection conn = JDBCUtils.getConnection();
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long count = runner.query(conn, sql, handler);
        System.out.println(count);
        JDBCUtils.close(conn);
    }

    public static void beanListHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "select * from member";
        Connection conn = JDBCUtils.getConnection();
        BeanListHandler<Member> handler = new BeanListHandler<>(Member.class);
        List<Member> list = runner.query(conn, sql, handler);
        for (Member member : list) {
            System.out.println(member);
        }
        JDBCUtils.close(conn);
    }

    public static void beanHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "select * from member where id=2073699";
        Connection conn = JDBCUtils.getConnection();
        BeanHandler<Member> handler = new BeanHandler<>(Member.class);
        Member m = runner.query(conn, sql, handler);
        System.out.println(m);
        JDBCUtils.close(conn);
    }

    public static void mapHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "select * from member where id=2073699";
        Connection conn = JDBCUtils.getConnection();
        MapHandler handler = new MapHandler();
        Map<String, Object> map = runner.query(conn, sql, handler);
        System.out.println(map);
        JDBCUtils.close(conn);
    }

    public static void insert() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO member VALUES(NULL,'zhong','123456','15168230600,1,100,NOW());";
        Connection conn = JDBCUtils.getConnection();
        int count = runner.update(conn, sql);
        System.out.println(count);
        JDBCUtils.close(conn);
    }

    public static void update() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "UPDATE member SET leave_amount=100 WHERE id=2073699;";
        Connection conn = JDBCUtils.getConnection();
        int count = runner.update(conn, sql);
        System.out.println(count);
        JDBCUtils.close(conn);
    }
}
