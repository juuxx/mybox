package com.numble.mybox.service;

import com.numble.mybox.request.UserAddRequest;
import com.numble.mybox.response.UserResponse;

public interface UserService {
	UserResponse addUser(UserAddRequest userRequest);

	UserResponse getUser(Long userId);
}
