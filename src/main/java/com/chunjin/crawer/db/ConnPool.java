package com.chunjin.crawer.db;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 *实现数据库连接池
 *
 */
public class ConnPool implements DataSource {

	// 使用LinkedList集合存放数据库连接
	private static volatile  LinkedList<Connection> connPool = new LinkedList<Connection>();

	// 在静态代码块中加载配置文件
	static {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3305/patentinfo?useUnicode=true&amp;characterEncoding=utf-8";
			String user = "root";
			String password = "root";
			String poolSize = "50";

			// 数据库连接池的初始化连接数的大小
			int InitSize = Integer.parseInt(poolSize);
			// 加载驱动
			Class.forName(driver);
			for (int i = 0; i < InitSize; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(true);
				connPool.add(conn);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取数据库连接
	 */
	public Connection getConnection() throws SQLException {
		if (connPool.size() > 0) {
			// 从集合中获取一个连接
			final Connection conn = connPool.removeFirst();
			// 返回Connection的代理对象
			return (Connection) Proxy.newProxyInstance(ConnPool.class.getClassLoader(), conn.getClass().getInterfaces(),
					new InvocationHandler() {
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if (!"close".equals(method.getName())) {
								return method.invoke(conn, args);
							} else {
								System.out.println("give back the connection");
								connPool.add(conn);
								return null;
							}
						}
					});
		} else {
			return null;
//			throw new RuntimeException("数据库繁忙，正在等待");
		}
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	public void setLoginTimeout(int seconds) throws SQLException {

	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	public Object unwrap(Class iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class iface) throws SQLException {
		return false;
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

}
