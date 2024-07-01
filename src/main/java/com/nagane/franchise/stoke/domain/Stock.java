package com.nagane.franchise.stoke.domain;

import java.util.Date;

import com.nagane.franchise.menu.domain.Menu;
import com.nagane.franchise.store.domain.Store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nsr
 * @since 2024.06.27 
 * 지점별 재고 엔티티
 */
@Entity
@Table(name = "stock")
@SequenceGenerator(name = "stock_seq", sequenceName = "stock_seq", allocationSize = 1)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {


	/* 재고 번호 (PK) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
	private Long stockNo;
	
	
	/* 재고량 */
	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	
	/* 최근 입고 날짜 */
	@Column(name = "last_stock_date")
	@Temporal(TemporalType.DATE)
	private Date lastStockDate;
	

	/* 점포 (FK) */
	@ManyToOne
    @JoinColumn(name = "store_no")
    private Store store;


	/* 메뉴 (FK) */
	@ManyToOne
    @JoinColumn(name = "menu_no")
    private Menu menu;
	

	@PrePersist
	protected void onCreate() {
		quantity = 0;
	}
}
