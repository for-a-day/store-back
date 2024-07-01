package com.nagane.franchise.menu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
 * Category entity 코드
 * **/
@Entity
@Table(name = "category")
@SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
	/* 카테고리 번호(pk) */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
	private Long categoryNo;
	
	/* 카테고리명 */
	@Column(name="category_name", nullable = false, length=100)
	private String categoryName;
	
	/* 상태(0=단종, 1=판매) */
	@Column(name="state", nullable=false)
	private Integer state = 1;
	
	/* menu 엔티티와 OneToMany 매핑 */
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "category_no")
//	private List<Menu> menuList = new ArrayList<>();
	
	/*
	 * @PrePersist protected void onCreate() { if (this.state == null) { this.state
	 * = 1; // 기본 값 1로 설정 } }
	 */
}
