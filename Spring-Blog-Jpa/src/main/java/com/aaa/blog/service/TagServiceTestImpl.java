package com.aaa.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.blog.model.Tag;

@Service
public class TagServiceTestImpl implements TagService {
	
	private UserService userService;
	
	private List<Tag> tags = new ArrayList<Tag>();
	
	@Autowired
	public TagServiceTestImpl(UserService userService) {
		this.userService=userService;
		
		tags.add(new Tag(1L,"태그 1", new Date(), null, userService.findById(1L).get()));
		tags.add(new Tag(2L,"태그 2", new Date(), null, userService.findById(2L).get()));
		tags.add(new Tag(3L,"태그 3", new Date(), null, userService.findById(3L).get()));
	}

	@Override
	public Tag save(Tag tag) {
		// TODO Auto-generated method stub
		tag.setCreatedDate(new Date());
		this.tags.add(tag);
		return tag;
	}

	@Override
	public List<Tag> getTags() {
		// TODO Auto-generated method stub
		return this.tags;
	}

}
