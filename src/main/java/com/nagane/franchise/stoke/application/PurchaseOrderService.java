package com.nagane.franchise.stoke.application;

import java.util.List;

import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderUpdateDto;

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
