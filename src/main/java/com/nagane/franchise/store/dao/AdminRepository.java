package com.nagane.franchise.store.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.store.domain.Admin;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 관리자 관련 repository 코드
 * **/
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	/* 관리자 아이디와 비밀번호로 해당하는 레코드 반환 */
	Optional<Admin> findByAdminIdAndAdminPassword(String adminId, String adminPassword);
	
}
