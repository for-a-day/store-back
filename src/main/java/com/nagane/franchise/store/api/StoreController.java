package com.nagane.franchise.store.api;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.application.StoreService;
import com.nagane.franchise.store.dto.store.StoreCreateDto;
import com.nagane.franchise.store.dto.store.StoreDto;
import com.nagane.franchise.store.dto.store.StoreLoginDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.06.28
 * Store controller 코드
 * 지점 관련 controller
 * **/
@Tag(name= "가맹점 API")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class StoreController {
	
	// 의존성 주입(di)
	@Autowired
	private StoreService storeService;
	
	/** 지점 목록 조회 */
	@GetMapping("/admin/store")
	public ResponseEntity<String> getStoreList() {
		
		return null;
		
	}
	
	/** 지점 신규 등록(지점장 회원 가입) */
	@PostMapping("/admin/store")
	public ResponseEntity<String> createStore(
			@RequestBody StoreCreateDto storeCreateDto) {
		System.out.println(storeCreateDto.getAddress());
		System.out.println(storeCreateDto.getContact());
		System.out.println(storeCreateDto.getContractDate());
		try {
            this.storeService.createStore(storeCreateDto);
            return new ResponseEntity<>("성공적으로 등록되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("가맹점 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/** 지점 정보 수정 */
	@PutMapping("/admin/store")
	public ResponseEntity<String> updateStore(
			@RequestBody StoreDto storeUpdateDto) {
		try {
            this.storeService.updateStore(storeUpdateDto);
            return new ResponseEntity<>("가맹점 정보 수정이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("가맹점 정보 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/** 지점 삭제 */
	@DeleteMapping("/admin/store")
	public ResponseEntity<String> deleteStore(
			@RequestParam("storeNo") Long storeNo) {
		System.out.println(storeNo);
		try {
            this.storeService.deleteStore(storeNo);
            return new ResponseEntity<>("가맹점 삭제가 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("가맹점 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/** 가맹점 로그인 */
	@PostMapping("/login")
	public ResponseEntity<String> loginStore(
			@RequestBody StoreLoginDto storeLoginDto) {
		System.out.println(storeLoginDto.getStoreCode());
		try {
            this.storeService.loginStore(storeLoginDto);
            return new ResponseEntity<>("로그인에 성공했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("로그인에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	// error 처리
	private ResponseEntity<String> checkException(String message) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
		
	// 지점 정보가 존재하지 않을 시
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> noSuchElementExceptionHandler(NoSuchElementException ex) {
		return this.checkException("해당 지점이 존재하지 않습니다.");
	}
}
