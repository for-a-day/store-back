package com.nagane.franchise.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.order.domain.OrderMenu;

public interface OrderMenuRepository  extends JpaRepository<OrderMenu, Long>{

}
