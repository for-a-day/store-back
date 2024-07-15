package com.nagane.franchise.menu.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nsr
 * @since 2024.06.29
 * Category List dto 코드
 * 카테고리 목록 dto
 * **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDto {

	private Long categoryNo;
	
	// 카테고리 정보
	private String categoryName;
}
