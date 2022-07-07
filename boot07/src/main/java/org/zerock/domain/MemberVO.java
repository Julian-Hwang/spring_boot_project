package org.zerock.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_membervo")
@EqualsAndHashCode(of="mno")
public class MemberVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mno;
	private String mid;
	private String mpw;
	private String mname;
	private Timestamp regdate;
}
