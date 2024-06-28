package com.nagane.franchise.stoke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.stoke.domain.Stoke;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 재고 관련 repository 코드
 * **/
public interface StokeRepository extends JpaRepository<Stoke, Long>{
	
	/* 지점 번호 기준으로 재고 리스트 반환 */
	List<Stoke> findByStore(Long storeNo);
	
}
