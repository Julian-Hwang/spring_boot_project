package com.aaa.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.aaa.blog.model.Tag;
import com.aaa.blog.repository.TagJpaRepository;

@Service
@Primary
public class TagServiceJpaImpl implements TagService {

	@Autowired
	private TagJpaRepository tagJpaRepository;
	
	@Override
	public Tag save(Tag tag) {
		// TODO Auto-generated method stub
		return this.tagJpaRepository.saveAndFlush(tag);
	}

	@Override
	public List<Tag> getTags() {
		// TODO Auto-generated method stub
		return this.tagJpaRepository.findAll();
	}

}
