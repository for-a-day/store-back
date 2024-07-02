package com.nagane.franchise.table.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nagane.franchise.order.domain.Order;
import com.nagane.franchise.store.domain.Store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.06.27 
 * StoreTable entity 코드
 **/
@Entity
@Table(name = "s_table")
@SequenceGenerator(name = "table_seq", sequenceName = "table_seq", allocationSize = 1)
@ToString(exclude = {"store"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StoreTable {

	/* 테이블 번호(pk) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_seq")
	private Long tableNo;

	/* 테이블 코드 */
	@Column(name = "table_code", nullable = false, length = 100, unique=true)
	private String tableCode;

	/* 등록날짜 */
	@Column(name = "register_date", nullable = false)
	@Builder.Default
	private LocalDateTime registerDate = LocalDateTime.now();

	/* 지점 내 테이블 번호 */
	@Column(name = "table_number")
	private Integer tableNumber;

	/* 테이블명 */
	@Column(name = "table_name", length = 100)
	private String tableName;

	/* 상태 */
	@Column(name = "state", nullable = false)
	@Builder.Default
	private Integer state = 1;

	/* order 엔티티와 OneToMany 매핑 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "table_no")
	private List<Order> orderList = new ArrayList<>();

	/* 점포번호(fk) */
	@ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
	 @JoinColumn(name = "store_no", nullable = false) 
	 private Store store;
	 
}
