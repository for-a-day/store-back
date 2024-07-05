package com.nagane.franchise.order.dto.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.order.dto.ordermenu.OrderMenuDto;
import com.nagane.franchise.order.dto.ordermenu.OrderMenuResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * OrderTableDetailDto 코드
 * 테이블 입장에서 주문 리스트들 받아올 때 주문 정보 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderTableDetailDto {
	
	/* 주문(결제) 번호(pk) */
	private Long orderNo;
	
	/* 금액 */
	private Integer amount;
	
	/* 주문 일시 */
	private LocalDateTime orderDate;
	
	/* 상태 */
	private int state;
	
	/* 결제 방법 */
	private String paymentMethod;
	
	/* 업데이트 날짜 */
	private LocalDateTime updatedDate;
	
	/* order_menu 엔티티와 OneToMany 매핑 */
	private List<OrderMenuResponseDto> orderMenuList = new ArrayList<>();

}
