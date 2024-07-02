package com.nagane.franchise.order.application.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.menu.dao.MenuRepository;
import com.nagane.franchise.menu.domain.Menu;
import com.nagane.franchise.order.application.OrderService;
import com.nagane.franchise.order.dao.OrderMenuRepository;
import com.nagane.franchise.order.dao.OrderRepository;
import com.nagane.franchise.order.domain.Order;
import com.nagane.franchise.order.domain.OrderMenu;
import com.nagane.franchise.order.dto.order.OrderChangeStateDto;
import com.nagane.franchise.order.dto.order.OrderCreateDto;
import com.nagane.franchise.order.dto.order.OrderDetailDto;
import com.nagane.franchise.order.dto.order.OrderResponseDto;
import com.nagane.franchise.order.dto.order.PaymentResponseDto;
import com.nagane.franchise.order.dto.ordermenu.OrderMenuResponseDto;
import com.nagane.franchise.stoke.dao.StockRepository;
import com.nagane.franchise.stoke.domain.Stock;
import com.nagane.franchise.store.dao.StoreRepository;
import com.nagane.franchise.store.domain.Store;
import com.nagane.franchise.table.dao.StoreTableRepository;
import com.nagane.franchise.table.domain.StoreTable;
import com.nagane.franchise.util.exceptions.InsufficientStockException;

/**
 * @author ljy
 * @since 2024.07.01
 * Order Service 코드
 * 주문 관련 Service impl
 * **/
@Service
public class OrderServiceImpl implements OrderService {
	
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	// 필요 레포 연결, 의존성 주입(di)
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	StoreTableRepository tableRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderMenuRepository orderMenuRepository;
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	StockRepository stockRepository;
	
		
	/**
	 * 현재 주문 목록 조회
	 * @param Long storeNo
	 * @return List<OrderResponseDto>
	 */
	@Override
	public List<OrderResponseDto> getOrderList(Long storeNo) {
	    LOGGER.info("[getOrderList] input storeNo : {}", storeNo);
	    try {
	        // 해당 가맹점 받아오기
	        Store nowStore = this.storeRepository.findById(storeNo)
	                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
	        
	        // 모든 order 목록 조회
	        List<Order> orderList = this.orderRepository.findByStoreNoAndState(nowStore.getStoreNo());
	        
	        // return할 changedOrderList 미리 생성
	        List<OrderResponseDto> changedOrderList = new ArrayList<>();
	        
	        // 각 order 엔티티 객체 OrderResponseDto로 변경해서 changedOrderList에 추가
	        orderList.forEach(order -> {
	        	
	        	// 각 orderMenu 항목 먼저 변환(메뉴번호, 메뉴명, 주문한 개수)
	        	List<OrderMenuResponseDto> orderMenuResponseList = new ArrayList<>();
	        	
	        	order.getOrderMenuList().forEach(orderMenu -> {
	        		OrderMenuResponseDto orderMenuResponseDto = OrderMenuResponseDto.builder()
	        				.menuNo(orderMenu.getMenu().getMenuNo())
	        				.menuName(orderMenu.getMenu().getMenuName())
	        				.quantity(orderMenu.getQuantity())
	        				.build();
	        		
	        		orderMenuResponseList.add(orderMenuResponseDto);
	        	});
	        	
	        	
	            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
	            		.orderNo(order.getOrderNo())
	            		.amount(order.getAmount())
	            		.tableNo(order.getTable().getTableNo())
	            		.tableNumber(order.getTable().getTableNumber())
	            		.orderMenuList(orderMenuResponseList)
	                    .build();
	            
	            changedOrderList.add(orderResponseDto);
	        });
	        
	        return changedOrderList;
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while getting order list: ", e);
	        throw e;
	    }
	}

