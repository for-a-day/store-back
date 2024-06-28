package com.nagane.franchise.table.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.table.domain.StoreTable;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 테이블 관련 repository 코드
 * **/
public interface StoreTableRepository extends JpaRepository<StoreTable, Long>{
	
	/* 지점 번호로 해당하는 테이블 리스트 반환 */
	List<StoreTable> findByStoreNo(Long storeNo);
	
	/* 테이블 코드 기준으로 레코드 존재 여부 조회 */
	StoreTable findByTableCode(String tableCode);
	
	/* 테이블 코드와 지점 코드로 존재 여부 확인 */
}
