package com.nagane.franchise.table.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author ljy
 * @since 2024.07.02
 * 테이블 정보 업데이트용 Dto 코드
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableUpdateDto {

	/* 테이블 번호(pk) */
	private Long tableNo;

	/* 지점 내 테이블 번호 */
	private int tableNumber;

	/* 테이블명 */
	private String tableName;

}
