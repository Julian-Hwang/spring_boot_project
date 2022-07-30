package com.aaa.blog.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostForm {

	@NotNull
	@Size(min=2, max=30, message="2자 이상 30자 이하로 입력하여주십시요")
	private String title;
	
	@NotNull
	@Size(min=5, max=500, message="5장 이상 500자 이하로 작성해 주세요")
	private String body;
}
