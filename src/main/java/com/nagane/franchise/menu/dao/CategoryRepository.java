package com.nagane.franchise.menu.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.nagane.franchise.menu.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
