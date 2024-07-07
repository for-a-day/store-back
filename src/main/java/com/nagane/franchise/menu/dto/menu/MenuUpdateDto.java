package com.nagane.franchise.menu.dto.menu;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nsr
 * @since 2024.06.29
 * Menu Update dto 코드
 * 메뉴 수정시 dto
 * **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateDto {

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

	/* 카테고리 번호(fk) */
	private Long categoryNo;

    private MultipartFile file;
	
}
