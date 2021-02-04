package com.rsupport.notice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rsupport.notice.entity.NoticeEntity;
import com.rsupport.notice.repository.NoticeRepository;
import com.rsupport.notice.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	public Page<NoticeEntity> selectNoticeList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1 , pageable.getPageSize(), Sort.by("seq").descending());		
		return noticeRepository.findAll(pageable);
	}
	
	public NoticeEntity selectNoticeById(Long id) {
		return noticeRepository.findById(id).orElse(new NoticeEntity());
	}
}
