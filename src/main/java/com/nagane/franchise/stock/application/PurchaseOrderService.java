package com.nagane.franchise.stock.application;

import java.util.List;

import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderUpdateDto;

/**
 * @author nsr
 * @since 2024.07.01
 * PurchaseOrder Service 인터페이스
 * **/

public interface PurchaseOrderService {

	public Long createPurchaseOrder(PurchaseOrderCreateDto purchaseOrderCreateDto);
	public Long updatePurchaseOrder(PurchaseOrderUpdateDto purchaseOrderUpdateDto);
	public List<PurchaseOrderListDto> getPurchaseOrderList();
	public boolean deletePurchaseOrder(Long purchaseOrderNo);
}
