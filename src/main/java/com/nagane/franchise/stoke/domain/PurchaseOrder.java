package com.nagane.franchise.stoke.domain;

import java.util.Date;

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
 * 발주 엔티티
 */
@Entity
@Table(name = "purchase_order")
@SequenceGenerator(name = "p_order_seq", sequenceName = "p_order_seq", allocationSize = 1)
@Data
public class PurchaseOrder {


	/* 발주 번호 (PK) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p_order_seq")
	private Long pOrderNo;
	

	/* 수량 */
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	

	/* 발주날짜 */
	@Column(name = "order_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	

	/* 발주상태 */
	@Column(name = "state", nullable = false)
	private Integer state;

	/* 가격 */
	@Column(name = "price", nullable = false)
	private Integer price;


	/* 재고 */
	@ManyToOne
    @JoinColumn(name = "stoke_no")
    private Stoke stoke;
}
