package com.nagane.franchise.table.api;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.dto.store.StoreNoDto;
import com.nagane.franchise.table.application.TableService;
import com.nagane.franchise.table.dto.TableAdminDto;
import com.nagane.franchise.table.dto.TableCodeDto;
import com.nagane.franchise.table.dto.TableLoginDto;
import com.nagane.franchise.table.dto.TableNoDto;
import com.nagane.franchise.table.dto.TableResponseDto;
import com.nagane.franchise.table.dto.TableUpdateDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.07.01
 * Table controller 코드
 * 테이블 오더 관련 controller
 * **/
@Tag(name= "테이블 오더 API")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class TableController {
	
	// 의존성 주입 (di)
	@Autowired
	private TableService tableService;
	
	/**
	 * 테이블 목록 조회
	 * @param Long
	 * @return Map<String, Object>>
	 */
	@GetMapping("/table")
    public ResponseEntity<Map<String, Object>> getTableList(
    		@RequestParam Long storeNo) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        // 테이블 정보 리스트 가져오는 서비스 메서드 호출
	        List<TableResponseDto> tableList = this.tableService.getTableList(storeNo);
	        response.put("msg", "테이블 목록 조회 성공");
	        response.put("data", tableList);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        response.put("msg", "테이블 목록 조회에 실패했습니다.");
	        response.put("data", null);
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
	
	/**
	 * 테이블 신규 등록
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@PostMapping("/table")
	public ResponseEntity<Map<String, Object>> createTable(
			@RequestBody StoreNoDto storeNoDto) {
		Map<String, Object> response = new HashMap<>();
        
        try {
        	this.tableService.createTable(storeNoDto.getStoreNo());
        	response.put("msg", "테이블 등록에 성공했습니다.");
        	response.put("data", null);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("msg", e.getMessage());
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 테이블 수정
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@PutMapping("/table")
	public ResponseEntity<Map<String, Object>> updateTable(
			@RequestBody TableUpdateDto tableUpdateDto) {
		Map<String, Object> response = new HashMap<>();
        
        try {
        	this.tableService.updateTable(tableUpdateDto);
        	response.put("msg", "테이블 수정에 성공했습니다.");
        	response.put("data", null);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("msg", e.getMessage());
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 현재 테이블 내 주문 내역(0, 1. 환불 제외) 다 2로 수정
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@PutMapping("/table/clear")
	public ResponseEntity<Map<String, Object>> clearTable(
			@RequestBody TableNoDto tableNoDto) {
		Map<String, Object> response = new HashMap<>();
        
        try {
        	this.tableService.clearTable(tableNoDto.getTableNo());
        	response.put("msg", "테이블 수정에 성공했습니다.");
        	response.put("data", null);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("msg", e.getMessage());
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	/**
	 * 테이블 삭제
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@DeleteMapping("/table")
	public ResponseEntity<Map<String, Object>> deleteTable(
			@RequestBody TableNoDto tableNoDto) {
		Map<String, Object> response = new HashMap<>();
        
        try {
        	this.tableService.deleteTable(tableNoDto.getTableNo());
        	response.put("msg", "테이블 삭제에 성공했습니다.");
        	response.put("data", null);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("msg", e.getMessage());
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 테이블 오더 로그인
	 * @param TableLoginDto
	 * @return Map<String, Object>>
	 */
	@PostMapping("/to")
	public ResponseEntity<Map<String, Object>> loginTable(
			@RequestBody TableLoginDto tableLoginDto) {
		Map<String, Object> response = new HashMap<>();
        
        try {
        	this.tableService.loginTable(tableLoginDto);
        	response.put("msg", "테이블 오더 로그인에 성공했습니다.");
        	response.put("data", null);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("msg", e.getMessage());
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	/**
	 * 테이블 오더 관리자 모드 로그인
	 * @param TableAdminDto
	 * @return Map<String, Object>>
	 */
	@PostMapping("/to/login")
	public ResponseEntity<Map<String, Object>> loginTableAdmin(
			@RequestBody TableAdminDto tableAdminDto) {
		Map<String, Object> response = new HashMap<>();
		
        try {
        	this.tableService.loginTableAdmin(tableAdminDto);
        	response.put("msg", "관리자 로그인에 성공했습니다.");
        	response.put("data", null);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("msg", e.getMessage());
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 테이블 오더 비활성화 요청
	 * @param 
	 * @return Map<String, Object>>
	 */
	@PutMapping("/to/admin")
	public ResponseEntity<Map<String, Object>> logoutTable(
			@RequestBody TableCodeDto tableCodeDto) {
		Map<String, Object> response = new HashMap<>();
		
        try {
        	this.tableService.logoutTable(tableCodeDto.getTableCode());
        	response.put("msg", "테이블 오더 비활성화에 성공했습니다.");
        	response.put("data", null);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("msg", e.getMessage());
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
