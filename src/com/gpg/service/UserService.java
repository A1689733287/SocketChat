package com.gpg.service;

import com.gpg.entity.User;

public interface UserService {
	User query(User user);

	int register(User user);
}
