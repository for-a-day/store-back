package com.nagane.franchise.stock.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author nsr
 * @since 2024.06.30
 * Stock Create dto 코드
 * 재고 생성 시 dto
 * **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockCreateDto {

    private Long storeNo;

    private Long menuNo;
}
