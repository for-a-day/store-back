package com.nagane.franchise.store.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.store.application.StoreService;
import com.nagane.franchise.store.dao.StoreRepository;
import com.nagane.franchise.store.domain.Store;
import com.nagane.franchise.store.dto.store.StoreCreateDto;
import com.nagane.franchise.store.dto.store.StoreDto;
import com.nagane.franchise.store.dto.store.StoreLoginDto;
import com.nagane.franchise.store.dto.store.StoreUpdateDto;

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
	
	// 필요 레포 연결, 의존성 주입(di)
	@Autowired
	StoreRepository storeRepository;
	
	/** 지점 목록 조회 */
	@Override
	public List<StoreDto> getStoreList() {
		LOGGER.info("[getStoreList] get Store List");
		
		// 모든 store 목록 조회
		List<Store> storeList = this.storeRepository.findAll();
		
		// return할 changedStoreList 미리 생성
		List<StoreDto> changedStoreList = new ArrayList<>();
		
		// 각 store 엔티티 객체 StoreDto로 변경해서 changedStoreList에 추가 
		storeList.forEach(store -> {
			StoreDto storeDto = StoreDto.builder()
					.storeNo(store.getStoreNo())
					.storeName(store.getStoreName())
					.rprName(store.getRprName())
					.address(store.getAddress())
					.contact(store.getContact())
					.contractDate(store.getContractDate())
					.expirationDate(store.getExpirationDate())
					.warningCount(store.getWarningCount())
					.storeCode(store.getStoreCode())
					.areaCode(store.getAreaCode())
					.state(store.getState())
					.build();
			
			changedStoreList.add(storeDto);
		});
		
		return changedStoreList;
	}

	/** 지점 신규 등록(지점장 회원 가입) */
	@Override
	public void createStore(StoreCreateDto storeCreateDto) {
		LOGGER.info("[createStore] input storeCreateDto : {}", storeCreateDto);
		
		// 생성용 Store 엔티티 객체 생성
		Store createStore = Store.builder()
				.storeName(storeCreateDto.getStoreName())
				.rprName(storeCreateDto.getRprName())
				.address(storeCreateDto.getAddress())
				.contact(storeCreateDto.getContact())
				.contractDate(storeCreateDto.getContractDate())
				.expirationDate(storeCreateDto.getExpirationDate())
				.storeCode(storeCreateDto.getStoreCode())
				.areaCode(storeCreateDto.getAreaCode())
				.build();
		
		// 신규 지점 등록
		this.storeRepository.save(createStore);
	}

	/** 지점 정보 수정 */
	@Override
	public void updateStore(StoreUpdateDto storeUpdateDto) {
		LOGGER.info("[updateStore] input storeUpdateDto : {}", storeUpdateDto);
		
		// 수정할 store 데이터 불러오기
		Store existingStore = this.storeRepository.findById(storeUpdateDto.getStoreNo())
                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));

		// 기존 엔티티에 새로운 값 설정
		existingStore.setStoreName(storeUpdateDto.getStoreName());
		existingStore.setRprName(storeUpdateDto.getRprName());
		existingStore.setAddress(storeUpdateDto.getAddress());
		existingStore.setContact(storeUpdateDto.getContact());
		existingStore.setExpirationDate(storeUpdateDto.getExpirationDate());
		existingStore.setWarningCount(storeUpdateDto.getWarningCount());
		// existingStore.setStoreCode(storeUpdateDto.getStoreCode());
		existingStore.setAreaCode(storeUpdateDto.getAreaCode());
		// existingStore.setState(storeUpdateDto.getState());
		
		// 데이터 업데이트
		this.storeRepository.save(existingStore);
		
	}

	/** 지점 삭제 */
	@Override
	public void deleteStore(Long storeNo) {
		LOGGER.info("[deleteStore] input StoreNo : {}", storeNo);
		
		// 지점 번호(pk) 기준으로 해당하는 데이터 상태 변경
		Optional<Store> deleteStore = this.storeRepository.findById(storeNo);
		
		// 없을 시, NoSuchElementException throw
		Store updateStateStore = deleteStore
				.orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
		
		// 상태값 0일 시 1로, 1일 시 0으로 변경
		if (updateStateStore.getState() == 1) {
			updateStateStore.setState(0);
		} 
		/*
		 * else { updateStateStore.setState(1); }
		 */
		
		// state 값 업데이트
		this.storeRepository.save(updateStateStore);
		
	}

	/** 가맹점 로그인 */
	@Override
	public void loginStore(StoreLoginDto storeLoginDto) throws NoSuchElementException {
		LOGGER.info("[loginStore] input storeLoginDto : {}", storeLoginDto);
		
		// 해당 가맹점 코드, 대표자명으로 db 조회하여 해당 데이터 있는지 검증
		Optional<Store> optionalStore = this.storeRepository
				.findByStoreCodeAndRprName(storeLoginDto.getStoreCode(), storeLoginDto.getRprName());
	
		// 없을 시, NoSuchElementException throw
		optionalStore.orElseThrow(() -> new NoSuchElementException("no record"));
	}
	
	
}
