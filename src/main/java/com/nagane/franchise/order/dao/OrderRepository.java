package com.nagane.franchise.order.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagane.franchise.order.domain.Order;

/**
 * @author ljy, nsr
 * @since 2024.06.28 주문 관련 repository 코드
 **/
public interface OrderRepository extends JpaRepository<Order, Long> {

	/* 점포번호로 주문 리스트 받아오기 */
	List<Order> findByStore_StoreNo(@Param("storeNo") Long storeNo);

	/* 점포번호로 상태가 0(주문 접수) 또는 1(음식 나감)인 주문 리스트 반환 */
	@Query("SELECT o FROM Order o WHERE o.store.storeNo = :storeNo " + "AND (o.state = 0 OR o.state = 1 OR o.state = 2)")
	List<Order> findByStoreNoAndState(@Param("storeNo") Long storeNo);

	/* 조회 당일 날짜와 같은 날 들어온 주문 개수를 세는 쿼리(점포 번호 기준) */
	Integer countByStore_StoreNoAndOrderDateBetween(Long storeNo, LocalDateTime startOfDay, LocalDateTime endOfDay);

	/* 조회 당일 날짜와 같은 날 들어온 주문들을 return하는 쿼리(점포 번호 기준) */
	List<Order> findByStore_StoreNoAndOrderDateBetween(Long storeNo, LocalDateTime startOfDay, LocalDateTime endOfDay);

	/* 결제 일시와 상태(1)와 가맹점 번호로 레코드 리스트 반환 */
	@Query("SELECT o FROM Order o WHERE o.state = 1 AND o.store.storeNo = :storeNo AND o.orderDate BETWEEN :startDate AND :endDate")
	List<Order> findByStateAndStoreNoAndOrderDateBetween(@Param("storeNo") Long storeNo,
			@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

	/*
	 * 테이블 code 기준으로 해당하는 주문 정보 받아오기
	 * 
	 * @Query("SELECT o FROM Order o WHERE o.table.tableCode = :tableCode")
	 * Optional<Order> findByTableCode(@Param("tableCode") String tableCode);
	 */

	/* 테이블 code 기준으로 해당하는 주문 정보 받아오기 */
	@Query("SELECT o FROM Order o WHERE o.table.tableCode = :tableCode " + "AND (o.state = 0 OR o.state = 1)")
	List<Order> findByTableCodeAndState(@Param("tableCode") String tableCode);
	
	/* 테이블 code 기준으로 해당하는 주문 정보 받아오기(주문시간 기준 역순) */
	@Query("SELECT o FROM Order o WHERE o.table.tableCode = :tableCode " + "AND (o.state = 0 OR o.state = 1) ORDER BY o.orderDate DESC")
	List<Order> findByTableCodeAndStateDESC(@Param("tableCode") String tableCode);
	
	@Query("SELECT o FROM Order o WHERE o.store.storeNo = :storeNo " + "AND (o.state = 0 OR o.state = 1)")
	List<Order> findByStoreNoAndState2(@Param("storeNo") Long storeNo);
}
