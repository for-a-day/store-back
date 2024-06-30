package com.nagane.franchise.menu.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author nsr
 * @since 2024.06.29
 * Category Create dto 코드
 * 카테고리 생성 시 dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CategoryCreateDto {
	// 카테고리 정보
	private String categoryName;

}
