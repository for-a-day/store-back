package com.nagane.franchise.store.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.application.AdminService;

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
	private AdminService adminService;
	
	/* 관리자 로그인 */
	@PostMapping("/admin/login")
	public ResponseEntity<String> loginAdmin(
			@RequestParam("admin_id") String adminId, 
			@RequestParam("admin_password") String adminPassword) {
		
		return ResponseEntity.ok("Login Success!");
	}
}
