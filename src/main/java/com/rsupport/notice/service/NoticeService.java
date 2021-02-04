package com.rsupport.notice.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rsupport.notice.entity.NoticeEntity;

@Service
@Transactional
public interface NoticeService {
	public Page<NoticeEntity> selectNoticeList(Pageable pageable);
	
	public NoticeEntity selectNoticeById(Long id);
}
