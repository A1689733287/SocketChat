package com.gpg.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

	public static <T> T singleQuery(String sql, RowCallBackHandler<T> handler) {
		return singleQuery(sql, null, handler);
	}

	/**
	 * 单挑数据查询
	 * 
	 * @param sql
	 *            sql语句
	 * @param setter
	 *            PreparedStatementSetter 处理通配符？
	 * @param handler
	 *            处理结果集对象
	 * @return 返回数据
	 */
	public static <T> T singleQuery(String sql, PreparedStatementSetter setter, RowCallBackHandler<T> handler) {
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		try {
			rs = result(sql, setter, conn);
			if (handler != null && rs.next()) {
				return handler.processRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, rs);
		}
		return null;
	}

	public static <T> List<T> query(String sql, RowCallBackHandler<T> handler) {
		return query(sql, null, handler);
	}

	/**
	 * 查询多条数据
	 * 
	 * @param sql
	 *            sql语句
	 * @param setter
	 *            PreparedStatementSetter 处理通配符？
	 * @param handler
	 *            处理结果集对象
	 * @return 返回数据
	 */
	public static <T> List<T> query(String sql, PreparedStatementSetter setter, RowCallBackHandler<T> handler) {
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		List<T> list = null;
		try {
			rs = result(sql, setter, conn);
			if (handler != null) {
				list = new ArrayList<>();
				while (rs.next()) {
					list.add(handler.processRow(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, rs);
		}
		return list;
	}

	/**
	 * 更新数据方法
	 * 
	 * @param sql
	 *            sql语句
	 * @param setter
	 *            处理通配符对象
	 * @return 返回结果
	 */
	public static <T> int update(String sql, PreparedStatementSetter setter) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (setter != null) {
				setter.setValues(pstmt);
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(pstmt);
			DBUtil.release(conn, null);
		}
		return 0;
	}

	/**
	 * 获取结果集
	 * 
	 * @param sql
	 *            语句
	 * @param setter
	 *            PreparedStatementSetter 用来处理通配符？
	 * @param conn
	 *            连接对象
	 * @return 返回结果集对象
	 * @throws SQLException
	 */
	private static ResultSet result(String sql, PreparedStatementSetter setter, Connection conn) throws SQLException {
		ResultSet rs;
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		if (setter != null) {
			setter.setValues(pstmt);
		}
		rs = pstmt.executeQuery();
		return rs;
	}

}
