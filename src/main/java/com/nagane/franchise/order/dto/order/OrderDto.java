package com.nagane.franchise.order.dto.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.order.dto.ordermenu.OrderMenuDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * Order Dto 코드
 * 주문 기본 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderDto {
	
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
	
	/* 점포 번호(fk) */
	 private Long storeNo;
	
	/* 테이블 번호(fk) */
	private Long tableNo;
	
	/* order_menu 엔티티와 OneToMany 매핑 */
	private List<OrderMenuDto> orderMenuList = new ArrayList<>();

}