package com.rsupport.notice.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="TB_MEMBER")
@NoArgsConstructor	
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 8124563785445645L;
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="id")
	private String id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="username")
	private String username;
	
	@Column(name="reg_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate reg_date;
	
	@Builder
	public MemberEntity(String id, String password, String username, LocalDate reg_date) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.reg_date = reg_date;
	}
}
