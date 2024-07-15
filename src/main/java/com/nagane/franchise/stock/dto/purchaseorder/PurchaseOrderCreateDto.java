package com.nagane.franchise.stock.dto.purchaseorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nsr
 * @since 2024.06.30
 * 발주 생성시 Dto
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderCreateDto {

	/* 수량 */
	private Integer quantity;

	/* 재고 */
    private Long stockNo;
}
