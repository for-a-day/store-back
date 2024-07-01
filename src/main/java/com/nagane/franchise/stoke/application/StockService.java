package com.nagane.franchise.stoke.application;

import java.util.List;

import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderUpdateDto;
import com.nagane.franchise.stoke.dto.stock.StockCreateDto;
import com.nagane.franchise.stoke.dto.stock.StockListDto;
import com.nagane.franchise.stoke.dto.stock.StockUpdateDto;

/**
 * @author nsr
 * @since 2024.06.30
 * Stock Service 인터페이스
 * **/

public interface StockService {

	public Long createStock(StockCreateDto stockCreateDto);
	public Long updateStock(StockUpdateDto stockUpdateDto);
	public List<StockListDto> getStockList(Long storeNo);
	public boolean deleteStock(Long stockNo);
}
