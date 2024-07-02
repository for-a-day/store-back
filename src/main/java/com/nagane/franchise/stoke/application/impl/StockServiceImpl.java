package com.nagane.franchise.stoke.application.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.menu.dao.MenuRepository;
import com.nagane.franchise.menu.domain.Menu;
import com.nagane.franchise.stoke.application.StockService;
import com.nagane.franchise.stoke.dao.PurchaseOrderRepository;
import com.nagane.franchise.stoke.dao.StockRepository;
import com.nagane.franchise.stoke.domain.PurchaseOrder;
import com.nagane.franchise.stoke.domain.Stock;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderUpdateDto;
import com.nagane.franchise.stoke.dto.stock.StockCreateDto;
import com.nagane.franchise.stoke.dto.stock.StockListDto;
import com.nagane.franchise.stoke.dto.stock.StockUpdateDto;
import com.nagane.franchise.store.dao.StoreRepository;
import com.nagane.franchise.store.domain.Store;

/**
 * @author nsr
 * @since 2024.06.30
 * 재고 관리 기능
 * **/
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	/**
	 * 재고 등록
	 * @param StockCreateDto 생성할 재고 정보
	 * @return Long 재고 번호
	 */
	@Override
	public Long createStock(StockCreateDto stockCreateDto) {
		
		// 1. 스토어 엔티티 가져오기
		Store store = storeRepository.findById(stockCreateDto.getStoreNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 가맹점을 찾을 수 없습니다."));
		// 2. 메뉴 엔티티 가져오기
		Menu menu = menuRepository.findById(stockCreateDto.getMenuNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));

		// 3. 재고 엔티티 생성
		Stock stock = Stock.builder()
				.store(store)
				.menu(menu)
				.build();

		// 4. 재고 저장
		Stock saved = stockRepository.save(stock);
		
		return saved.getStockNo();
	}


	/**
	 * 재고 수정
	 * @param StockUpdateDto 수정할 재고 정보
	 * @return Long 재고 번호
	 */
	@Override
	public Long updateStock(StockUpdateDto stockUpdateDto) {

		// 1. 기존 재고 정보 가져오기
		Stock stock = stockRepository.findById(stockUpdateDto.getStokeNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 재고를 찾을 수 없습니다."));
		
		// 2. 재고 정보 업데이트
		if(stockUpdateDto.getQuantity() != null)
			stock.setQuantity(stockUpdateDto.getQuantity());
		if(stockUpdateDto.getLastStockDate() != null)
			stock.setLastStockDate(stockUpdateDto.getLastStockDate());
		
		// 3. 재고 수정
		Stock saved = stockRepository.save(stock);
		System.out.println(saved.toString());
		
		return saved.getStockNo();
	}

	/**
	 * 가맹점별 재고 목록 조회
	 * @param Long 가맹점 번호
	 * @return List<StockListDto> 조회된 재고 목록
	 */
	@Override
	public List<StockListDto> getStockList(Long storeNo) {

		// 발주 정보 가져오기
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findLatestPurchaseOrderByStockNo(storeNo);
	    List<Stock> stockList = stockRepository.findByStore_StoreNo(storeNo);

	    // Stock을 StockListDto로 변환
	    List<StockListDto> stockDtoList = stockList.stream()
	            .map(stock -> {
	            	StockListDto stockDto = new StockListDto();
	            	stockDto.setStockNo(stock.getStockNo());
	            	stockDto.setQuantity(stock.getQuantity());
	            	stockDto.setLastStockDate(stock.getLastStockDate());
	            	stockDto.setMenuName(stock.getMenu().getMenuName());
	            	if(purchaseOrder != null) {
	            		stockDto.setPoState(purchaseOrder.getState());
	            		stockDto.setPoQuantity(purchaseOrder.getQuantity());
	            		stockDto.setPoPrice(purchaseOrder.getPrice());	            		
	            	}
	                return stockDto;
	            })
	            .collect(Collectors.toList());

	    return stockDtoList;
	}
	

	/**
	 * 재고 삭제
	 * @param Long 재고 번호
	 * @return boolean
	 */
	@Override
	public boolean deleteStock(Long stockNo) {
		
			stockRepository.deleteById(stockNo);
			
			return true;
	}
	

}
