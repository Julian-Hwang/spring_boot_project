package com.aaa.blog.service;

import org.springframework.stereotype.Service;

import com.aaa.blog.model.Person;

@Service
public class HelloworldImpl implements HelloworldService {

	Person person = new Person("스프링 부트","안녕하세요.");
	
	@Override
	public String helloworld() {
		
		return person.sayHello();
	}

}
