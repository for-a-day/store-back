package com.nagane.franchise.sales.domain;
/**
 * @author nsr
 * @since 2024.06.27 
 * 매출 엔티티
 */
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
import lombok.Data;

@Entity
@Table(name = "sales")
@SequenceGenerator(name = "sales_seq", sequenceName = "sales_seq", allocationSize = 1)
@Data
public class Sales {

	/* 매출 번호 (PK) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_seq")
	private Long salesNo;
	

	/* 월 매출액 */
	@Column(name = "monthly_sales", nullable = false)
	private Long monthlySales;
	
	/* 연도 */
	@Column(name = "year", nullable = false, precision = 4, scale = 0)
	private Integer year;
	
	/* 월 */
	@Column(name = "month", nullable = false, precision = 2, scale = 0)
	private Integer month;
	
	/* 점포 */
	@ManyToOne
    @JoinColumn(name = "store_no")
    private Store store;
	
}
