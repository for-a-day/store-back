package com.nagane.franchise.order.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.order.dto.ordermenu.OrderMenuResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * OrderCreateDto 코드
 * 주문 생성 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderCreateDto {
	
	/* 금액 */
	private Integer amount;
	
	/* 결제 방법 */
	private String paymentMethod;
	
	/* 점포 번호(fk) */
	 private String storeCode;
	
	/* 테이블 코드(fk) */
	private String tableCode;
	
	/* order_menu 엔티티와 OneToMany 매핑 */
	private List<OrderMenuResponseDto> orderMenuList = new ArrayList<>();
}
