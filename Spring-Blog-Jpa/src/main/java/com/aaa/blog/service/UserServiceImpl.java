package com.aaa.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aaa.blog.model.User;
import com.aaa.blog.model.UserRole;

@Service
public class UserServiceImpl implements UserService {

	private List<User> users = new ArrayList<>() {{
		add(new User(1L, "aupres","password","aaa@bbb.com","홍길동",UserRole.ADMIN));
		add(new User(2L, "ana","password","bbb@ccc.com","심청이",UserRole.USER));
		add(new User(3L, "julian","password","ccc@sss.com","임꺽정",UserRole.USER));
	}};
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return users.stream().collect(Collectors.toList());
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return users.stream().filter(u->u.getId().equals(id)).findAny();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return users.stream().filter(u->u.getUsername().equals(username)).findAny();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return users.stream().filter(u->u.getEmail().equals(email)).findAny();
	}

	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		user.setId(this.users.stream().count());
		user.setRole(UserRole.USER);
		users.add(user);
		
		return user;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		Predicate<User> loginFilter = u->u.getUsername().equals(username) && u.getPassword().equals(password);
		return users.stream().anyMatch(loginFilter);
	}

}
