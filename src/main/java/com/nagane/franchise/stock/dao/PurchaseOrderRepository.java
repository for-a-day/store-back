package com.nagane.franchise.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagane.franchise.stock.domain.PurchaseOrder;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 발주 요청 관련 repository 코드
 * **/
public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Long>{
	
	/* 재고 번호로 발주 상태(state)가 0인 레코드 조회 */
    @Query("SELECT po FROM PurchaseOrder po WHERE po.stock.stockNo = :stockNo " +
            "AND (po.state = 0)")
    PurchaseOrder findLatestPurchaseOrderByStockNo(@Param("stockNo") Long stockNo);
    
    /* 발주 상태(state)에 따라 레코드들 리스트로 반환 */
    List<PurchaseOrder> findByState(Integer state);
    
}
