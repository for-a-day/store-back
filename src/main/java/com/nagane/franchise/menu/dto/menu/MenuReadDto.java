package com.nagane.franchise.menu.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuReadDto {

	/* 메뉴 번호(pk) */
	private Long menuNo;
	
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
	
	/* 상태(0=단종, 1=판매) */
	private Integer state;
}
