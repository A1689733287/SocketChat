package com.gpg.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gpg.dao.UserDao;
import com.gpg.entity.User;
import com.gpg.util.JdbcTemplate;
import com.gpg.util.PreparedStatementSetter;
import com.gpg.util.RSAUtil;
import com.gpg.util.RowCallBackHandler;

public class UserDaoImpl implements UserDao {

	@Override
	public User query(User user) {
		String sql = "SELECT ID,USERNAME,USERPWD FROM T_USERS WHERE USERNAME=? AND USERPWD=?";
		return JdbcTemplate.singleQuery(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, RSAUtil.decrypt(user.getUserPwd()));
			}
		}, createHandler());
	}

	@Override
	public int register(User user) {
		String sql = "INSERT INTO T_USERS(ID,USERNAME,USERPWD) VALUES(T_USERS_ID_SEQ.NEXTVAL,?,?)";
		return JdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, RSAUtil.decrypt(user.getUserPwd()));
			}
		});
	}

	private RowCallBackHandler<User> createHandler() {

		return new RowCallBackHandler<User>() {

			@Override
			public User processRow(ResultSet rs) throws SQLException {
				User user = new User(rs.getInt(1), rs.getString(1), rs.getString(2));
				return user;
			}
		};

	}

}
