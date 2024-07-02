package com.nagane.franchise.store.api;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.application.AdminService;
import com.nagane.franchise.store.dto.admin.AdminCreateDto;
import com.nagane.franchise.util.model.response.BaseResponseBody;
import com.nagane.franchise.util.model.response.ErrorResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin controller 코드
 * 관리자 관련 controller
 * **/
@Tag(name= "관리자 API", description = "관리자 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class AdminController {
	
	// 반환할 데이터를 담을 객체 생성
    private BaseResponseBody responseBody;
    
    // 반환할 데이터를 담을 맵 생성
    private Map<String, Object> data;
    
	// 의존성 주입(di)
	@Autowired
	private AdminService adminService;
	
	/**
	 * 관리자 생성
	 * @param AdminCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "신규 관리자 생성", description = "관리자 새로 생성할 때 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "CREATED"),
	        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", 
	        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/admin")
	public ResponseEntity<? extends BaseResponseBody> createAdmin( // BaseResponseBody 상속 받는 다양한 클래스 return 가능
			@RequestBody AdminCreateDto adminCreateDto) {
		
		// 신규 관리자 계정 생성
		try {
			this.adminService.createAdmin(adminCreateDto);
			responseBody = BaseResponseBody.of(HttpStatus.CREATED.value(), "성공적으로 등록되었습니다.");
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "에러가 발생했습니다.");
		}
		
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	
	
	/**
	 * 관리자 로그인
	 * @param AdminCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "관리자 로그인", description = "관리자 로그인 시 이용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
	        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/admin/login")
	public ResponseEntity<? extends BaseResponseBody> loginAdmin(
			@RequestBody AdminCreateDto adminLoginDto) {
	    
		// 로그인
		try {
			this.adminService.loginAdmin(adminLoginDto);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "로그인에 성공했습니다.");
		// 예외 발생 시, 로그인 x
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "에러가 발생했습니다.");
		}
		
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	
}
