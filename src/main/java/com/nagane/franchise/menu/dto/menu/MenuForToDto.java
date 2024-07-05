package com.nagane.franchise.menu.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nsr
 * @since 2024.06.29
 * Table Order 용 Menu dto 코드
 * 테이블오더용 메뉴 dto
 * **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuForToDto {

	/* 메뉴 번호(pk) */
	private Long menuNo;
	
	/* 메뉴명 */
	private String menuName;
	
	/* 판매가격 */
	private Integer price;
	
	/* 이미지 */
	private String menuImage;
	
	/* 설명 */
	private String description;
	
	/* 품절상태 */
	private boolean soldOut;

}
