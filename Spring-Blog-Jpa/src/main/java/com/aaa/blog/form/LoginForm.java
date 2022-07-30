package com.aaa.blog.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginForm {
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
}
