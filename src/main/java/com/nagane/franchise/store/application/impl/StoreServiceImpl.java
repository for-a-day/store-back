package com.nagane.franchise.store.application.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.store.application.StoreService;
import com.nagane.franchise.store.dao.StoreRepository;

/**
 * @author ljy
 * @since 2024.06.28
 * Store Service Impl 코드
 * 지점 관련 service 상속
 * **/
@Service
public class StoreServiceImpl implements StoreService {
	
	// 로그 설정
	private final Logger Logger = LoggerFactory.getLogger(StoreServiceImpl.class);
	
	// 필요 레포 연결
	private final StoreRepository storeRepository;
	
	// 의존성 주입(di)
	@Autowired
	public StoreServiceImpl(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
	
	
}
