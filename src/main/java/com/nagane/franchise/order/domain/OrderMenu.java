package com.nagane.franchise.order.domain;

import com.nagane.franchise.menu.domain.Menu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * OrderMenu entity 코드
 * 
 * @autor ljy
 * @since 2024.06.27
 **/
@Entity
@Table(name = "order_menu")
@SequenceGenerator(name = "order_menu_seq", sequenceName = "order_menu_seq", allocationSize = 1)
@ToString(exclude = {"menu", "order"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderMenu {
	
	/* 주문 항목 번호(pk) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_menu_seq")
	private Long orderItemNo;
	
	/* 수량 */
	@Column(name = "quantity", nullable = false)
	@Builder.Default
	private Integer quantity = 1;
	
	/* 메뉴 번호(fk) */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_no", nullable = false)
	private Menu menu;
	
	/* 결제 번호(fk) */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_no", nullable = false)
	private Order order;
	
}
