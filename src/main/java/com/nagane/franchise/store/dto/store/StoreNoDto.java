package com.nagane.franchise.store.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.07.01
 * StoreNo dto 코드
 * restful 형식 지키기 위한 dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreNoDto {
	
	/* 지점 번호 (PK) */
	private Long storeNo;
	
}
