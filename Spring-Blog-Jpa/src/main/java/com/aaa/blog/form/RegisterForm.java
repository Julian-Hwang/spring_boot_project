package com.aaa.blog.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterForm {
	
	@NotBlank(message="유효한 유저명을 입력하세요.")
	@Size(min=2,max=30,message="아이디는 2자 이상 30자 이하입니다.")
	private String username;
	
	@NotBlank(message="유효한 암호를 입력하세요.")
	@Size(min=5, message="최소 5글자이어야 합니다.")
	private String password;
	
	@Size(max=50, message="50자 이내로 작성하세요")
	private String fullname;
	
	@NotEmpty(message="유효한 이메일을 입력하세요")
	private String email;
}
