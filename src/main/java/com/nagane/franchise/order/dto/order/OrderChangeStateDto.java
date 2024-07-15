package com.nagane.franchise.order.dto.order;

import com.nagane.franchise.util.enums.OrderStateInstr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * OrderChangeStateDto 코드
 * 주문 상태 변경용 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderChangeStateDto {
	
	/* 주문(결제) 번호(pk) */
	private Long orderNo;

	/* 변경 지시 */
	private OrderStateInstr orderStateInstr;
}
