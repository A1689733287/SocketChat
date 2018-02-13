package com.gpg.service.impl;

import com.gpg.dao.UserDao;
import com.gpg.dao.impl.UserDaoImpl;
import com.gpg.entity.User;
import com.gpg.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User query(User user) {
		return userDao.query(user);
	}

	@Override
	public int register(User user) {
		return userDao.register(user);
	}

}
