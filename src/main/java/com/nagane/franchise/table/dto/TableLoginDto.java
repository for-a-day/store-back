package com.nagane.franchise.table.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * 테이블 오더 등록 시 Dto 코드
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableLoginDto {
	
	/* 테이블 코드 */
	private String tableCode;
	
	/* 점포 코드 */
	private String storeCode;
	
	/* 지점 내 테이블 번호 */
	private int tableNumber;
	
	/* 테이블명 */
	private String tableName;
	
}
