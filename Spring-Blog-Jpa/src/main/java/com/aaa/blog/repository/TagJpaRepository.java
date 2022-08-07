package com.aaa.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaa.blog.model.Tag;

public interface TagJpaRepository extends JpaRepository<Tag, Long> {

}
