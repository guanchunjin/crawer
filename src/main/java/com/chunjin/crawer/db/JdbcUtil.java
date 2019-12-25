package com.chunjin.crawer.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 获取连接的工具类
 *
 * @author wds
 */
public class JdbcUtil {

    // 数据库连接池
    private static ConnPool connPool = new ConnPool();

    /**
     * 从池中获取一个连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws Exception {

        Connection con = connPool.getConnection();
        while (con == null) {
            System.out.println("Wait the dataBase");
            Thread.sleep(1000);
            con = connPool.getConnection();
        }
        return con;
    }

    /**
     * 关闭连接
     *
     * @param conn
     * @param st
     * @param rs
     * @throws SQLException
     */
    public static void CloseConnection(Connection conn, Statement st, ResultSet rs) throws SQLException {

        // 关闭存储查询结果的ResultSet对象
        if (rs != null) {
            rs.close();
        }

        // 关闭Statement对象
        if (st != null) {
            st.close();
        }

        // 关闭连接
        if (conn != null) {
            conn.close();
        }
    }

}
