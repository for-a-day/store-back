package com.nagane.franchise.table.application.impl;

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
import com.nagane.franchise.order.dao.OrderRepository;
import com.nagane.franchise.order.domain.Order;
import com.nagane.franchise.order.dto.order.OrderDetailDto;
import com.nagane.franchise.order.dto.order.OrderResponseDto;
import com.nagane.franchise.order.dto.order.OrderTableDetailDto;
import com.nagane.franchise.order.dto.ordermenu.OrderMenuResponseDto;
import com.nagane.franchise.store.dao.StoreRepository;
import com.nagane.franchise.store.domain.Store;
import com.nagane.franchise.table.application.TableService;
import com.nagane.franchise.table.dao.StoreTableRepository;
import com.nagane.franchise.table.domain.StoreTable;
import com.nagane.franchise.table.dto.TableAdminDto;
import com.nagane.franchise.table.dto.TableDto;
import com.nagane.franchise.table.dto.TableLoginDto;
import com.nagane.franchise.table.dto.TableOrderDetailDto;
import com.nagane.franchise.table.dto.TableResponseDto;
import com.nagane.franchise.table.dto.TableUpdateDto;
import com.nagane.franchise.util.TableCodeGenerator;
import com.nagane.franchise.util.exceptions.InsufficientStockException;

/**
 * @author ljy
 * @since 2024.07.01
 * Table Service 코드
 * 테이블 Service 상속
 * **/
@Service
public class TableServiceImpl implements TableService {

	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(TableServiceImpl.class);
	
	// 필요 레포 연결, 의존성 주입(di)
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	StoreTableRepository tableRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	JwtUtil jwtUtil;
	
	/**
	 * 테이블 목록 조회
	 * 해당 지점 번호로 해당하는 테이블 목록 return
	 * @param Long
	 * @return List<TableResponseDto>
	 */
	@Override
	public List<TableResponseDto> getTableList(Long storeNo) {
	    LOGGER.info("[getTableList] input storeNo : {}", storeNo);
	    try {
	        // 해당 가맹점 받아오기
	        Store nowStore = this.storeRepository.findById(storeNo)
	                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
	        
	        // 모든 table 목록 조회
	        List<StoreTable> tableList = this.tableRepository.findByStore_StoreNo(nowStore.getStoreNo());
	        
	        // return할 changedTableList 미리 생성
	        List<TableResponseDto> changedTableList = new ArrayList<>();
	        
	        // 각 table 엔티티 객체 TableResponseDto로 변경해서 changedTableList에 추가
	        tableList.forEach(table -> {
	            TableResponseDto tableResponseDto = TableResponseDto.builder()
	                    .tableNo(table.getTableNo())
	                    .tableCode(table.getTableCode())
	                    .registerDate(table.getRegisterDate())
	                    .tableNumber(table.getTableNumber() == null ? -1 : table.getTableNumber())
	                    .tableName(table.getTableName() == null ? "" : table.getTableName())
	                    .state(table.getState())
	                    .build();
	            
	            changedTableList.add(tableResponseDto);
	        });
	        
	        return changedTableList;
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while getting table list: ", e);
	        throw e;
	    }
	}

	/** 테이블 상세 정보 조회 */
	@Override
	public TableResponseDto getTableDetail(String tableCode) {
		LOGGER.info("[getTableDetail] input tableCode : {}", tableCode);
		
		try {
			// 해당 테이블 정보 받아오기
			// 지정한 table 데이터 불러오기
			StoreTable nowTable = this.tableRepository.findByTableCode(tableCode)
					.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
			
			TableResponseDto tableResponseDto = TableResponseDto.builder()
	                .tableNo(nowTable.getTableNo())
	                .tableCode(nowTable.getTableCode())
	                .registerDate(nowTable.getRegisterDate())
	                .tableNumber(nowTable.getTableNumber() == null ? -1 : nowTable.getTableNumber())
	                .tableName(nowTable.getTableName() == null ? "" : nowTable.getTableName())
	                .state(nowTable.getState())
	                .build();
			
			return tableResponseDto;
		} catch (Exception e) {
			LOGGER.error("Error occurred while getting table list: ", e);
	        throw e;
		}
	};
	
