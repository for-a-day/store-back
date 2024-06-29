package com.nagane.franchise.menu.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuListForToDto {

	/* 메뉴 번호(pk) */
	private Long menuNo;
	
	/* 메뉴명 */
	private String menuName;
	
	/* 판매가격 */
	private Integer price;

}
