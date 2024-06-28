package com.nagane.franchise.store.application;

import java.util.List;

import com.nagane.franchise.store.dto.store.StoreCreateDto;
import com.nagane.franchise.store.dto.store.StoreDto;
import com.nagane.franchise.store.dto.store.StoreLoginDto;

/**
 * @author ljy
 * @since 2024.06.28
 * Store Service 코드
 * 지점 관련 service 인터페이스
 * **/
public interface StoreService {
	
	/** 지점 목록 조회 */
	List<StoreDto> getStoreList();
	
	/** 지점 신규 등록(지점장 회원 가입) */
	void createStore(StoreCreateDto storeCreateDto);
	
	/** 지점 정보 수정 */
	void updateStore(StoreDto storeUpdateDto);
	
	/** 지점 삭제 */
	void deleteStore(Long StoreNo);
	
	/** 가맹점 로그인 */
	void loginStore(StoreLoginDto storeLoginDto);
	
}