	/**
	 * 선택한 주문 상세 정보 조회
	 * @param Long orderNo
	 * @return OrderDetailDto
	 */
	@Override
	public OrderDetailDto getOrder(Long orderNo) {
	    LOGGER.info("[getOrder] input orderNo : {}", orderNo);
	    try {
	        // 해당 order 단일 조회
	        Order order = this.orderRepository.findById(orderNo)
	        		.orElseThrow(() -> new NoSuchElementException("해당 주문을 찾을 수 없습니다."));
	        
	        // 각 orderMenu 항목 먼저 변환(메뉴번호, 메뉴명, 주문한 개수)
        	List<OrderMenuResponseDto> orderMenuResponseList = new ArrayList<>();
	        
        	// 주문 상세 정보 OrderMenuResponseDto 형태로 변환해서 orderMenuResponseList에 추가
	        order.getOrderMenuList().forEach(orderMenu -> {
        		OrderMenuResponseDto orderMenuResponseDto = OrderMenuResponseDto.builder()
        				.menuNo(orderMenu.getMenu().getMenuNo())
        				.menuName(orderMenu.getMenu().getMenuName())
        				.quantity(orderMenu.getQuantity())
        				.build();
        		
        		orderMenuResponseList.add(orderMenuResponseDto);
        	});
	        
	        // order 엔티티 orderDetailDto 형태로 변환해서 list에 저장
	        OrderDetailDto orderDetailDto = OrderDetailDto.builder()
            		.orderNo(order.getOrderNo())
            		.amount(order.getAmount())
            		.orderDate(order.getOrderDate())
            		.state(order.getState())
            		.paymentMethod(order.getPaymentMethod())
            		.updatedDate(order.getUpdatedDate())
            		.tableNo(order.getTable().getTableNo())
            		.tableNumber(order.getTable().getTableNumber())
            		.orderMenuList(orderMenuResponseList)
                    .build();
	        
	        return orderDetailDto;
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while getting order: ", e);
	        throw e;
	    }
	}

	/**
	 * 결제 목록 조회(영수증)
	 * @param Long storeNo
	 * @return List<PaymentResponseDto>
	 */
	@Override
	public List<PaymentResponseDto> getPaymentList(Long storeNo) {
		LOGGER.info("[getOrderList] input storeNo : {}", storeNo);
	    try {
	        // 해당 가맹점 받아오기
	        Store nowStore = this.storeRepository.findById(storeNo)
	                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
	        
	        // 오늘 날짜를 가져옴
	        LocalDate today = LocalDate.now();
	        
	        // 오늘의 시작 시간과 끝 시간 계산
	        LocalDateTime startDate = today.atStartOfDay(); // 00:00:00
	        LocalDateTime endDate = today.atTime(LocalTime.MAX); // 23:59:59
	        
	        // 오늘 들어온 모든 목록 조회
	        List<Order> paymentList = this.orderRepository.findByStore_StoreNoAndOrderDateBetween(
	        		nowStore.getStoreNo(), 
	        		startDate,
	        		endDate);
	        
	        // return할 changedOrderList 미리 생성
	        List<PaymentResponseDto> changedPaymentList = new ArrayList<>();
	        
	        // 각 order 엔티티 객체 OrderResponseDto로 변경해서 changedOrderList에 추가
	        paymentList.forEach(payment -> {
	        	PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
	            		.orderNo(payment.getOrderNo())
	            		.amount(payment.getAmount())
	            		.paymentMethod(payment.getPaymentMethod())
	            		.orderDate(payment.getOrderDate())
	            		.updatedDate(payment.getUpdatedDate())
	            		.state(payment.getState())
	            		.tableNo(payment.getTable().getTableNo())
	            		.tableNumber(payment.getTable().getTableNumber())
	                    .build();
	            
	        	changedPaymentList.add(paymentResponseDto);
	        });
	        
	        return changedPaymentList;
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while getting payment list: ", e);
	        throw e;
	    }
	}

