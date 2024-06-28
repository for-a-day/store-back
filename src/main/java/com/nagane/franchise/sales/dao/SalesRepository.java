package com.nagane.franchise.sales.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.sales.domain.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long>{

}
