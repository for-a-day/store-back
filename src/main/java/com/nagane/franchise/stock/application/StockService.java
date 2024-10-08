package com.nagane.franchise.stock.application;

import java.util.List;

import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderUpdateDto;
import com.nagane.franchise.stock.dto.stock.StockCreateDto;
import com.nagane.franchise.stock.dto.stock.StockListDto;
import com.nagane.franchise.stock.dto.stock.StockUpdateDto;

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
