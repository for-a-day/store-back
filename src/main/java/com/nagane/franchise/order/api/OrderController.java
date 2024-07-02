package com.nagane.franchise.order.api;

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

import com.nagane.franchise.order.application.OrderService;
import com.nagane.franchise.order.dto.order.OrderChangeStateDto;
import com.nagane.franchise.order.dto.order.OrderCreateDto;
import com.nagane.franchise.order.dto.order.OrderDetailDto;
import com.nagane.franchise.order.dto.order.OrderResponseDto;
import com.nagane.franchise.order.dto.order.OrderUpdateDto;
import com.nagane.franchise.order.dto.order.PaymentResponseDto;
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
 * @since 2024.07.01
 * Order controller 코드
 * 주문 관련 controller
 * **/
@Tag(name= "주문 API", description = "주문 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class OrderController {
	
	// 반환할 데이터를 담을 객체 생성
    private BaseResponseBody responseBody;
    
    // 반환할 데이터를 담을 맵 생성
    private Map<String, Object> data;
    
	// 의존성 주입 (di)
	@Autowired
	private OrderService orderService;
	
	/**
	 * 현재 주문 목록 조회
	 * @param Long storeNo
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "현재 주문 목록 조회", 
			description = "현재 해당 가게에 들어와 있는 주문 목록을 조회하는 메서드입니다."
					+ "\n 이미 완료되거나 환불된 주문은 조회하지 않습니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/order")
    public ResponseEntity<? extends BaseResponseBody> getOrderList(
    		@RequestParam("storeNo") Long storeNo) {
        
        try {
            // OrderResponseDto 리스트를 가져오는 서비스 메서드 호출
            List<OrderResponseDto> orderList = this.orderService.getOrderList(storeNo);
            data = new HashMap<>();
			data.put("orderList", orderList);
			responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "주문 데이터를 가져오는 데에 성공했습니다.", data);
            // 예외 발생 시 오류 처리
 		} catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
        } catch (Exception e) {
        	 responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "주문 데이터를 가져오는 데에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 선택한 주문 상세 정보 조회
	 * @param Long orderNo
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "선택한 주문 상세 정보 조회", 
			description = "선택한 주문의 상세 정보를 조회하는 메서드입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/order/detail")
    public ResponseEntity<? extends BaseResponseBody> getOrder(
    		@RequestParam("orderNo") Long orderNo) {
        
        try {
        	OrderDetailDto orderDetailDto = this.orderService.getOrder(orderNo);
            data = new HashMap<>();
			data.put("order", orderDetailDto);
			responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "주문 데이터를 가져오는 데에 성공했습니다.", data);
			// 예외 발생 시 오류 처리
 		} catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
        } catch (Exception e) {
        	 responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "주문 상세 정보를 가져오는 데에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 결제 목록 조회(영수증)
	 * @param Long storeNo
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "결제 목록 조회(영수증)", 
			description = "가맹점에서 결제 목록 조회(영수증)할 때 사용하는 메서드입니다(당일 항목만 제공)")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/payment")
    public ResponseEntity<? extends BaseResponseBody> getPaymentList(
    		@RequestParam("storeNo") Long storeNo) {
        
        try {
            // PaymentResponseDto 리스트를 가져오는 서비스 메서드 호출
        	List<PaymentResponseDto> paymentList = this.orderService.getPaymentList(storeNo);
            data = new HashMap<>();
			data.put("paymentList", paymentList);
			responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "영수증 정보를 가져오는 데에 성공했습니다.", data);
	    // 예외 발생 시 오류 처리
 		} catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
        } catch (Exception e) {
        	 responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "영수증 정보를 가져오는 데에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
    
	/**
	 * 주문 상태 변경
	 * @param OrderChangeStateDto orderChangeStateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "주문 상태 변경", 
			description = "가맹점에서 주문 상태를 원하는 상태로 변경할 때 사용하는 메서드입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/order/state")
    public ResponseEntity<? extends BaseResponseBody> changeOrderState(
    		@RequestBody OrderChangeStateDto orderChangeStateDto) {
        
        try {
            this.orderService.changeOrderState(orderChangeStateDto);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "해당 주문 상태 변경에 성공했습니다.");
	    // 예외 발생 시 오류 처리
 		} catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
        } catch (Exception e) {
        	 responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 주문 상태 변경에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 단일 결제 환불
	 * @param Long orderNo
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "단일 결제 환불", 
			description = "가맹점에서 단일 결제 환불 시 사용하는 메서드입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@DeleteMapping("/payment")
    public ResponseEntity<? extends BaseResponseBody> refundPayment(
    		@RequestParam("orderNo") Long orderNo) {
        
        try {
            this.orderService.refundPayment(orderNo);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "해당 결제 환불에 성공했습니다.");
	    // 예외 발생 시 오류 처리
 		} catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
        } catch (Exception e) {
        	 responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 결제 환불에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
    
	/**
	 * 해당 테이블 주문 내역 조회
	 * @param Long  tableNo
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "해당 테이블 주문 내역 조회", 
			description = "테이블 오더에서 해당 테이블 주문 내역 조회하는 메서드입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/to/order")
    public ResponseEntity<? extends BaseResponseBody> getTableOrder(
    		@RequestParam("tableCode") String tableCode) {
        
        try {
        	List<OrderDetailDto> orderList = this.orderService.getTableOrder(tableCode);
            data = new HashMap<>();
			data.put("orderList", orderList);
			responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "주문 내역을 불러오는 데에 성공했습니다.", data);
	    // 예외 발생 시 오류 처리
 		} catch (NoSuchElementException se) {
 			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
        } catch (Exception e) {
        	 responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "주문 내역을 불러오는 데에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 주문 신규 등록
	 * @param OrderCreateDto orderCreateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "주문 신규 등록", 
			description = "주문 신규 등록할 때 사용하는 메서드입니다(현재는 테이블 오더에서만 주문 & 결제 가능).")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/order")
    public ResponseEntity<? extends BaseResponseBody> createOrder(
    		@RequestBody OrderCreateDto orderCreateDto) {
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
        	OrderDetailDto newOrder = this.orderService.createOrder(orderCreateDto);
	        data = new HashMap<>();
			data.put("order", newOrder);
			responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "주문에 성공했습니다.", data);
	    } catch (InsufficientStockException ie) {
			responseBody = BaseResponseBody.of(HttpStatus.BAD_REQUEST.value(), ie.getMessage());
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "주문에 실패했습니다.");
		}
		
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	/**
	 * 주문 수정
	 * @param OrderUpdateDto orderUpdateDto
	 * @return Map<String, Object>>
	 */
	@Operation(summary = "주문 수정", 
			description = "가맹점에서 주문 정보 수정 시(현재는 결제 방법만 가능) 사용하는 메서드입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "404", description = "NOT_FOUND", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/order")
    public ResponseEntity<? extends BaseResponseBody> updateOrder(
    		@RequestBody OrderUpdateDto orderUpdateDto) {
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
            this.orderService.updateOrder(orderUpdateDto);
        	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "주문 정보 수정에 성공했습니다.");
        // 예외 발생 시 오류 처리
		} catch (NoSuchElementException se) {
			responseBody = BaseResponseBody.of(HttpStatus.NOT_FOUND.value(), se.getMessage());
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "주문 정보 수정에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }

}
