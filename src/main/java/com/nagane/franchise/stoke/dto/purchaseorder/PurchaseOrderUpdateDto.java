package com.nagane.franchise.stoke.dto.purchaseorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nsr
 * @since 2024.06.30
 * 발주 수정시 Dto
 * **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PurchaseOrderUpdateDto {

	/* 발주 번호 (PK) */
	private Long pOrderNo;
	
	/* 발주상태 */
	private Integer state;

}
