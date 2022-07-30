package com.aaa.blog.service;

import java.util.List;
import java.util.Optional;

import com.aaa.blog.model.User;

public interface UserService {
	List<User> findAll();
	Optional<User> findById(Long id);
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	User register(User user);
	boolean authenticate(String username, String password);
}
