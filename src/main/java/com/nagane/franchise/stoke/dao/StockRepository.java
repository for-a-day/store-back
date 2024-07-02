package com.nagane.franchise.stoke.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.stoke.domain.Stock;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 재고 관련 repository 코드
 * **/
public interface StockRepository extends JpaRepository<Stock, Long>{
	
	/* 지점 번호 기준으로 재고 리스트 반환 */
	List<Stock> findByStore_StoreNo(Long storeNo);
	
	/* 메뉴 번호 기준으로 재고 단일 반환 */
	Optional<Stock> findByMenu_MenuNo(Long MenuNo);
	
}

