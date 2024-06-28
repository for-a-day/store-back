package com.nagane.franchise.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.order.domain.OrderMenu;
/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 주문 항목 관련 repository 코드
 * **/
public interface OrderMenuRepository  extends JpaRepository<OrderMenu, Long>{
	
	/* 주문 번호 기준으로 일치하는 주문 항목 반환 */
	List<OrderMenu> findByOrderNo(Long orderNo);
	
}
