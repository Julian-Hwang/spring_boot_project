package com.aaa.blog.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

	private Long id;
	
	@NotNull(message="글을 입력하세요")
	private String body;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createdDate;
	private Post post;
	private User user;
}
