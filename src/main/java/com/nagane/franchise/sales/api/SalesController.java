package com.nagane.franchise.sales.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.sales.application.SalesService;
import com.nagane.franchise.sales.dto.SalesDetailDto;
import com.nagane.franchise.sales.dto.SalesListDto;
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
 * @author nsr, (doc) jy
 * @since 2024.07.01
 * 매출 관련 controller
 * **/
@Tag(name= "매출 관리 API", description = "매출 관리 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
public class SalesController {
	
	// 반환할 데이터를 담을 객체 생성
    private BaseResponseBody responseBody;
    
    // 반환할 데이터를 담을 맵 생성
    private Map<String, Object> data;
    
	@Autowired
	SalesService salesService;

	/**
	 * 특정 가맹점의 매출 정보 조회
	 * @param Long 가맹점 번호
	 * @return SalesDetailDto
	 */
	@Operation(summary = "특정 가맹점의 매출 정보 조회", 
			description = "(가맹점) 선택한 가맹점의 매출 정보를 조회하는 api입니다.",
			security = @SecurityRequirement(name="bearerAuth"))
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
    		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
			content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("sales")
	public ResponseEntity<? extends BaseResponseBody> getSales(@RequestParam Long storeNo){
		
		try {
			SalesDetailDto salesDetailDto = salesService.getSalesDetail(storeNo);
	        // 맵에 데이터 삽입
	        data = new HashMap<>();
	        data.put("salesDetail", salesDetailDto);
	        
	        // requestBody 생성
	        responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "매출 정보를 성공적으로 가져왔습니다.", data);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "매출 정보 조회에 실패했습니다.");
	    }
	    return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	

	/**
	 * 월별 매출 정보 목록 조회
	 * @param 년도, 월
	 * @return List<SalesListDto>
	 */
	@Operation(summary = "월별 매출 정보 목록 조회", description = "(본사) 선택한 년도, 월의 매출 목록을 조회하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("api/sales")
	public ResponseEntity<? extends BaseResponseBody> getSales(
			@RequestParam Integer year,
			@RequestParam Integer month){
		
		try {
			List<SalesListDto> salesDetailList = salesService.getSalesList(year, month);
	        // 맵에 데이터 삽입
	        data = new HashMap<>();
	        data.put("salesList", salesDetailList);
	        responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "월별 매출 정보 목록을 성공적으로 가져왔습니다.", data);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "매출 정보 조회에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
}
