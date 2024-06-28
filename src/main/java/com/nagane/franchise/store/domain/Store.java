package com.nagane.franchise.store.domain;
/**
 * @author nsr
 * @since 2024.06.27 
 * 가맹점 엔티티
 */
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store")
@SequenceGenerator(name = "store_seq", sequenceName = "store_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Store {

	/* 지점 번호 (PK) */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq")
	private Long storeNo;
	
	/* 지점명 */
	@Column(name = "store_name", nullable = false, length = 100)
	private String storeName;

	/* 대표자 명 */
	@Column(name = "rpr_name", nullable = false, length = 50)
	private String rprName;

	/* 지점 주소 */
	@Column(name = "address", nullable = false)
	private String address;

	/* 연락처 */
	@Column(name = "contact", nullable = false, length = 20)
	private String contact;

	/* 가맹계약일 */
	@Column(name = "contract_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date contractDate;

	/* 만료계약일 */
	@Column(name = "expiration_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	/* 경고 횟수 */
	@Column(name = "warning_count", nullable = false)
	@Builder.Default
	private Integer warningCount = 0;

	/* 점포 코드 */
	@Column(name = "store_code", nullable = false, length = 20, unique=true)
	private String storeCode;

	/* 지역 코드 */
	@Column(name = "area_code", nullable = false, length = 20)
	private String areaCode;

	/* 상태 0=폐점, 1=영업 */
	@Column(name = "state", nullable = false)
	@Builder.Default
	private Integer state = 1;
	
	@PrePersist
	protected void onCreate() {
		contractDate = new Date();
	}
}