package com.numble.mybox.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.mybox.request.UserAddRequest;
import com.numble.mybox.response.UserResponse;

@Service @Transactional
public class UserServiceImpl implements UserService {
	@Override
	public UserResponse addUser(UserAddRequest userRequest) {
		return null;
	}

	@Override
	public UserResponse getUser(Long userId) {
		return null;
	}
}
