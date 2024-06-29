package com.nagane.franchise.menu.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateDto {

	private Long categoryNo;
	
	// 카테고리 정보
	private String categoryName;
	private Integer categoryState;
}
