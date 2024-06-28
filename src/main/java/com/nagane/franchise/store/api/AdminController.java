package com.nagane.franchise.store.api;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.application.AdminService;
import com.nagane.franchise.store.dto.AdminDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin controller 코드
 * 관리자 관련 controller
 * **/
@Tag(name= "Admin api")
@RestController
@RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:${pos.port}")
public class AdminController {
	
	// 의존성 주입(di)
	@Autowired
	private AdminService adminService;
	
	/** 관리자 생성 */
	@PostMapping("/admin")
	public ResponseEntity<String> createAdmin(
			@RequestParam("admin_id") String adminId, 
			@RequestParam("admin_password") String adminPassword) {
		
		// dto 생성
		AdminDto adminDto = AdminDto
				.builder()
				.adminId(adminId.trim())
				.adminPassword(adminPassword.trim())
				.build();
		
		// 로그인
		int result = this.adminService.createAdmin(adminDto);
		
		if (result == 1) {
			return ResponseEntity.ok("Create Success!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed create");
		}
	}
	
	
	/** 관리자 로그인 */
	@PostMapping("/admin/login")
	public ResponseEntity<String> loginAdmin(
			@RequestParam("admin_id") String adminId, 
			@RequestParam("admin_password") String adminPassword) {
		
		// dto 생성
		AdminDto adminDto = AdminDto
				.builder()
				.adminId(adminId.trim())
				.adminPassword(adminPassword.trim())
				.build();
		
		// 로그인
		int result = this.adminService.loginAdmin(adminDto);
		
		if (result == 1) {
			return ResponseEntity.ok("Login Success!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed login");
		}
	}
	
	// error 처리
	private ResponseEntity<String> checkException(String message) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
	
	// 해당 id나 password가 존재하지 않을 시
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> noSuchElementExceptionHandler(NoSuchElementException ex) {
		return this.checkException("로그인에 실패했습니다.");
	}
}
