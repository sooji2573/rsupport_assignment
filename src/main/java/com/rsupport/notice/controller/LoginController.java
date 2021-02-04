package com.rsupport.notice.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rsupport.BaseController;
import com.rsupport.notice.entity.MemberEntity;
import com.rsupport.notice.repository.MemberRepository;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	public MemberRepository memberRepository;
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody MemberEntity param, HttpServletRequest request) {
		MemberEntity member = memberRepository.findById(param.getId());
		if(member == null) {
			return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
		}
		
		if(!param.getPassword().equals(member.getPassword())) {
			return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("memberId", member.getId());
			session.setAttribute("memberUserNames", member.getUsername());
		}
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}
