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
@Table(name="TB_NOTICE")
@NoArgsConstructor
public class NoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="reg_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate reg_date;
	
	@Column(name="username")
	private String username;
	
	@Column(name="mod_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate mod_date;
	
	@Column(name="contents")	
	private String contents;
	
	
	@Builder
	public NoticeEntity(Long seq, String userId, String title, LocalDate reg_date, String username, LocalDate mod_date, String contents) {
		this.seq = seq;
		this.userId = userId;
		this.title = title;
		this.reg_date = LocalDate.now();
		this.username = username;
		this.mod_date = LocalDate.now();
		this.contents = contents;
	}
	
	
}
