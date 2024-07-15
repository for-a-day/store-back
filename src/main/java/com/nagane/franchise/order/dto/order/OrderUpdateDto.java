package com.nagane.franchise.order.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * OrderUpdateDto 코드
 * 주문 수정 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderUpdateDto {
	
	/* 주문(결제) 번호(pk) */
	private Long orderNo;
	
	/* 금액 */
	// private Integer amount;
	
	/* 결제 방법 */
	private String paymentMethod;
	
	/* 테이블 코드(fk) */
	private String tableCode;

}
