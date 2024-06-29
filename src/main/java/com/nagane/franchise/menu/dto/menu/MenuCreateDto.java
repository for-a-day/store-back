package com.nagane.franchise.menu.dto.menu;

import com.nagane.franchise.menu.dto.category.CategoryListForToDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuCreateDto {

	/* 메뉴명 */
	private String menuName;
	
	/* 메뉴 코드 */
	private String menuId;
	
	/* 판매가격 */
	private Integer price;
	
	/* 이미지 */
	private String menuImage;
	
	/* 설명 */
	private String description;
	
	
	/* 재료 공급가 */
	private Integer supplyPrice;
	
	/* 카테고리 번호(fk) */
	private Long categoryNo;
}
