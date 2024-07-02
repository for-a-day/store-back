package com.nagane.franchise.store.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.application.StoreService;
import com.nagane.franchise.store.dto.store.StoreCreateDto;
import com.nagane.franchise.store.dto.store.StoreDto;
import com.nagane.franchise.store.dto.store.StoreLoginDto;
import com.nagane.franchise.store.dto.store.StoreNoDto;
import com.nagane.franchise.store.dto.store.StoreUpdateDto;

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
	
	/**
	 * 지점 목록 조회
	 * @param 
	 * @return Map<String, Object>>
	 */
	@GetMapping("/admin/store")
    public ResponseEntity<Map<String, Object>> getStoreList() {
        // 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // StoreDto 리스트를 가져오는 서비스 메서드 호출
            List<StoreDto> storeList = this.storeService.getStoreList();
            
            // 맵에 데이터 삽입
            response.put("message", "가맹점 데이터를 가져오는 데에 성공했습니다.");
            response.put("data", storeList);

            // ResponseEntity에 맵과 HttpStatus.OK를 포함하여 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "가맹점 데이터를 가져오는 데에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	 
	 /**
	 * 지점 신규 등록(지점장 회원 가입)
	 * @param StoreCreateDto
	 * @return Map<String, Object>>
	 */
	@PostMapping("/admin/store")
	public ResponseEntity<Map<String, Object>> createStore(
			@RequestBody StoreCreateDto storeCreateDto) {
		Map<String, Object> response = new HashMap<>();
		
		try {
            this.storeService.createStore(storeCreateDto);
            response.put("message", "가맹점 등록에 성공했습니다.");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "가맹점 등록에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	 /**
	 * 지점 정보 수정
	 * @param StoreUpdateDto
	 * @return Map<String, Object>>
	 */
	@PutMapping("/admin/store")
	public ResponseEntity<Map<String, Object>> updateStore(
			@RequestBody StoreUpdateDto storeUpdateDto) {
		Map<String, Object> response = new HashMap<>();
		
		try {
            this.storeService.updateStore(storeUpdateDto);
            response.put("message", "가맹점 정보 수정이 완료되었습니다.");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "가맹점 정보 수정에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	 /**
	 * 지점 삭제
	 * @param Long
	 * @return Map<String, Object>>
	 */
	@DeleteMapping("/admin/store")
	public ResponseEntity<Map<String, Object>> deleteStore(
			@RequestBody StoreNoDto storeNoDto) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			this.storeService.deleteStore(storeNoDto.getStoreNo());
			response.put("message", "가맹점 비활성화가 완료되었습니다.");
			response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "가맹점 삭제에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	 /**
	 * 가맹점 로그인
	 * @param StoreLoginDto
	 * @return Map<String, Object>>
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginStore(
			@RequestBody StoreLoginDto storeLoginDto) {
		Map<String, Object> response = new HashMap<>();
		
		try {
            this.storeService.loginStore(storeLoginDto);
            response.put("message", "로그인에 성공했습니다.");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "로그인에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
}
