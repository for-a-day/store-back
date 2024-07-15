package com.nagane.franchise.store.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.06.28
 * Store Login dto 코드
 * 가맹점 로그인 dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreLoginDto {
	
	/* 대표자 명 */
	private String rprName;

	/* 점포 코드 */
	private String storeCode;
	
}
