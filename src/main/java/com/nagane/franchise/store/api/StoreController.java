package com.nagane.franchise.store.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.store.application.StoreService;

/**
 * @author ljy
 * @since 2024.06.28
 * Store controller 코드
 * 지점 관련 controller
 * **/
@RestController
@CrossOrigin(origins = "http://localhost:${pos.port}")
public class StoreController {
	
	// 의존성 주입(di)
	private StoreService storeService;
	
	
}
