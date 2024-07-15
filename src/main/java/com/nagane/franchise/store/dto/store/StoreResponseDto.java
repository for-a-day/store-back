package com.nagane.franchise.store.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.02
 * StoreResponse dto 코드
 * 가맹점 측에서 지점 로그인 시 return용 dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreResponseDto {
	
	/* 지점 번호 (PK) */
	private Long storeNo;
	
	/* 지점명 */
	private String storeName;

	/* 대표자 명 */
	private String rprName;

	/* 경고 횟수 */
	private Integer warningCount;

	/* 점포 코드 */
	private String storeCode;

	
}
