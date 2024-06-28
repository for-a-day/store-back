package com.nagane.franchise.store.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.06.28
 * Admin Create dto 코드
 * 관리자 신규 등록 & 로그인 코드
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AdminCreateDto {
	
	/** 관리자 id */
	private String adminId;
	
	/** 관리자 password */
	private String adminPassword;
	
}
