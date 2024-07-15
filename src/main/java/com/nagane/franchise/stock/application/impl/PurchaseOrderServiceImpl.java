package com.nagane.franchise.stock.application.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.stock.application.PurchaseOrderService;
import com.nagane.franchise.stock.dao.PurchaseOrderRepository;
import com.nagane.franchise.stock.dao.StockRepository;
import com.nagane.franchise.stock.domain.PurchaseOrder;
import com.nagane.franchise.stock.domain.Stock;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stock.dto.purchaseorder.PurchaseOrderUpdateDto;

/**
 * @author nsr
 * @since 2024.06.30
 * 재고 관리 기능
 * **/
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	/**
	 * 발주 등록
	 * @param PurchaseOrderCreateDto 생성할 발주 정보
	 * @return Long 발주 번호
	 */
	@Override
	public Long createPurchaseOrder(PurchaseOrderCreateDto purchaseOrderCreateDto) {
		
		// 1. 재고 엔티티 가져오기
		Stock stock = stockRepository.findById(purchaseOrderCreateDto.getStockNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 재고를 찾을 수 없습니다."));
		
		System.out.println("재고 번호 : " + stock.getStockNo());
		
		Integer quantity = purchaseOrderCreateDto.getQuantity();
		Integer supplyPrice = stock.getMenu().getSupplyPrice();
		
		// 2. 발주 엔티티 생성
		PurchaseOrder purchaseOrder = PurchaseOrder.builder()
				.quantity(quantity)
				.price(supplyPrice * quantity)
				.stock(stock)
				.build();

		// 4. 발주 저장
		PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);
		
		return saved.getPOrderNo();
	}


	/**
	 * 발주 수정
	 * @param PurchaseOrderUpdateDto 수정할 발주 정보
	 * @return Long 발주 번호
	 */
	@Override
	public Long updatePurchaseOrder(PurchaseOrderUpdateDto purchaseOrderUpdateDto) {

		// 1. 기존 발주 정보 가져오기
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderUpdateDto.getPOrderNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 발주를 찾을 수 없습니다."));
		
		// 2. 재고 정보 업데이트
		Stock stock = purchaseOrder.getStock();
		stock.setLastStockDate(new Date());
		Integer stockQuantity = stock.getQuantity(); 
		stock.setQuantity(stockQuantity + purchaseOrder.getQuantity());
		stockRepository.save(stock);
		
		// 3. 발주 정보 업데이트
		purchaseOrder.setState(purchaseOrderUpdateDto.getState());
		
		// 4. 발주 수정
		PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);
		System.out.println(saved.toString());
		
		return saved.getPOrderNo();
	}

	

	/**
	 * 발주 목록 조회
	 * @param 
	 * @return List<PurchaseOrderListDto> 조회된 발주 목록
	 */
	@Override
	public List<PurchaseOrderListDto> getPurchaseOrderList() {

		// 재고 정보 가져오기
	    List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findByState(0);

	    // purchaseOrderList을 PurchaseOrderListDto로 변환
	    List<PurchaseOrderListDto> purchaseOrderListDto = purchaseOrderList.stream()
	            .map(purchaseOrder -> {
	            	PurchaseOrderListDto purchaseOrderDto = new PurchaseOrderListDto();
	            	
	            	purchaseOrderDto.setQuantity(purchaseOrder.getQuantity());
	            	purchaseOrderDto.setOrderDate(purchaseOrder.getOrderDate());
	            	purchaseOrderDto.setPrice(purchaseOrder.getPrice());
	            	purchaseOrderDto.setStoreCode(purchaseOrder.getStock().getStore().getStoreCode());
	            	purchaseOrderDto.setMenuId(purchaseOrder.getStock().getMenu().getMenuId());
	            	
	                return purchaseOrderDto;
	            })
	            .collect(Collectors.toList());

	    return purchaseOrderListDto;
	}
	

	/**
	 * 발주 삭제
	 * @param Long 발주 번호
	 * @return boolean
	 */
	@Override
	public boolean deletePurchaseOrder(Long purchaseOrderNo) {
		
		purchaseOrderRepository.deleteById(purchaseOrderNo);
			
			return true;
	}
	
}
