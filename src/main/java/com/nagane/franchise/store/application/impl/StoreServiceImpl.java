package com.nagane.franchise.store.application.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.store.application.StoreService;
import com.nagane.franchise.store.dao.StoreRepository;
import com.nagane.franchise.store.dto.store.StoreCreateDto;
import com.nagane.franchise.store.dto.store.StoreDto;
import com.nagane.franchise.store.dto.store.StoreLoginDto;

/**
 * @author ljy
 * @since 2024.06.28
 * Store Service Impl 코드
 * 지점 관련 service 상속
 * **/
@Service
public class StoreServiceImpl implements StoreService {
	
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(StoreServiceImpl.class);
	
	// 필요 레포 연결
	private final StoreRepository storeRepository;
	
	// 의존성 주입(di)
	@Autowired
	public StoreServiceImpl(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	/** 지점 목록 조회 */
	@Override
	public List<StoreDto> getStoreList() {
		// TODO Auto-generated method stub
		return null;
	}

	/** 지점 신규 등록(지점장 회원 가입) */
	@Override
	public void createStore(StoreCreateDto storeCreateDto) {
		
		
	}

	/** 지점 정보 수정 */
	@Override
	public void updateStore(StoreDto storeUpdateDto) {
		
	}

	/** 지점 삭제 */
	@Override
	public void deleteStore(Long StoreNo) {
		
	}

	/** 가맹점 로그인 */
	@Override
	public void loginStore(StoreLoginDto storeLoginDto) {
		
	}
	
	
}
