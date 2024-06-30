package com.nagane.franchise.menu.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nsr
 * @since 2024.06.29
 * Category Update dto 코드
 * 카테고리 수정 시 dto
 * **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateDto {

	private Long categoryNo;
	
	// 카테고리 정보
	private String categoryName;
	private Integer categoryState;
}
