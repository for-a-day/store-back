package com.nagane.franchise.table.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.table.application.TableService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author ljy
 * @since 2024.06.28
 * Table controller 코드
 * 테이블 오더 관련 controller
 * **/
@Tag(name= "테이블 오더 API")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class TableController {
	
	// 의존성 주입 (di)
	@Autowired
	private TableService tableService;
	
	/**
	 * 
	 * */

}
