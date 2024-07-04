package com.nagane.franchise.stock.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nagane.franchise.menu.dto.MenuDto;
import com.nagane.franchise.store.dto.store.StoreDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StokeDto {

    private Long stokeNo;

    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastStockDate;

    private StoreDto store;

    private MenuDto menu;
}
