package com.nagane.franchise.table.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author ljy
 * @since 2024.07.01
 * StoreTable Code만 받는 Dto 코드
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableCodeDto {

	/* 테이블 코드 */
	private String tableCode;

}
