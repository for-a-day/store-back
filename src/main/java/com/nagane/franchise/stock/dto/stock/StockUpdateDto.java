package com.nagane.franchise.stock.dto.stock;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nsr
 * @since 2024.06.30
 * Stock Update dto 코드
 * 재고 수정시 dto
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateDto {

    private Long stokeNo;

    private Integer quantity;

    private Date lastStockDate;
}
