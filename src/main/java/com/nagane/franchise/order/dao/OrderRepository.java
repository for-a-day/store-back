package com.nagane.franchise.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
