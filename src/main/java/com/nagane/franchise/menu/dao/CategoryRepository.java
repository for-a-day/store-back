package com.nagane.franchise.menu.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagane.franchise.menu.domain.Category;
import com.nagane.franchise.menu.domain.Menu;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 카테고리 관련 repository 코드
 * **/
public interface CategoryRepository extends JpaRepository<Category, Long>{
	/* 상태값(1:판매중)으로 해당하는 메뉴 리스트 반환 */
	List<Category> findByState(Integer state);
	
}
