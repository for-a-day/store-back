package com.nagane.franchise.sales.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.sales.application.SalesService;
import com.nagane.franchise.sales.dto.SalesDetailDto;
import com.nagane.franchise.sales.dto.SalesListDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
/**
 * @author nsr
 * @since 2024.07.01
 * 매출 관련 controller
 * **/
@Tag(name= "매출 관리 API")
@RestController
@RequiredArgsConstructor
public class SalesController {

	@Autowired
	SalesService salesService;

	/**
	 * 특정 가맹점의 매출 정보 조회
	 * @param Long 가맹점 번호
	 * @return SalesDetailDto
	 */
	@GetMapping("sales")
	public ResponseEntity<SalesDetailDto> getSales(@RequestParam Long storeNo){
		
		try {
			SalesDetailDto salesDetailDto = salesService.getSalesDetail(storeNo);
			return new ResponseEntity<>(salesDetailDto, HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/**
	 * 월별 매출 정보 목록 조회
	 * @param 년도, 월
	 * @return List<SalesListDto>
	 */
	@GetMapping("api/sales")
	public ResponseEntity<List<SalesListDto>> getSales(
			@RequestParam Integer year,
			@RequestParam Integer month){
		
		try {
			List<SalesListDto> salesDetailDto = salesService.getSalesList(year, month);
			return new ResponseEntity<>(salesDetailDto, HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
