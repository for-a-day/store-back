package com.nagane.franchise.sales.dto;

import com.nagane.franchise.store.domain.Store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nsr
 * @since 2024.07.01
 * 매출 상세정보 Dto
 * **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SalesDetailDto {

	/* 오늘 매출량 */
	private Integer todaySalesCnt;
	/* 이번달 매출량 */
	private Long thisMonthSalesCnt;
	/*오늘 결제건수*/
	private Integer todayPaymentCnt;
	/* 이번달 결제 건수 */
	private Integer thisMonthPaymentCnt;


}
