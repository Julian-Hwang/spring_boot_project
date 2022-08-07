package com.aaa.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Tag {

	@Id
	@Column(name="TAG_ID")
	private Long id;
	
	@Column
	@NotEmpty(message="**입력해주세요")
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="POST_ID", referencedColumnName = "POST_ID", nullable = false)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name="USER_ID", referencedColumnName = "USER_ID", nullable = false)
	private User user;
}
