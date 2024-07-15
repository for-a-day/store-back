package com.nagane.franchise.store.application.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagane.franchise.global.config.JwtUtil;
import com.nagane.franchise.store.application.StoreService;
import com.nagane.franchise.store.dao.AdminRepository;
import com.nagane.franchise.store.dao.StoreRepository;
import com.nagane.franchise.store.domain.Store;
import com.nagane.franchise.store.dto.store.StoreCreateDto;
import com.nagane.franchise.store.dto.store.StoreDto;
import com.nagane.franchise.store.dto.store.StoreResponseDto;
import com.nagane.franchise.store.dto.store.StoreUpdateDto;
import com.nagane.franchise.util.exceptions.InsufficientStockException;

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
	private final StoreRepository storeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtUtil jwtUtil;
	
    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.storeRepository = storeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }
	
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
		Store nowStore = this.storeRepository.findById(storeUpdateDto.getStoreNo())
                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));

		// 기존 엔티티에 새로운 값 설정
		nowStore.setStoreName(storeUpdateDto.getStoreName());
		nowStore.setRprName(storeUpdateDto.getRprName());
		nowStore.setAddress(storeUpdateDto.getAddress());
		nowStore.setContact(storeUpdateDto.getContact());
		nowStore.setExpirationDate(storeUpdateDto.getExpirationDate());
		nowStore.setWarningCount(storeUpdateDto.getWarningCount());
		// existingStore.setStoreCode(storeUpdateDto.getStoreCode());
		nowStore.setAreaCode(storeUpdateDto.getAreaCode());
		nowStore.setState(storeUpdateDto.getState());
		
		// 데이터 업데이트
		this.storeRepository.save(nowStore);
		
	}

	/** 지점 삭제 */
	@Override
	public void deleteStore(Long storeNo) {
		LOGGER.info("[deleteStore] input StoreNo : {}", storeNo);
		
		// 지점 번호(pk) 기준으로 해당하는 데이터 상태 변경
		// 없을 시, NoSuchElementException throw
		Store deleteStore = this.storeRepository.findById(storeNo)
				.orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
		
		// 만약 주문 기록이 존재하면 삭제 불가
		if (deleteStore.getOrderList().size() == 0) {
			this.storeRepository.delete(deleteStore);
		} else {
			throw new InsufficientStockException("해당 지점 삭제가 불가합니다.");
		}
	}

	/** 가맹점 로그인 */
	@Override
	public Map<String, Object> loginStore(String rprName, String StoreCode){
		LOGGER.info("[loginStore] input rprName : {}", rprName);
		
		// 해당 가맹점 코드, 대표자명으로 db 조회하여 해당 데이터 있는지 검증
		// 없을 시, NoSuchElementException throw
		Optional<Store> storeOptional = this.storeRepository.findByRprName(rprName);
		
		if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            if (StoreCode.equals(store.getStoreCode())) {
            	System.out.println("일치함");
                Map<String, Object> claims = new HashMap<>();
                claims.put("sub", store.getRprName().toString());

                String accessToken = jwtUtil.generateAccessToken(claims);
                String refreshToken = jwtUtil.generateRefreshToken(claims);

                Map<String, Object> response = new HashMap<>();
                response.put("accessToken", accessToken);
                response.put("refreshToken", refreshToken);
                

                StoreResponseDto storeResponseDto = StoreResponseDto.builder()
        				.storeNo(store.getStoreNo())
        				.storeName(store.getStoreName())
        				.rprName(store.getRprName())
        				.warningCount(store.getWarningCount())
        				.storeCode(store.getStoreCode())
        				.build();
                
                
                response.put("store", storeResponseDto);
                return response;
            }
            System.out.println("일치하지 않음");
        }

        return null;
		
//	
//		// 지점(pos) 측에서 필요한 정보 dto에 담아줌
//		StoreResponseDto storeResponseDto = StoreResponseDto.builder()
//				.storeNo(nowStore.getStoreNo())
//				.storeName(nowStore.getStoreName())
//				.rprName(nowStore.getRprName())
//				.warningCount(nowStore.getWarningCount())
//				.storeCode(nowStore.getStoreCode())
//				.build();
//		
//		return storeResponseDto;
	
	}
	
	
}
