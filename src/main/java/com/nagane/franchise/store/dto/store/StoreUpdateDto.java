package com.nagane.franchise.store.dto.store;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.06.28
 * Store Update dto 코드
 * 지점 정보 수정용dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreUpdateDto {
	
	/* 지점 번호 (PK) */
	private Long storeNo;
	
	/* 지점명 */
	private String storeName;

	/* 대표자 명 */
	private String rprName;

	/* 지점 주소 */
	private String address;

	/* 연락처 */
	private String contact;

	/* 만료계약일 */
	private Date expirationDate;

	/* 경고 횟수 */
	private Integer warningCount;

	/* 점포 코드 */
	// private String storeCode;

	/* 지역 코드 */
	private String areaCode;

	/* 상태 0=폐점, 1=영업 */
	// private Integer state;
	
}
