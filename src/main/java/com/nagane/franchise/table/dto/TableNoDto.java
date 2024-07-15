package com.nagane.franchise.table.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.nagane.franchise.order.domain.Order;
import com.nagane.franchise.store.domain.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author ljy
 * @since 2024.07.01
 * StoreTable 단일 컬럼용 코드
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableNoDto {

	/* 테이블 번호(pk) */
	private Long tableNo;

}
