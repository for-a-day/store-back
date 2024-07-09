package com.nagane.franchise.menu.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nsr
 * @since 2024.06.29
 * Menu List dto 코드
 * 메뉴 목록 dto
 * **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuListDto {

	/* 메뉴 번호(pk) */
	private Long menuNo;
	
	/* 메뉴명 */
	private String menuName;
	
	/* 이미지 */
	private String menuImage;
	
	/* 메뉴 가격 */
	private Integer price;

	/* 이미지 바이트 배열 */
	private byte[] imageByte;
	
	/* 상태(0=단종, 1=판매) */
	private Integer state;
}