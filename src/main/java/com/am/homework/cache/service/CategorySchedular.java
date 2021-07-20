package com.am.homework.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategorySchedular {
	
	@Autowired
	private CategoryService service;

	@Scheduled(fixedDelay = 5000)
	public void resetAll() {
		// 테이블에 update 날짜를 추가 하여 변경된 데이터만 갱신한다.
		//아래는 전체 변경으로 적용함.
		
		log.debug("schedule category");
		
		//service.resetAll();
		
		
	}
}
