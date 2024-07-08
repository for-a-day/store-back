package com.nagane.franchise.store.application.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagane.franchise.global.config.JwtUtil;
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

	// 필요 레포지토리 연결, 의존성 주입(di)
	private final AdminRepository adminRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtUtil jwtUtil;
	
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	

	/** 관리자 로그인 */
	@Override
	public Map<String, Object> loginAdmin(String adminId, String adminPassword) {
		   Optional<Admin> adminOptional = adminRepository.findByAdminId(adminId);

	        if (adminOptional.isPresent()) {
	            Admin admin = adminOptional.get();
	            if (bCryptPasswordEncoder.matches(adminPassword, admin.getAdminPassword())) {
	            	System.out.println("일치함");
	                Map<String, Object> claims = new HashMap<>();
	                claims.put("sub", admin.getAdminId().toString());

	                String accessToken = jwtUtil.generateAccessToken(claims);
	                String refreshToken = jwtUtil.generateRefreshToken(claims);

	                Map<String, Object> response = new HashMap<>();
	                response.put("accessToken", accessToken);
	                response.put("refreshToken", refreshToken);
	                response.put("admin", admin);
	                return response;
	            }
	            System.out.println("일치하지 않음");
	        }

	        return null;
	        
	}

	/** 관리자 생성 */
	@Override
	public void createAdmin(AdminCreateDto adminCreateDto) {
		LOGGER.info("[createAdmin] input adminDto : {}", adminCreateDto);
		
		String encryptedPassword = bCryptPasswordEncoder.encode(adminCreateDto.getAdminPassword());
		
		// 새로 등록할 admin 계정 엔티티 객체 생성
		Admin createAdmin = Admin.builder()
				.adminId(adminCreateDto.getAdminId())
				.adminPassword(encryptedPassword)
				.build();
		
		// admin db에 신규 등록
		this.adminRepository.save(createAdmin);
		
	}
		
}
