package com.nagane.franchise.order.dto.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.order.dto.ordermenu.OrderMenuDetailDto;
import com.nagane.franchise.order.dto.ordermenu.OrderMenuResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * OrderResponseDto 코드
 * 주문 상세 리스트 조회용 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderDetailResponseDto {
	
	/* 주문(결제) 번호(pk) */
	private Long orderNo;
	
	/* 금액 */
	private Integer amount;
	
	/* 주문 일시 */
	private LocalDateTime orderDate;
	
	/* 결제 방법 */
	private String paymentMethod;
	
	/* 매점 내 테이블 번호 */
	private int tableNumber;
	
	/* order_menu 엔티티와 OneToMany 매핑 */
	private List<OrderMenuDetailDto> orderMenuDetailList = new ArrayList<>();

}
