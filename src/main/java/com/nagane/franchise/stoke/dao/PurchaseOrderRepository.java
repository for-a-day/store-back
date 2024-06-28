package com.nagane.franchise.stoke.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.stoke.domain.PurchaseOrder;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Long>{

}
