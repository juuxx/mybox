package com.numble.mybox.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAddRequest {
	private String username;
	private String password;
	private String profileImage;
}
