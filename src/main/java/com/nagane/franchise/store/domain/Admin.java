package com.nagane.franchise.store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author ljy
 * @since 2024.06.27
 * Admin entity 코드
 * **/
@Entity
@Table(name = "admin")
@SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
@Data
public class Admin {
	
	/* 관리자 번호(pk) */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
    private Long adminNo;
	
	/* 관리자 아이디 */
	@Column(name = "admin_id", nullable = false, length = 50, unique=true)
    private String adminId;
	
	/* 관리자 비밀번호 */
	@Column(name = "admin_password", nullable = false)
    private String adminPassword;
}
