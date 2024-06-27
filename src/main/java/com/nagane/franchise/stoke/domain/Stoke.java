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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * @author nsr
 * @since 2024.06.27 
 * 지점별 재고 엔티티
 */
@Entity
@Table(name = "stoke")
@SequenceGenerator(name = "stoke_seq", sequenceName = "stoke_seq", allocationSize = 1)
@Data
public class Stoke {


	/* 재고 번호 (PK) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stoke_seq")
	private Long stokeNo;
	
	
	/* 재고량 */
	@Column(name = "quantity", nullable = false)
	private Integer quantity = 1;

	
	/* 최근 입고 날짜 */
	@Column(name = "last_stock_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date lastStockDate;
	

	/* 점포 */
	@ManyToOne
    @JoinColumn(name = "store_no")
    private Store store;


	/* 점포 */
//	@ManyToOne
//    @JoinColumn(name = "menu_no")
//    private Menu menu;
}