	/**
	 * 주문 상태 변경
	 * @param OrderChangeStateDto orderChangeStateDto
	 * @return Map<String, Object>>
	 */
	@Override
	public void changeOrderState(OrderChangeStateDto orderChangeStateDto) {
		LOGGER.info("[changeOrderState] input orderChangeStateDto : {}", orderChangeStateDto);
		try {
			// 주문 정보 찾기
			Order nowOrder = this.orderRepository.findById(orderChangeStateDto.getOrderNo())
					.orElseThrow(() -> new NoSuchElementException("주문 정보를 찾을 수 없습니다."));
			
			// 케이스따라 정보 수정
			switch (orderChangeStateDto.getOrderStateInstr()) {
				// 음식 나갔을 때
				case OUT:
					nowOrder.setState(1);
					break;
				// 손님이 가게 밖으로 나갔을 때(식사 끝)
				case CLEAR:
					nowOrder.setState(2);
					break;
				// 환불 됐을 때
				case REFUND:
					nowOrder.setState(99);
			}
			
			// 수정된 정보 db에 저장
			this.orderRepository.save(nowOrder);
			
		} catch (Exception e) {
	        LOGGER.error("Error occurred while changing payment State : ", e);
	        throw e;
	    }
	}
	
	/**
	 * 단일 결제 환불
	 * @param Long orderNo
	 * @return void
	 */
	@Override
	public void refundPayment(Long orderNo) {
		LOGGER.info("[refundPayment] input orderNo : {}", orderNo);
		try {
			// 주문 정보 찾기
			Order nowOrder = this.orderRepository.findById(orderNo)
					.orElseThrow(() -> new NoSuchElementException("주문 정보를 찾을 수 없습니다."));
			
			// 환불로 수정(code: 99)
			if (nowOrder.getState() == 1) {
				nowOrder.setState(99);
			}
			
			// 수정된 정보 db에 저장
			this.orderRepository.save(nowOrder);
			
		} catch (Exception e) {
	        LOGGER.error("Error occurred while payment refund : ", e);
	        throw e;
	    }
	}

	/**
	 * 해당 테이블 주문 내역 조회
	 * @param String tableCode
	 * @return List<OrderResponseDto>
	 */
	@Override
	public List<OrderDetailDto> getTableOrder(String tableCode) {
		LOGGER.info("[getTableOrder] input tableCode : {}", tableCode);
		// 지정한 table 데이터 불러오기
		StoreTable nowTable = this.tableRepository.findByTableCode(tableCode)
				.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
				
	    try {
	        // 해당 order 리스트 조회
	        List<Order> orderList = this.orderRepository.findByTableCodeAndState(nowTable.getTableCode());
	        
	        // LOGGER.info("[getTableOrder] get orderList : {}", orderList);
	        
	        // dto로 변환한 값 저장할 changedOrderList 생성
	        List<OrderDetailDto> changedOrderList = new ArrayList<>();
	        
	        orderList.forEach(order -> {
	        	// 각 orderMenu 항목 먼저 변환(메뉴번호, 메뉴명, 주문한 개수)
	        	List<OrderMenuResponseDto> orderMenuResponseList = new ArrayList<>();
		        
	        	// 주문 상세 정보 OrderMenuResponseDto 형태로 변환해서 orderMenuResponseList에 추가
		        order.getOrderMenuList().forEach(orderMenu -> {
	        		OrderMenuResponseDto orderMenuResponseDto = OrderMenuResponseDto.builder()
	        				.menuNo(orderMenu.getMenu().getMenuNo())
	        				.menuName(orderMenu.getMenu().getMenuName())
	        				.quantity(orderMenu.getQuantity())
	        				.build();
	        		
	        		orderMenuResponseList.add(orderMenuResponseDto);
	        		// LOGGER.info("[getTableOrder] make orderMenuResponseDto : {}", orderMenuResponseDto);
	        	});
		        
		        
		        
		        // order 엔티티 orderDetailDto 형태로 변환해서 list에 저장
		        OrderDetailDto orderDetailDto = OrderDetailDto.builder()
	            		.orderNo(order.getOrderNo())
	            		.amount(order.getAmount())
	            		.orderDate(order.getOrderDate())
	            		.state(order.getState())
	            		.paymentMethod(order.getPaymentMethod())
	            		.updatedDate(order.getUpdatedDate())
	            		.tableNo(order.getTable().getTableNo())
	            		.tableNumber(order.getTable().getTableNumber())
	            		.orderMenuList(orderMenuResponseList)
	                    .build();
		        
		        changedOrderList.add(orderDetailDto);
		        
		        // LOGGER.info("[getTableOrder] make orderDetailDto : {}", orderDetailDto);
	        });
	        
	        return changedOrderList;
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while getting order: ", e);
	        throw e;
	    }
	}

