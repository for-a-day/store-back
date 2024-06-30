package com.nagane.franchise.menu.dto.menu;

import com.nagane.franchise.menu.dto.category.CategoryCreateDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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
