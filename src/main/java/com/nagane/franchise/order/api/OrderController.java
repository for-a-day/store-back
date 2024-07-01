package com.nagane.franchise.order.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.order.application.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.07.01
 * Order controller 코드
 * 주문 관련 controller
 * **/
@Tag(name= "주문 API")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class OrderController {
	
	// 의존성 주입 (di)
	@Autowired
	private OrderService orderService;
	
	/**
	 * 현재 주문 목록 조회
	 * @param Long storeNo
	 * @return Map<String, Object>>
	 */
	@GetMapping("/order")
    public ResponseEntity<Map<String, Object>> getOrderList(
    		@RequestParam("storeNo") Long storeNo) {
		
        // 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
            
            // 맵에 데이터 삽입
            response.put("message", "주문 데이터를 가져오는 데에 성공했습니다.");
            response.put("data", null);

            // ResponseEntity에 맵과 HttpStatus.OK를 포함하여 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "주문 데이터를 가져오는 데에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 선택한 주문 상세 정보 조회
	 * @param Long orderNo
	 * @return Map<String, Object>>
	 */
	@GetMapping("/order/detail")
    public ResponseEntity<Map<String, Object>> getOrder(
    		@RequestParam("orderNo") Long orderNo) {
		
        // 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
            
            // 맵에 데이터 삽입
            response.put("message", "주문 상세 정보를 가져오는 데에 성공했습니다.");
            response.put("data", null);

            // ResponseEntity에 맵과 HttpStatus.OK를 포함하여 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "주문 상세 정보를 가져오는 데에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 결제 목록 조회(영수증)
	 * @param Long storeNo
	 * @return Map<String, Object>>
	 */
    public ResponseEntity<Map<String, Object>> getPaymentList(
    		@RequestParam("storeNo") Long storeNo) {
		
        // 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
            
            // 맵에 데이터 삽입
            response.put("message", "영수증 정보를 가져오는 데에 성공했습니다.");
            response.put("data", null);

            // ResponseEntity에 맵과 HttpStatus.OK를 포함하여 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "영수증 정보를 가져오는 데에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 단일 결제 환불
	 * @param Long orderNo
	 * @return Map<String, Object>>
	 */
    public ResponseEntity<Map<String, Object>> refundPayment(
    		@RequestParam("orderNo") Long orderNo) {
		
        // 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
            
            // 맵에 데이터 삽입
            response.put("message", "해당 결제 환불에 성공했습니다.");
            response.put("data", null);

            // ResponseEntity에 맵과 HttpStatus.OK를 포함하여 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "해당 결제 환불에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	/**
	 * 해당 테이블 주문 내역 조회
	 * @param Long  tableNo
	 * @return Map<String, Object>>
	 */
    public ResponseEntity<Map<String, Object>> getTableOrder(
    		@RequestParam("tableNo") Long tableNo) {
		
        // 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
            
            // 맵에 데이터 삽입
            response.put("message", "주문 내역을 불러오는 데에 성공했습니다.");
            response.put("data", null);

            // ResponseEntity에 맵과 HttpStatus.OK를 포함하여 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "주문 내역을 불러오는 데에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * 주문 신규 등록
	 * @param OrderCreateDto orderCreateDto
	 * @return Map<String, Object>>
	 */
    public ResponseEntity<Map<String, Object>> createOrder(
    		@RequestParam("tableNo") Long tableNo) {
		
        // 반환할 데이터를 담을 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // OrderDto 리스트를 가져오는 서비스 메서드 호출
            
            // 맵에 데이터 삽입
            response.put("message", "주문에 성공했습니다.");
            response.put("data", null);

            // ResponseEntity에 맵과 HttpStatus.OK를 포함하여 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("message", "주문에 실패했습니다.");
        	response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
