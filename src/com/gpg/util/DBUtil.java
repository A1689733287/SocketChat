package com.gpg.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	//由ThreadLocal处理该并发问题
	private final static ThreadLocal<Connection> LOCAL = new ThreadLocal<>();
	//数据源
	private static DataSource dataSource = null;
	//静态代码块获取初始化源文件
	static {
		dataSource = new ComboPooledDataSource("oracle");
	}
	/**
	 * 得到连接对象
	 * @return 返回对象
	 */
	public static Connection getConnection() {
		Connection conn = LOCAL.get();
		try {
			if (null == conn || conn.isClosed()) {
				conn = dataSource.getConnection();
			}
			LOCAL.set(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放连接和结果集
	 * @param conn 连接对象
	 * @param rs 结果集对象
	 */
	public static void release(Connection conn, ResultSet rs) {
		try {
			if (rs != null) {
				release(rs.getStatement());
				rs.close();
			}
			if (conn != null) {
				conn.close();
				LOCAL.remove();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放Staetment对象
	 * @param stmt 传入Statement对象
	 */
	public static void release(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
