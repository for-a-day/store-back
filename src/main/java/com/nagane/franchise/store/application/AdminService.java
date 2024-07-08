package com.nagane.franchise.store.application;

import java.util.Map;

import com.nagane.franchise.store.dto.admin.AdminCreateDto;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin Service 코드
 * 관리자 관련 service
 * **/
public interface AdminService {
	
	/** 관리자 로그인 */
	Map<String, Object> loginAdmin(String adminId, String adminPassword);
	
	/** 관리자 생성 */
	void createAdmin(AdminCreateDto adminCreateDto);
	
}
