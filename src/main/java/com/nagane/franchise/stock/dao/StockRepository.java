package com.nagane.franchise.stock.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nagane.franchise.stock.domain.Stock;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 재고 관련 repository 코드
 * **/
public interface StockRepository extends JpaRepository<Stock, Long>{
	
	/* 지점 번호 기준으로 재고 리스트 반환 */
	List<Stock> findByStore_StoreNo(Long storeNo);
	

    @Query("SELECT s FROM Stock s WHERE s.store.storeNo = :storeNo " +
            "AND (s.menu.state = 1)")
    List<Stock> findByStoreStoreNo(@Param("storeNo") Long storeNo);

    @Query("SELECT s FROM Stock s WHERE s.store.storeNo = :storeNo " +
            "AND s.menu.menuNo = :menuNo ")
    Stock findByMenuNoAndStoreNo(@Param("menuNo")Long menuNo, @Param("storeNo")Long storeNo);
    
	/* 메뉴 번호 기준으로 재고 단일 반환 */
	Optional<Stock> findByMenu_MenuNo(Long MenuNo);

	/* 메뉴 번호로 재고 삭제*/
    @Transactional
    void deleteByMenuMenuNo(Long menuNo);
    

    @Modifying
    @Transactional
    @Query("DELETE FROM Stock s WHERE s.menu.menuNo = :menuNo")
    void deleteByMenuNo(@Param("menuNo") Long menuNo);
	
}

