package com.nagane.franchise.store.application.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagane.franchise.store.application.AdminService;
import com.nagane.franchise.store.dao.AdminRepository;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin Service Impl 코드
 * 관리자 관련 service 상속
 * **/
public class AdminServiceImpl implements AdminService {
	
	// 로그 설정
	private final Logger Logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	// 필요 레포지토리 연결
	private final AdminRepository adminRepository;
	
	// 의존성 주입(di)
	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
		
}
