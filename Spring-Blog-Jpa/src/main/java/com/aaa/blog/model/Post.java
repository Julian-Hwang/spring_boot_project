package com.aaa.blog.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	private Long id;
	private String title;
	private String body;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createdDate;
	
	private User user;
	private Collection<Tag> tags;
}
