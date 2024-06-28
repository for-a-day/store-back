package com.nagane.franchise.menu.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.nagane.franchise.menu.domain.Category;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 카테고리 관련 repository 코드
 * **/
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