	/**
	 * 테이블 생성
	 * 해당 지점에 귀속되는 테이블 신규 생성
	 * @param Long
	 * @return 
	 */
	@Override
	public String createTable(Long storeNo) {
		LOGGER.info("[createTable] input storeNo : {}", storeNo);

		// 해당 가맹점 받아오기
		Store nowStore = this.storeRepository.findById(storeNo)
                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
		
		// 새로운 테이블 코드 생성
		String newTableCode = "";
		
		// random 6자리
		// 기존 테이블 코드와 조회해보고 없으면 break
		// 있으면 코드 재생성
		while (true) {
			newTableCode = TableCodeGenerator.generateTableCode();
			
			Optional<StoreTable> findTable = this.tableRepository.findByTableCode(newTableCode);
			
			if (!findTable.isPresent()) {
				break;
			}
		}
		
		// 신규 엔티티 생성
		StoreTable newStoreTable = StoreTable.builder()
				.tableCode(newTableCode)
				.store(nowStore)
				.build();
		
		LOGGER.info("[createTable] create new Table entity : {}", newStoreTable);
		
		// db에 저장
		StoreTable savedTable = this.tableRepository.save(newStoreTable);
		
		return savedTable.getTableCode();
	}
	
	/**
	 * 테이블 수정
	 * @param TableUpdateDto
	 * @return void
	 */
	@Override
	public void updateTable(TableUpdateDto tableUpdateDto) {
		// 지정한 table 데이터 불러오기
		StoreTable nowTable = this.tableRepository.findById(tableUpdateDto.getTableNo())
				.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
		
		// 원하는 정보 입력한 뒤, 수정
		nowTable.setTableNumber(tableUpdateDto.getTableNumber());
		nowTable.setTableName(tableUpdateDto.getTableName());
		
		this.tableRepository.save(nowTable);
    }
	
	/**
	 * 테이블 삭제
	 * @param Long
	 * @return void
	 */
	@Override
	public void deleteTable(Long tableNo) {
		// 지정한 table 데이터 불러오기
		StoreTable nowTable = this.tableRepository.findById(tableNo)
				.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
		
		// 연관된 주문이 0개일 시, 테이블 삭제
		if (nowTable.getOrderList().size() == 0) {
			this.tableRepository.delete(nowTable);
		} else {
			throw new InsufficientStockException("해당 테이블 삭제가 불가합니다.");
		}
    }
	
