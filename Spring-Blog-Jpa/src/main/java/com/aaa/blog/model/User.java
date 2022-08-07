package com.aaa.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BlogUser")
public class User {
	
	@Id
	@Column(name="USER_ID", nullable = false, unique = true)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Length(min=2, max=30, message="**아이디는 2자 이상 30자 이하입니다.")
	@NotEmpty(message="**계정명을 입력해주세요")
	private String username;
	
	@Column(nullable = false)
	@Length(min=5, message = "**암호를 5글자 이상으로 입력하세요")
	@NotEmpty(message="**암호를 입력하세요")
	@JsonIgnore
	private String password;
	
	@Column
	@Email(message = "**유효한 이메일 계정을 입력해주세요")
	@NotEmpty(message="**이메일 계정을 입력하세요")
	private String email;
	
	@Column
	@NotEmpty(message = "**이름을 입력하세요")
	private String fullname;
	
	@Column
	private UserRole role;
}
