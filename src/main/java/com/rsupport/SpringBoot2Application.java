package com.rsupport;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.rsupport.notice.entity.MemberEntity;
import com.rsupport.notice.entity.NoticeEntity;
import com.rsupport.notice.repository.MemberRepository;
import com.rsupport.notice.repository.NoticeRepository;

@SpringBootApplication
@ComponentScan("com.rsupport")
public class SpringBoot2Application extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		memberRepository.save(MemberEntity.builder()
				.id("test")
				.password("test")
				.username("테스터")
				.reg_date(LocalDate.now()).build());
		for(int i=1; i<=30; i++) {
		   noticeRepository.save(NoticeEntity.builder()
					.seq(Long.valueOf(i))
					.userId("test")
					.title("게시글" + i)
					.contents("내용" + i)
					.reg_date(LocalDate.now())
					.username("테스터")
					.mod_date(LocalDate.now()).build());
		}
	}

}
