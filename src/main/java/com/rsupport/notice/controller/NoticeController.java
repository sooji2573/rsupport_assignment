package com.rsupport.notice.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rsupport.BaseController;
import com.rsupport.notice.entity.NoticeEntity;
import com.rsupport.notice.repository.NoticeRepository;
import com.rsupport.notice.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeService noticeService;

	/**
	 * 리스트 조회	 
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list(@PageableDefault Pageable pageable, Model model) {
		ModelAndView mav = new ModelAndView("notice/index");
		Page<NoticeEntity> notice = noticeService.selectNoticeList(pageable);
		mav.addObject("notice", notice);
		return mav;
	}
	
	/**
	 * 등록 폼
	 * @return
	 */
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public ModelAndView form(@RequestParam(value="seq", defaultValue = "0") Long seq, Model model) {	
		ModelAndView mav = new ModelAndView("notice/form");
		mav.addObject("notice", noticeService.selectNoticeById(seq));
		return mav;
	}
	
	
	/**
	 * 등록
	 * @param notice
	 */
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody NoticeEntity notice) {
		notice.setReg_date(LocalDate.now());
		notice.setMod_date(LocalDate.now());
		noticeRepository.save(notice);
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}
	
	/**
	 * 수정
	 * @param id
	 * @param notice
	 * @return
	 */
	@RequestMapping(value="/{seq}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("seq") Long seq, @RequestBody NoticeEntity notice) {
		NoticeEntity newNotice = noticeRepository.getOne(seq);
		newNotice.setUserId(notice.getUserId());
		newNotice.setTitle(notice.getTitle());
		newNotice.setContents(notice.getContents());
		newNotice.setUsername(notice.getUsername());
		newNotice.setMod_date(LocalDate.now());
		noticeRepository.save(newNotice);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	/**
	 * 삭제
	 * @param notice
	 */
	@RequestMapping(value="/{seq}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("seq") Long seq) {
		noticeRepository.deleteById(seq);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}
