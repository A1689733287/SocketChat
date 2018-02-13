package com.gpg.dao;

import com.gpg.entity.User;

public interface UserDao {
	User query(User user);

	int register(User user);
}
