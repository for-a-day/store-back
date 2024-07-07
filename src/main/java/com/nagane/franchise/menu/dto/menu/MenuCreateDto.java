package com.nagane.franchise.menu.dto.menu;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author nsr
 * @since 2024.06.29
 * Menu Create dto 코드
 * 메뉴 생성 시 dto
 * **/
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

    private MultipartFile file;
	
	/* 설명 */
	private String description;
	
	
	/* 재료 공급가 */
	private Integer supplyPrice;
	
	/* 카테고리 번호(fk) */
	private Long categoryNo;
}
