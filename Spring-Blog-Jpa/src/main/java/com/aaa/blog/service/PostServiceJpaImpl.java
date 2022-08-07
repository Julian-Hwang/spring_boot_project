package com.aaa.blog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.aaa.blog.model.Post;
import com.aaa.blog.repository.PostJpaRepository;

@Service
@Primary
public class PostServiceJpaImpl implements PostService {

	@Autowired
	private PostJpaRepository postJpaRepository;
	
	@Override
	public List<Post> findAllOrderedById() {
		// TODO Auto-generated method stub
		return this.postJpaRepository.findAll().stream().sorted(Comparator.comparing(Post::getId)).collect(Collectors.toList());
	}

	@Override
	public List<Post> findAllOrderedById(int page) {
		// TODO Auto-generated method stub
		return this.postJpaRepository.findAll().stream().sorted(Comparator.comparing(Post::getId)).limit(page).collect(Collectors.toList());
	}

	@Override
	public Optional<Post> findById(Long id) {
		// TODO Auto-generated method stub
		return this.postJpaRepository.findById(id);
	}

	@Override
	public Post create(Post post) {
		// TODO Auto-generated method stub
		return this.postJpaRepository.saveAndFlush(post);
	}

	@Override
	public Post edit(Post post) {
		// TODO Auto-generated method stub
		return this.postJpaRepository.saveAndFlush(post);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		this.postJpaRepository.deleteById(id);
	}

}
