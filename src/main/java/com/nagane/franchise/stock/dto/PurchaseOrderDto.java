package com.nagane.franchise.stock.dto;

import java.util.Date;

import com.nagane.franchise.stock.domain.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nsr
 * @since 2024.06.30
 * 발주 Dto
 * **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PurchaseOrderDto {

	/* 발주 번호 (PK) */
	private Long pOrderNo;

	/* 수량 */
	private Integer quantity;
	
	/* 발주날짜 */
	private Date orderDate;
	
	/* 발주상태 */
	private Integer state;

	/* 가격 */
	private Integer price;

	/* 재고 */
    private Stock stoke;
}
