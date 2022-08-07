package com.aaa.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aaa.blog.model.Post;
import com.aaa.blog.model.User;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

	Page<Post> findByUserOrderById(User user, Pageable pageable);
	Page<Post> findAllByOrderById(Pageable pageable);
}
