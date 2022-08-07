package com.aaa.blog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;

import com.aaa.blog.model.User;
import com.aaa.blog.repository.UserJpaRepository;

public class UserServiceJpaImpl implements UserService {
	
	@Autowired
	private UserJpaRepository userJpaRepository;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userJpaRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return this.userJpaRepository.findById(id);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return this.userJpaRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userJpaRepository.findByEmail(email);
	}

	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		user.setId(this.userJpaRepository.findAll().stream().map(User::getId).max(Comparator.naturalOrder()).orElse(Long.MIN_VALUE));
		return this.userJpaRepository.save(user);
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		Predicate<User> loginFilter = u->u.getUsername().equals(username) && u.getPassword().equals(password);
		return this.userJpaRepository.findAll().stream().filter(loginFilter).findAny().isPresent();
	}

}
