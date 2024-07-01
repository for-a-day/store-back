package com.nagane.franchise.table.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * StoreTable 관리자 모드 로그인 시 필요 데이터 Dto 코드
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableAdminDto {
	
	/* 테이블 코드 */
	private String tableCode;
	
	/* 점포 코드 */
	private String storeCode;
	
}
