package com.nagane.franchise.stoke.dto.stock;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nagane.franchise.menu.dto.MenuDto;
import com.nagane.franchise.stoke.dto.StokeDto;
import com.nagane.franchise.store.dto.store.StoreDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockListDto {

    private Long stockNo;

    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastStockDate;

    private String menuName;
    
    private Integer poState;
    
    private Integer poQuantity;
    
    private Integer poPrice;
    
}
