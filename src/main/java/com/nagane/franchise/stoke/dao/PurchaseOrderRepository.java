package com.nagane.franchise.stoke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagane.franchise.stoke.domain.PurchaseOrder;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 발주 요청 관련 repository 코드
 * **/
public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Long>{
	
	/* 재고 번호로 발주 상태(state)가 0이거나 1인 레코드 조회 */
    @Query("SELECT po FROM PurchaseOrder po WHERE po.stoke.stokeNo = :stokeNo " +
           "AND (po.state = 0 OR po.state = 1)")
    PurchaseOrder findLatestPurchaseOrderByStokeNo(@Param("stokeNo") Long stokeNo);
    
    /* 발주 상태(state)에 따라 레코드들 리스트로 반환 */
    List<PurchaseOrder> findByState(Integer state);
    
}