	/**
	 * 현재 테이블 내 주문 내역(0, 1. 환불 제외) 다 2로 수정
	 * @param 
	 * @return Map<String, Object>>
	 */
	@Override
	public void clearTable(Long tableNo) {
		// 지정한 table 데이터 불러오기
		StoreTable nowTable = this.tableRepository.findById(tableNo)
				.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
		
		// 현재 테이블 기준으로 상태 0이나 1인 주문만 불러오기
		List<Order> nowOrderList = this.orderRepository.findByTableCodeAndState(nowTable.getTableCode());
		
		// 상태 손님 나감(2)로 변경
		nowOrderList.forEach(nowOrder -> {
			nowOrder.setState(2);
			this.orderRepository.save(nowOrder);
		});
		
	};

	
	/** 선택한 테이블 주문 리스트 조회 */
	@Override
	public TableOrderDetailDto getTableOrderList(Long tableNo) {
		try {
			// 해당 테이블 정보 받아오기
			// 지정한 table 데이터 불러오기
			StoreTable nowTable = this.tableRepository.findById(tableNo)
					.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
			
			// 현재 가용한 주문만 불러옴
			List<Order> nowOrderList = this.orderRepository.findByTableCodeAndState(nowTable.getTableCode());
			
			List<OrderTableDetailDto> orderList = new ArrayList<>();
			
			nowOrderList.forEach( order -> {
							List<OrderMenuResponseDto> orderMenuList = new ArrayList<>();
							order.getOrderMenuList().forEach( orderMenu -> {
								OrderMenuResponseDto changedOrderMenu = OrderMenuResponseDto.builder()
										.menuNo(orderMenu.getMenu().getMenuNo())
										.menuName(orderMenu.getMenu().getMenuName())
										.quantity(orderMenu.getQuantity())
										.build();
								orderMenuList.add(changedOrderMenu);
							}
						);
							OrderTableDetailDto orderDetailDto = OrderTableDetailDto.builder()
									.orderNo(order.getOrderNo())
									.amount(order.getAmount())
									.orderDate(order.getOrderDate())
									.state(order.getState())
									.paymentMethod(order.getPaymentMethod())
									.updatedDate(order.getUpdatedDate())
									.orderMenuList(orderMenuList)
									.build();
							
							orderList.add(orderDetailDto);
					}
				);
			
					
			TableOrderDetailDto tableOrderDetailDto = TableOrderDetailDto.builder()
					.tableNo(nowTable.getTableNo())
					.tableCode(nowTable.getTableCode())
					.registerDate(nowTable.getRegisterDate())
					.tableNumber(nowTable.getTableNumber())
					.tableName(nowTable.getTableName())
					.state(nowTable.getState())
					.orderList(orderList)
					.build();
			
			return tableOrderDetailDto;
		} catch (Exception e) {
			LOGGER.error("Error occurred while getting table order list: ", e);
	        throw e;
		}
	};
	
	/**
	 * 테이블 로그인
	 * 테이블 오더 기기에서 테이블 등록, 이때 테이블 명과 번호도 기록함
	 * @param TableLoginDto
	 * @return
	 */
	@Override
	public void loginTable(TableLoginDto tableLoginDto) {
		LOGGER.info("[loginTable] input tableLoginDto : {}", tableLoginDto);
		
		// 해당 가맹점 정보 확인
		Store nowStore = this.storeRepository.findByStoreCode(tableLoginDto.getStoreCode())
                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
				
		// 지정한 table 데이터 불러오기
		StoreTable nowTable = this.tableRepository.findByTableCode(tableLoginDto.getTableCode())
				.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
		
		// 입력받은 데이터로 테이블 번호, 테이블 명 변경
		nowTable.setTableNumber(tableLoginDto.getTableNumber());
		nowTable.setTableName(tableLoginDto.getTableName());
		nowTable.setState(1);
		
		// 데이터 업데이트
		this.tableRepository.save(nowTable);
        
	}

	/**
	 * 테이블 관리자 로그인
	 * 테이블 내에서 관리자 모드로 진입할 때, 정보 입력 요구
	 * @param Long
	 * @return 
	 */
	@Override
	public void loginTableAdmin(TableAdminDto tableAdminDto) {
		LOGGER.info("[loginTable] input tableAdminDto : {}", tableAdminDto);
		
		// 해당 가맹점 정보 확인
		Store nowStore = this.storeRepository.findByStoreCode(tableAdminDto.getStoreCode())
                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
				
		// 지정한 table 데이터 불러오기
		StoreTable nowTable = this.tableRepository.findByTableCode(tableAdminDto.getTableCode())
				.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
	
	}

	/**
	 * 테이블 로그아웃
	 * 테이블 오더 기기 로그아웃
	 * @param Long
	 * @return
	 */
	@Override
	public void logoutTable(String tableCode) {
		LOGGER.info("[logoutTable] logout request from table: {}", tableCode);
		
		// 지정한 table 데이터 불러오기
		StoreTable targetTable = this.tableRepository.findByTableCode(tableCode)
				.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
		
		// 테이블 상태 변경
		if (targetTable.getState() == 1) {
			targetTable.setState(0);
		}
		
		// state 값 업데이트
		this.tableRepository.save(targetTable);
	}

}
