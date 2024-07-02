package com.nagane.franchise.order.dto.ordermenu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * OrderMenuResponseDto Dto 코드
 * 주문한 음식이름이랑 갯수 보내주는 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderMenuResponseDto {
	
	/* 메뉴 번호(pk) */
	private Long menuNo;
	
	/* 메뉴명 */
	private String menuName;
	
	/* 수량 */
	private int quantity;
}
