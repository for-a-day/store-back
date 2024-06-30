package com.nagane.franchise.stoke.dto.purchaseorder;

import java.util.Date;

import com.nagane.franchise.stoke.domain.Stock;
import com.nagane.franchise.stoke.dto.PurchaseOrderDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nsr
 * @since 2024.06.30
 * 발주 목록 조회시 Dto
 * **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PurchaseOrderListDto {


	/* 수량 */
	private Integer quantity;
	
	/* 발주날짜 */
	private Date orderDate;

	/* 가격 */
	private Integer price;

	/* 가맹점 코드(아이디) */
    private String storeCode;
    
	/* 메뉴 코드(아이디) */
    private String menuId;

}
