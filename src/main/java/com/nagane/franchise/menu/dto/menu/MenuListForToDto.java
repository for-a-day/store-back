package com.nagane.franchise.menu.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * @author nsr
 * @since 2024.06.29
 * Table Order 용 Menu List dto 코드
 * 테이블오더용 메뉴 목록 dto
 * **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuListForToDto {

	/* 메뉴 번호(pk) */
	private Long menuNo;
	
	/* 메뉴명 */
	private String menuName;
	
	/* 판매가격 */
	private Integer price;

	/* 품절상태 */
	private boolean soldOut;
	
	/* 이미지 바이트 배열 */
	private byte[] imageByte;
	
}
