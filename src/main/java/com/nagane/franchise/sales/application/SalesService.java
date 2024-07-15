package com.nagane.franchise.sales.application;

import java.util.List;

import com.nagane.franchise.sales.dto.SalesDetailDto;
import com.nagane.franchise.sales.dto.SalesListDto;

/**
 * @author nsr
 * @since 2024.07.01
 * Sales Service 인터페이스
 * **/
public interface SalesService {
	public SalesDetailDto getSalesDetail(Long storeNo);
	public List<SalesListDto> getSalesList(Integer year, Integer month);
}
