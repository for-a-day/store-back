package com.nagane.franchise.menu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author ljy
 * @since 2024.06.27
 * Menu entity 코드
 * **/
@Entity
@Table(name = "menu")
@SequenceGenerator(name = "menu_seq", sequenceName = "menu_seq", allocationSize = 1)
@Data
public class Menu {
	
	/* 메뉴 번호(pk) */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
	private Long menuNo;
	
	/* 메뉴명 */
	@Column(name="menu_name", nullable = false, length=100)
	private String menuName;
	
	/* 메뉴 코드 */
	@Column(name="menu_id", nullable = false, length=100)
	private String menuId;
	
	/* 판매가격 */
	@Column(name="price", nullable = false)
	private Integer price;
	
	/* 이미지 */
	@Column(name="menu_img", length=255)
	private String menuImage;
	
	/* 설명 */
	@Column(name="description", length=500)
	private String description;
	
	/* 카테고리 번호(fk) */
	@ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no", insertable = false, updatable = false)
	private Long categoryNo;
	
	/* 재료 공급가 */
	@Column(name="supply_price", nullable = false)
	private Integer supplyPrice;
	
	/* 상태(0=단종, 1=판매) */
	@Column(name="state", nullable=false)
	private Integer state = 1;
	
	/*
	 * @PrePersist protected void onCreate() { if (this.state == null) { this.state
	 * = 1; // 기본 값 1로 설정 } }
	 */
}