	/**
	 * 주문 신규 등록
	 * @param String tableCode
	 * @return void
	 */
	@Override
	public OrderDetailDto createOrder(OrderCreateDto orderCreateDto) {
		LOGGER.info("[createOrder] input orderCreateDto : {}", orderCreateDto);
		try {
			// 해당 가맹점 받아오기
	        Store nowStore = this.storeRepository.findByStoreCode(orderCreateDto.getStoreCode())
	                .orElseThrow(() -> new NoSuchElementException("지점을 찾을 수 없습니다."));
			
	        // 해당 table 단일 조회
	        StoreTable nowTable = this.tableRepository.findByTableCode(orderCreateDto.getTableCode())
	        		.orElseThrow(() -> new NoSuchElementException("해당 테이블을 찾을 수 없습니다."));
	        
	        // 재고 상태 변경 위한 리스트, 만약 재고 부족할 시 바로 return
	        List<Stock> changeStockList = new ArrayList<>();
	        
	        // 주문 상세 정보 저장
        	orderCreateDto.getOrderMenuList().forEach(orderMenu -> {
        		// 해당 menu 단일 조회
    	        Stock nowStock = this.stockRepository.findByMenu_MenuNo(orderMenu.getMenuNo())
    	        		.orElseThrow(() -> new NoSuchElementException("해당 재고를 찾을 수 없습니다."));
        		
    	        if (nowStock.getQuantity() >= orderMenu.getQuantity()) {
                    nowStock.setQuantity(nowStock.getQuantity() - orderMenu.getQuantity());
                    changeStockList.add(nowStock);
                } else {
                    throw new InsufficientStockException("재고가 부족합니다. 메뉴 번호: " + orderMenu.getMenuNo());
                }
    	        
        	});
        	
	        // 주문 엔티티 신규 생성
	        Order createOrder = Order.builder()
	        		.amount(orderCreateDto.getAmount())
	        		.paymentMethod(orderCreateDto.getPaymentMethod())
	        		.store(nowStore)
	        		.table(nowTable)
	        		.build();
	        
	        LOGGER.info("[createOrder] create createOrder : {}", createOrder);
	        
	        // 주문 정보 db에 저장
	        Order savedOrder = this.orderRepository.save(createOrder);
	        
	        // 주문 상세 정보 저장
        	orderCreateDto.getOrderMenuList().forEach(orderMenu -> {
        		// 해당 menu 단일 조회
    	        Menu nowMenu = this.menuRepository.findById(orderMenu.getMenuNo())
    	        		.orElseThrow(() -> new NoSuchElementException("해당 메뉴를 찾을 수 없습니다."));
        		
        		OrderMenu createOrderMenu = OrderMenu.builder()
        				.menu(nowMenu)
        				.order(savedOrder)
        				.quantity(orderMenu.getQuantity())
        				.build();
        		
        		this.orderMenuRepository.save(createOrderMenu);
        	});
	        
	        
        	// 모두 다 통과하면, 재고 변경 사항 반영
        	changeStockList.forEach(changeStock -> {
        		this.stockRepository.save(changeStock);
        	});
        	
        	OrderDetailDto nowOrder = OrderDetailDto.builder()
        			.orderNo(savedOrder.getOrderNo())
            		.amount(savedOrder.getAmount())
            		.orderDate(savedOrder.getOrderDate())
            		.state(savedOrder.getState())
            		.paymentMethod(savedOrder.getPaymentMethod())
            		.updatedDate(savedOrder.getUpdatedDate())
            		.tableNo(savedOrder.getTable().getTableNo())
            		.tableNumber(savedOrder.getTable().getTableNumber())
            		.orderMenuList(orderCreateDto.getOrderMenuList())
                    .build();
        	
        	return nowOrder;
	       
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while saving order: ", e);
	        throw e;
	    }
	}


}
