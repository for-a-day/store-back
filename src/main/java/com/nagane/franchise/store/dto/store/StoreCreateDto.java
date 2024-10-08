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
 * Store Create dto 코드
 * 지점 생성 시 dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreCreateDto {
	
	/* 지점명 */
	private String storeName;

	/* 대표자 명 */
	private String rprName;

	/* 지점 주소 */
	private String address;

	/* 연락처 */
	private String contact;

	/* 가맹계약일 */
	private Date contractDate;

	/* 만료계약일 */
	private Date expirationDate;

	/* 점포 코드 */
	private String storeCode;

	/* 지역 코드 */
	private String areaCode;
	
}
