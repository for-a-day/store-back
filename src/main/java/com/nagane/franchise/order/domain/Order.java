package com.nagane.franchise.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.store.domain.Store;
import com.nagane.franchise.table.domain.StoreTable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

/**
 * @author ljy
 * @since 2024.06.27
 * Order entity 코드
 **/
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1)
@Data
@Builder
public class Order {
	
	/* 주문(결제) 번호(pk) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
	private Long orderNo;
	
	/* 금액 */
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	/* 주문 일시 */
	@Column(name = "order_date", nullable = false)
	@Builder.Default
	private LocalDateTime orderDate = LocalDateTime.now();
	
	/* 상태 */
	@Column(name = "state", nullable = false)
	private Integer state = 1;
	
	/* 결제 방법 */
	@Column(name = "payment_method", nullable = false, length=100)
	private String paymentMethod;
	
	/* 업데이트 날짜 */
	@Column(name = "updated_date", nullable = false)
	private LocalDateTime updatedDate;
	
	/* 점포 번호(fk) */
	 @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
     @JoinColumn(name = "store_no", nullable = false)
	 private Store store;
	
	/* 테이블 번호(fk) */
	@ManyToOne(targetEntity = StoreTable.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "table_no", nullable = false)
	private StoreTable table;
	
	/* order_menu 엔티티와 OneToMany 매핑 */
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderMenu> orderMenuList = new ArrayList<>();
	
	/* 레코드 신규 생성 시 orderDate, updatedDate 값 자동 배정 */
	@PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
	// 업데이트 자동 처리
    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
