package com.nagane.franchise.store.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.application.AdminService;
import com.nagane.franchise.store.dto.admin.AdminCreateDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin controller 코드
 * 관리자 관련 controller
 * **/
@Tag(name= "관리자 API")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class AdminController {
	
	// 의존성 주입(di)
	@Autowired
	private AdminService adminService;
	
	/**
	 * 관리자 생성
	 * @param AdminCreateDto
	 * @return String
	 */
	@PostMapping("/admin")
	public ResponseEntity<String> createAdmin(
			@RequestBody AdminCreateDto adminCreateDto) {

		// 신규 관리자 계정 생성
		try {
			this.adminService.createAdmin(adminCreateDto);
			return new ResponseEntity<>("성공적으로 등록되었습니다.", HttpStatus.OK);
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			return new ResponseEntity<>("관리자 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * 관리자 로그인
	 * @param AdminCreateDto
	 * @return String
	 */
	@PostMapping("/admin/login")
	public ResponseEntity<String> loginAdmin(
			@RequestBody AdminCreateDto adminLoginDto) {
		// 로그인
		try {
			this.adminService.loginAdmin(adminLoginDto);
			return new ResponseEntity<>("로그인 되었습니다.", HttpStatus.OK);
		// 예외 발생 시, 로그인 x
		} catch (Exception e) {
			return new ResponseEntity<>("로그인에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
