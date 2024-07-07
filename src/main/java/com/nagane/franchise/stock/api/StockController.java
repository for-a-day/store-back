package com.nagane.franchise.stock.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.stock.application.PurchaseOrderService;
import com.nagane.franchise.stock.application.StockService;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderUpdateDto;
import com.nagane.franchise.stock.dto.stock.StockCreateDto;
import com.nagane.franchise.stock.dto.stock.StockListDto;
import com.nagane.franchise.stock.dto.stock.StockUpdateDto;
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
 * @author nsr, (doc) jy
 * @since 2024.06.30
 * Stock controller 코드
 * 재고 및 발주 관련 controller
 * **/
@Tag(name= "재고 API", description = "재고 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
public class StockController {
	
	// 반환할 데이터를 담을 객체 생성
    private BaseResponseBody responseBody;
    
    // 반환할 데이터를 담을 맵 생성
    private Map<String, Object> data;
    
	@Autowired
	private StockService stockService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;

//	/**
//	 * 재고 등록
//	 * @param StockCreateDto
//	 * @return String
//	 */
//	@Operation(summary = "재고 등록", description = "(가맹점) 재고를 신규로 등록하는 api입니다.")
//	@ApiResponses(value = {
//	        @ApiResponse(responseCode = "200", description = "OK", 
//	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
//	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
//        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
//	    })
//	@PostMapping("/stock")
//	public ResponseEntity<? extends BaseResponseBody> createStock(
//			@RequestBody StockCreateDto stockCreateDto) {
//		try {
//            this.stockService.createStock(stockCreateDto);
//            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "재고가 성공적으로 등록되었습니다.");
//        } catch (Exception e) {
//        	System.out.println(e.getMessage());
//        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "재고 등록에 실패했습니다.");
//        }
//		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
//	}
	
	/**
	 * 재고 수정
	 * @param StockUpdateDto 수정할 재고 정보
	 * @return String
	 */
	@Operation(summary = "재고 수정", description = "(가맹점) 선택한 재고 정보를 수정하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/stock")
	public ResponseEntity<? extends BaseResponseBody> updateStock(
			@RequestBody StockUpdateDto stockUpdateDto) {
		try {
            this.stockService.updateStock(stockUpdateDto);
            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "재고 정보가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "재고 수정에 실패했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	

	/**
	 * 가맹점별 재고 목록 조회
	 * @param Long 가맹점 번호
	 * @return List<StockListDto> 조회된 재고 목록
	 */
	@Operation(summary = "가맹점별 재고 목록 조회", description = "(가맹점) 해당 가맹점의 재고 목록을 조회하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/stock")
	public ResponseEntity<? extends BaseResponseBody> getStockList(
			@RequestParam Long storeNo){
		try {
			List<StockListDto> stockList = this.stockService.getStockList(storeNo);
			data = new HashMap<>();
	        data.put("stockList", stockList);
	        responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "재고 목록를 성공적으로 가져왔습니다.", data);
        } catch (Exception e) {
            responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "재고 목록 조회에 실패했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	

	/**
	 * 재고 삭제
	 * @param Long 재고 번호
	 * @return String
	 */
	@Operation(summary = "재고 삭제", description = "(가맹점) 선택한 재고를 삭제하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@DeleteMapping("/stock")
	public ResponseEntity<? extends BaseResponseBody> deleteStock(
			@RequestParam Long stockNo){
		try {
			this.stockService.deleteStock(stockNo);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "해당 재고를 삭제했습니다.");
		} catch(Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "재고 삭제에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}

	/**
	 * 발주 등록
	 * @param PurchaseOrderCreateDto 생성할 발주 정보
	 * @return String
	 */
	@Operation(summary = "발주 등록", description = "(가맹점) 가맹점에서 신규 발주 등록 시 사용하는 api 입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/purchaseOrder")
	public ResponseEntity<? extends BaseResponseBody> createPurchaseOrder(
			@RequestBody PurchaseOrderCreateDto purchaseOrderCreateDto) {
		try {
            this.purchaseOrderService.createPurchaseOrder(purchaseOrderCreateDto);
            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "성공적으로 등록되었습니다.");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "발주 등록에 실패했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}

	/**
	 * 발주 수정
	 * @param PurchaseOrderUpdateDto 수정할 발주 정보
	 * @return String
	 */	
	@Operation(summary = "발주 수정", description = "(가맹점) 특정 발주 정보 수정 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/purchaseOrder")
	public ResponseEntity<? extends BaseResponseBody> updatePurchaseOrder(
			@RequestBody PurchaseOrderUpdateDto purchaseOrderUpdateDto) {
		try {
            this.purchaseOrderService.updatePurchaseOrder(purchaseOrderUpdateDto);
            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "성공적으로 수정되었습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "발주 수정에 실패했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	


	/**
	 * 발주 목록 조회
	 * @param 
	 * @return List<PurchaseOrderListDto> 조회된 발주 목록
	 */
	@Operation(summary = "발주 목록 조회", description = "(본사) 현재 발주 요청 상태인 발주 정보를 조회하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/api/order")
	public ResponseEntity<? extends BaseResponseBody> getStockList(){
		try {
			List<PurchaseOrderListDto> purchaseOrderList = this.purchaseOrderService.getPurchaseOrderList();
			// 맵에 데이터 삽입
	        data = new HashMap<>();
	        data.put("purchaseOrderList", purchaseOrderList);
	        responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "현재 발주 중인 목록을 성공적으로 가져왔습니다.", data);
        } catch (Exception e) {
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "발주 목록 조회에 실패했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}

	/**
	 * 발주 삭제
	 * @param Long 발주 번호
	 * @return boolean
	 */
	@Operation(summary = "발주 삭제", description = "(가맹점) 특정 발주 정보 삭제 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@DeleteMapping("/purchaseOrder")
	public ResponseEntity<? extends BaseResponseBody> deletePurchaseOrder(
			@RequestParam Long purchaseOrderNo){
		try {
			this.stockService.deleteStock(purchaseOrderNo);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "발주를 삭제하였습니다.");
		} catch(Exception e) {
        	System.out.println(e.getMessage());
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "발주 삭제에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}

	
}
