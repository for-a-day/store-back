package com.nagane.franchise.sales.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.sales.domain.Sales;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 매출 관련 repository 코드
 * **/
public interface SalesRepository extends JpaRepository<Sales, Long>{
	
	/* 가맹점 번호, 년도, 월로 해당하는 매출 레코드 반환 */
	List<Sales> findByStore_StoreNoAndYearAndMonth(Long storeNo, Integer year, Integer month);
	
	/* 년도, 월로 해당하는 매출 레코드 반환 */
	List<Sales> findByYearAndMonth(Integer year, Integer month);
}
