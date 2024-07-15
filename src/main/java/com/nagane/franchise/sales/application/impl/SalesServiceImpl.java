package com.nagane.franchise.sales.application.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.order.dao.OrderRepository;
import com.nagane.franchise.order.domain.Order;
import com.nagane.franchise.sales.application.SalesService;
import com.nagane.franchise.sales.dao.SalesRepository;
import com.nagane.franchise.sales.domain.Sales;
import com.nagane.franchise.sales.dto.SalesDetailDto;
import com.nagane.franchise.sales.dto.SalesListDto;

@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	SalesRepository salesRepository;

	@Autowired
	OrderRepository orderRepository;
	
	/**
	 * 특정 가맹점의 매출 정보 조회
	 * @param Long 가맹점 번호
	 * @return SalesDetailDto
	 */
	public SalesDetailDto getSalesDetail(Long storeNo) {
		
		// 오늘 날짜
	    LocalDate today = LocalDate.now();
	    Integer year = today.getYear();
	    Integer month = today.getMonthValue();

	    // 이번 달의 첫 번째 날
	    LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
	    LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
	    
	    // 이번 달의 마지막 날
	    LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
	    LocalDateTime endOfMonth = lastDayOfMonth.atTime(23, 59, 59, 999999999);

	    // 오늘의 시작 시간
	    LocalDateTime startOfToday = today.atStartOfDay();
	    // 오늘의 끝 시간
	    LocalDateTime endOfToday = today.atTime(23, 59, 59, 999999999);
	    
		// 이번달 매출
		Sales sales = salesRepository.findByStore_StoreNoAndYearAndMonth(storeNo, year, month);
		
		// 이번달 주문 건수
		List<Order> thisMonthPaymentCnt = orderRepository.findByStateAndStoreNoAndOrderDateBetween(storeNo, startOfMonth, endOfMonth);
		// 오늘 주문
		List<Order> todayOrders = orderRepository.findByStateAndStoreNoAndOrderDateBetween(storeNo, startOfToday, endOfToday);
		// 오늘 매출
		Integer todaySales = 0;
		for(int i = 0; i < todayOrders.size(); i++) {
			todaySales += todayOrders.get(i).getAmount();
		}
		
		SalesDetailDto salesDto = new SalesDetailDto();
		if(sales != null) {
			salesDto.setThisMonthSalesCnt(sales.getMonthlySales());			
		}
		else salesDto.setThisMonthSalesCnt(0l);
		salesDto.setThisMonthPaymentCnt(thisMonthPaymentCnt.size());
		salesDto.setTodayPaymentCnt(todayOrders.size());
		salesDto.setTodaySalesCnt(todaySales);

		
		System.out.println("year : " + year + "month : " + month);
		System.out.println("startOfMonth : " + startOfMonth + "endOfMonth : " + endOfMonth);
		System.out.println("startOfToday : " + startOfToday + "endOfToday : " + endOfToday);
		System.out.println(salesDto.toString());
		
	
		return salesDto;
	}
	
	
	/**
	 * 월별 매출 정보 목록 조회
	 * @param 년도, 월
	 * @return List<SalesListDto>
	 */
	public List<SalesListDto> getSalesList(Integer year, Integer month) {
		
		List<Sales> salesList = salesRepository.findByYearAndMonth(year, month);
		
		List<SalesListDto> salesDtoList =  salesList.stream()
	            .map(sales -> {
	            	SalesListDto salesListDto = new SalesListDto();

	        		salesListDto.setYear(year);
	        		salesListDto.setMonth(month);
	        		salesListDto.setMonthlySales(sales.getMonthlySales());
	        		salesListDto.setStoreCode(sales.getStore().getStoreCode());
	        		
	                return salesListDto;
	            })
	            .collect(Collectors.toList());
		
		return salesDtoList;
		
	}
}
