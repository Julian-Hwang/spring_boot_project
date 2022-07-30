package com.aaa.blog.service;

import java.util.List;

import com.aaa.blog.model.Tag;

public interface TagService {
	
	Tag save(Tag tag);
	List<Tag> getTags();

}
