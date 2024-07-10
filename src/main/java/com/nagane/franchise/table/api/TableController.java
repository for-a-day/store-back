package com.nagane.franchise.table.api;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.dto.store.StoreNoDto;
import com.nagane.franchise.table.application.TableService;
import com.nagane.franchise.table.dto.TableAdminDto;
import com.nagane.franchise.table.dto.TableCodeDto;
import com.nagane.franchise.table.dto.TableLoginDto;
import com.nagane.franchise.table.dto.TableNoDto;
import com.nagane.franchise.table.dto.TableOrderDetailDto;
import com.nagane.franchise.table.dto.TableResponseDto;
import com.nagane.franchise.table.dto.TableUpdateDto;
import com.nagane.franchise.util.exceptions.InsufficientStockException;
import com.nagane.franchise.util.model.response.BaseResponseBody;
import com.nagane.franchise.util.model.response.ErrorResponseBody;
import com.nagane.franchise.util.model.response.SuccessResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.07.01
 * Table controller 코드
 * 테이블 오더 관련 controller
 * **/
@Tag(name= "테이블 오더 API", description = "테이블 오더 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class TableController {
	
	// 반환할 데이터를 담을 객체 생성
    private BaseResponseBody responseBody;
    
    // 반환할 데이터를 담을 맵 생성
    private Map<String, Object> data;
    
	// 의존성 주입 (di)
	@Autowired
	private TableService tableService;
	
	/**
	 * 테이블 목록 조회
	 * @param Long
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 목록 조회", 
			description = "(가맹점) 가맹점 측에서 현재 테이블 목록 전체 조회 시 사용하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/table")
    public ResponseEntity<? extends BaseResponseBody> getTableList(
    		@RequestParam Long storeNo) {

	    try {
	        // 테이블 정보 리스트 가져오는 서비스 메서드 호출
	        List<TableResponseDto> tableList = this.tableService.getTableList(storeNo);
	        // 맵에 데이터 삽입
            data = new HashMap<>();
            data.put("tableList", tableList);
            
            // requestBody 생성
            responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "테이블 목록 조회에 성공했습니다.", data);
            
	    } catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
 		} catch (Exception e) {
 			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "테이블 목록 조회에 실패했습니다.");
        }
	    return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	
	/**
	 * 테이블 신규 등록
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 신규 등록", 
			description = "(가맹점) 가맹점 측에서 현재 테이블 신규 등록 시 사용하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/table")
	public ResponseEntity<? extends BaseResponseBody> createTable(
			@RequestBody StoreNoDto storeNoDto) {              
        
        try {
        	String newTableCode = this.tableService.createTable(storeNoDto.getStoreNo());
        	// 맵에 데이터 삽입
            data = new HashMap<>();
            data.put("tableCode", newTableCode);
        	responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "테이블 등록에 성공했습니다.", data);
        } catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "테이블 등록에 실패했습니다.");
		}
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 테이블 수정
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 수정", 
			description = "(가맹점) 가맹점 측에서 현재 테이블 정보 수정 시 사용하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/table")
	public ResponseEntity<? extends BaseResponseBody> updateTable(
			@RequestBody TableUpdateDto tableUpdateDto) {
        
        try {
        	this.tableService.updateTable(tableUpdateDto);
        	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "테이블 정보 수정에 성공했습니다.");
        } catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "테이블 정보 수정에 실패했습니다.");
		}
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 현재 테이블 내 주문 내역(0, 1. 환불 제외) 다 2로 수정
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 수정", 
			description = "(가맹점) 가맹점 측에서 해당 테이블을 나가는 손님이 완전히 가게를 나갔을 해 사용하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/table/clear")
	public ResponseEntity<? extends BaseResponseBody> clearTable(
			@RequestBody TableNoDto tableNoDto) {
        
        try {
        	this.tableService.clearTable(tableNoDto.getTableNo());
        	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "현재 테이블 주문 상태 변경에 성공했습니다.");
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "현재 테이블 주문 상태 변경에 실패했습니다.");
		}
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	
	/**
	 * 선택한 테이블 주문 리스트 조회
	 * @param Long
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "선택한 테이블 주문 리스트 조회", 
			description = "(가맹점) 가맹점 측에서 지정된 테이블의 주문 상태를 자세히 확인할 수 있는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/table/order")
    public ResponseEntity<? extends BaseResponseBody> getTableOrderList(
    		@RequestParam Long tableNo) {

	    try {
	        // 테이블 정보 리스트 가져오는 서비스 메서드 호출
	    	TableOrderDetailDto tableOrderDetail = this.tableService.getTableOrderList(tableNo);
	        // 맵에 데이터 삽입
            data = new HashMap<>();
            data.put("tableOrderDetail", tableOrderDetail);
            
            // requestBody 생성
            responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "테이블 목록 조회에 성공했습니다.", data);
            
	    } catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
 		} catch (Exception e) {
 			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "테이블 목록 조회에 실패했습니다.");
        }
	    return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	
	/**
	 * 테이블 삭제
	 * @param TableCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 삭제", 
			description = "(가맹점) 가맹점 측에서 현재 테이블 삭제 시 사용하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@DeleteMapping("/table")
	public ResponseEntity<? extends BaseResponseBody> deleteTable(
			@RequestBody TableCodeDto tableCodeDto) {
        
        try {
        	this.tableService.deleteTable(tableCodeDto.getTableCode());
        	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "테이블 삭제에 성공했습니다.");
        } catch (InsufficientStockException ie) {
			responseBody = BaseResponseBody.of(HttpStatus.BAD_REQUEST.value(), ie.getMessage());
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "테이블 삭제에 실패했습니다.");
		}
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 테이블 오더 로그인
	 * @param TableLoginDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 오더 로그인", 
			description = "(테이블 오더) 테이블에서 새로 기기 (재)등록할 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/to")
	public ResponseEntity<? extends BaseResponseBody> loginTable(
			@RequestBody TableLoginDto tableLoginDto) {
        
        try {
        	this.tableService.loginTable(tableLoginDto);
        	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "테이블 오더 로그인에 성공했습니다.");
        // 예외 발생 시 오류 처리
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "테이블 오더 로그인에 실패했습니다.");
	    }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	
	/**
	 * 테이블 오더 관리자 모드 로그인
	 * @param TableAdminDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 오더 관리자 모드 로그인", 
			description = "(테이블 오더) 테이블에서 관리자모드로 로그인할 시 사용하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/to/login")
	public ResponseEntity<? extends BaseResponseBody> loginTableAdmin(
			@RequestBody TableAdminDto tableAdminDto) {
		
        try {
        	this.tableService.loginTableAdmin(tableAdminDto);
        	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "관리자 모드 로그인에 성공했습니다.");
        	// 예외 발생 시 오류 처리
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "관리자 모드 로그인에 실패했습니다.");
	    }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 테이블 오더 비활성화 요청
	 * @param 
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "테이블 오더 비활성화 요청", 
			description = "(테이블 오더) 해당 테이블 오더를 비활성으로 전환하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/to/admin")
	public ResponseEntity<? extends BaseResponseBody> logoutTable(
			@RequestBody TableCodeDto tableCodeDto) {
		
        try {
        	this.tableService.logoutTable(tableCodeDto.getTableCode());
    		responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "테이블 오더 비활성화에 성공했습니다.");
	    	// 예외 발생 시 오류 처리
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "테이블 오더 비활성화에 실패했습니다.");
	    }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
}
