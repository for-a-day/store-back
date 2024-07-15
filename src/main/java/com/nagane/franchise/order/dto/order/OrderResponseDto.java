package com.nagane.franchise.order.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.order.dto.ordermenu.OrderMenuResponseDto;
import com.nagane.franchise.util.enums.OrderCase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * OrderResponseDto 코드
 * 주문 조회용 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderResponseDto {
	
	/* 주문(결제) 번호(pk) */
	private Long orderNo;
	
	/* 금액 */
	private Integer amount;
	
	/* 테이블 번호(fk) */
	private Long tableNo;
	
	/* 픽업 여부(DINE_IN 1, TAKEOUT 2) */
	private OrderCase orderCase = OrderCase.DINE_IN;
	
	/* 매점 내 테이블 번호 */
	private int tableNumber;
	
	/* order_menu 엔티티와 OneToMany 매핑 */
	private List<OrderMenuResponseDto> orderMenuList = new ArrayList<>();

}
