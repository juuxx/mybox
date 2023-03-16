package com.numble.mybox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.numble.mybox.request.UserAddRequest;
import com.numble.mybox.response.UserResponse;
import com.numble.mybox.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
	private final UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserResponse> addUser(@RequestBody UserAddRequest userRequest) {
		UserResponse response = userService.addUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserResponse> addUser(@PathVariable Long userId) {
		UserResponse response = userService.getUser(userId);
		return ResponseEntity.ok(response);
	}
}
