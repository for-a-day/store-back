package com.nagane.franchise.store.api;

import java.util.HashMap;
import java.util.Map;

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
	 * @return Map<String, Object>>
	 */
	@PostMapping("/admin")
	public ResponseEntity<Map<String, Object>> createAdmin(
			@RequestBody AdminCreateDto adminCreateDto) {
		// 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
		// 신규 관리자 계정 생성
		try {
			this.adminService.createAdmin(adminCreateDto);
			response.put("message", "성공적으로 등록되었습니다.");
			response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
        	response.put("message", "관리자 등록에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * 관리자 로그인
	 * @param AdminCreateDto
	 * @return Map<String, Object>>
	 */
	@PostMapping("/admin/login")
	public ResponseEntity<Map<String, Object>> loginAdmin(
			@RequestBody AdminCreateDto adminLoginDto) {
		Map<String, Object> response = new HashMap<>();
		
		// 로그인
		try {
			this.adminService.loginAdmin(adminLoginDto);
			response.put("message", "로그인에 성공했습니다.");
			response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
		// 예외 발생 시, 로그인 x
		} catch (Exception e) {
			response.put("message", "로그인에 실패했습니다.");
			response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
