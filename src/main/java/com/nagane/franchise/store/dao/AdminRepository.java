package com.nagane.franchise.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.store.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
