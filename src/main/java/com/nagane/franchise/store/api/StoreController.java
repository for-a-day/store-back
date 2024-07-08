package com.nagane.franchise.store.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
import com.nagane.franchise.store.dto.store.StoreResponseDto;
import com.nagane.franchise.store.dto.store.StoreUpdateDto;
import com.nagane.franchise.util.exceptions.InsufficientStockException;
import com.nagane.franchise.util.model.response.BaseResponseBody;
import com.nagane.franchise.util.model.response.ErrorResponseBody;
import com.nagane.franchise.util.model.response.SuccessResponseBody;

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
 * Store controller 코드
 * 지점 관련 controller
 * **/
@Tag(name= "가맹점 API", description = "가맹점 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class StoreController {
	
	// 반환할 데이터를 담을 객체 생성
    private BaseResponseBody responseBody;
    
    // 반환할 데이터를 담을 맵 생성
    private Map<String, Object> data;
    
	// 의존성 주입(di)
	@Autowired
	private StoreService storeService;
	
	/**
	 * 지점 목록 조회
	 * @param 
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "지점 목록 조회", description = "(관리자) 관리자 측에서 지점 목록 전체 조회 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/admin/store")
    public ResponseEntity<? extends BaseResponseBody> getStoreList() {
        try {
            // StoreDto 리스트를 가져오는 서비스 메서드 호출
            List<StoreDto> storeList = this.storeService.getStoreList();
            
            // 맵에 데이터 삽입
            data = new HashMap<>();
            data.put("storeList", storeList);
            
            // requestBody 생성
            responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "성공적으로 가져왔습니다.", data);
        } catch (Exception e) {
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "지점 목록 조회에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	 
	 /**
	 * 지점 신규 등록(지점장 회원 가입)
	 * @param StoreCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "지점 신규 등록", description = "(관리자) 관리자 측에서 지점 신규 등록 시 사용하는 api입니다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/admin/store")
	public ResponseEntity<? extends BaseResponseBody> createStore(
			@RequestBody StoreCreateDto storeCreateDto) {
		
		try {
            this.storeService.createStore(storeCreateDto);
            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "가맹점 등록에 성공했습니다.");
        } catch (Exception e) {
   
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "에러가 발생했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	
	 /**
	 * 지점 정보 수정
	 * @param StoreUpdateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "지점 정보 수정", description = "(관리자) 관리자 측에서 지점 정보 수정 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/admin/store")
	public ResponseEntity<? extends BaseResponseBody> updateStore(
			@RequestBody StoreUpdateDto storeUpdateDto) {
		try {
            this.storeService.updateStore(storeUpdateDto);
            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "가맹점 정보 수정이 완료되었습니다.");
         // 예외 발생 시 오류 처리
 		} catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
 		} catch (Exception e) {
 			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "에러가 발생했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	
	 /**
	 * 지점 삭제
	 * @param Long
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "지점 삭제", description = "(관리자) 관리자 측에서 지점 삭제 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@DeleteMapping("/admin/store")
	public ResponseEntity<? extends BaseResponseBody> deleteStore(
			@RequestBody StoreNoDto storeNoDto) {
		
		try {
			this.storeService.deleteStore(storeNoDto.getStoreNo());
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "가맹점 삭제가 완료되었습니다.");
        } catch (InsufficientStockException ie) {
			responseBody = BaseResponseBody.of(HttpStatus.BAD_REQUEST.value(), ie.getMessage());
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "에러가 발생했습니다.");
		}
		
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	
	 /**
	 * 가맹점 로그인
	 * @param StoreLoginDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "가맹점 로그인", description = "(가맹점) 가맹점 측에서 로그인 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginStore(
			@RequestBody Map<String, String> loginData) {
		String rprName = loginData.get("rprName");
        String storeCode = loginData.get("storeCode");
			Map<String, Object> result = this.storeService.loginStore(rprName, storeCode);
			
			if (result == null) {
		        System.out.println("로그인 실패");
	            return ResponseEntity.badRequest().body(null); // 로그인 실패 시 400 에러 반환
	        }

	        return ResponseEntity.ok(result); // 로그인 성공 시 JWT 토큰 및 사용자 정보 반환
	}
	
}
