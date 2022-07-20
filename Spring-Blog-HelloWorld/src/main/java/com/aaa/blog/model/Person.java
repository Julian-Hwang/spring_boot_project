package com.aaa.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	private String name;
	private String message;
	
	public String sayHello() {
		return name+"님!, "+message;
	}

}
