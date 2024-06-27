package com.nagane.franchise.table.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.order.domain.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author ljy
 * @since 2024.06.27
 * StoreTable entity 코드
 **/
@Entity
@Table(name = "s_table")
@SequenceGenerator(name = "table_seq", sequenceName = "table_seq", allocationSize = 1)
@Data
public class StoreTable {

	/* 테이블 번호(pk) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_seq")
	private Long tableNo;

	/* 테이블 코드 */
	@Column(name = "table_code", nullable = false, length = 100)
	private String tableCode;

	/* 등록날짜 */
	@Column(name = "register_date", nullable = false)
	private LocalDateTime registerDate;

	/* 지점 내 테이블 번호 */
	@Column(name = "table_number")
	private Integer tableNumber;

	/* 테이블명 */
	@Column(name = "table_name", length = 100)
	private String tableName;

	/* 상태 */
	@Column(name = "menu_name", nullable = false)
	private Integer state = 1;

	/* order 엔티티와 OneToMany 매핑 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "table_no")
	private List<Order> orderList = new ArrayList<>();
	
	/*
	 * 점포번호(fk)
	 * 
	 * @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "store_no", insertable = false, updatable = false) private
	 * Store store;
	 */
}
