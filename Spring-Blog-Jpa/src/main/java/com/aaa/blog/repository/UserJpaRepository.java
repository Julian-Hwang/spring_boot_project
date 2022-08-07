package com.aaa.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.aaa.blog.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(@Param("email") String email);
	Optional<User> findByUsername(@Param("username") String username);
}
