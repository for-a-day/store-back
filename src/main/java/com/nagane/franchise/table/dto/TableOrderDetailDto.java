package com.nagane.franchise.table.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.nagane.franchise.order.domain.Order;
import com.nagane.franchise.order.dto.order.OrderDetailDto;
import com.nagane.franchise.order.dto.order.OrderTableDetailDto;
import com.nagane.franchise.store.domain.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author ljy
 * @since 2024.07.05
 * TableOrderDetailDto 각 테이블 상세 주문 정보 조회 코드
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableOrderDetailDto {
	

	/* 테이블 번호(pk) */
	private Long tableNo;

	/* 테이블 코드 */
	private String tableCode;

	/* 등록날짜 */
	private LocalDateTime registerDate;

	/* 지점 내 테이블 번호 */
	private int tableNumber;

	/* 테이블명 */
	private String tableName;

	/* 상태 */
	private int state;

	/* order 엔티티와 OneToMany 매핑 */
	private List<OrderTableDetailDto> orderList;

}
