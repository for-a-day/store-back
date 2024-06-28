package com.nagane.franchise.store.application.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.store.application.AdminService;
import com.nagane.franchise.store.dao.AdminRepository;
import com.nagane.franchise.store.domain.Admin;
import com.nagane.franchise.store.dto.admin.AdminCreateDto;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin Service Impl 코드
 * 관리자 관련 service 상속
 * **/
@Service
public class AdminServiceImpl implements AdminService {
	
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	// 필요 레포지토리 연결
	private final AdminRepository adminRepository;
	
	// 의존성 주입(di)
	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	/** 관리자 로그인 */
	@Override
	public void loginAdmin(AdminCreateDto adminLoginDto) throws NoSuchElementException {
		LOGGER.info("[loginAdmin] input adminDto : {}", adminLoginDto);
		
		// 해당 admin 존재하는지 검증
		Optional<Admin> optionalAdmin = this.adminRepository
				.findByAdminIdAndAdminPassword(adminLoginDto.getAdminId(), adminLoginDto.getAdminPassword());
		
		Admin admin = optionalAdmin.orElseThrow(() -> new NoSuchElementException("no record"));
	}

	/** 관리자 생성 */
	@Override
	public void createAdmin(AdminCreateDto adminCreateDto) {
		LOGGER.info("[createAdmin] input adminDto : {}", adminCreateDto);
		
		// 새로 등록할 admin 계정 엔티티 객체 생성
		Admin createAdmin = Admin.builder()
				.adminId(adminCreateDto.getAdminId())
				.adminPassword(adminCreateDto.getAdminPassword())
				.build();
		
		// admin db에 신규 등록
		Admin savedAdmin = this.adminRepository.save(createAdmin);
		
	}
		
}
