package com.nagane.franchise.store.application;

import com.nagane.franchise.store.dto.AdminDto;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin Service 코드
 * 관리자 관련 service
 * **/
public interface AdminService {
	
	/** 관리자 로그인 */
	int loginAdmin(AdminDto adminDto);
	
	/** 관리자 생성 */
	int createAdmin(AdminDto adminDto);
	
}
