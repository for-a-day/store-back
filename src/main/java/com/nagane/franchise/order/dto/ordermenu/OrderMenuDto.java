package com.nagane.franchise.order.dto.ordermenu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * Order Menu Dto 코드
 * 주문 항목 기본 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderMenuDto {
	
	/* 주문 항목 번호(pk) */
	private Long orderItemNo;
	
	/* 수량 */
	private int quantity;
	
	/* 메뉴 번호(fk) */
	private Long menuNo;
	
	/* 결제 번호(fk) */
	private Long orderNo;
	
}
