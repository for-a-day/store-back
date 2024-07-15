package com.nagane.franchise.menu.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagane.franchise.menu.domain.Menu;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 메뉴 관련 repository 코드
 * **/
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	/* 카테고리 번호로 메뉴 리스트 반환 */
    List<Menu> findByCategory_CategoryNo(Long categoryNo);
    

    /* 카테고리 번호로 메뉴 개수 반환 */
    @Query("SELECT COUNT(m) FROM Menu m WHERE m.category.categoryNo = :categoryNo")
    Long countByCategory_CategoryNo(Long categoryNo);
    
    
    /* 메뉴 코드로 메뉴 존재하는지 여부 조회 */
    Optional<Menu> findByMenuId(String menuId);
	
	/* 카테고리 번호와 상태값(판매중:1)으로 해당하는 메뉴 리스트 반환 */
	List<Menu> findByCategory_CategoryNoAndState(Long categoryNo, Integer state);
	
	/* 상태값(판매중:1)으로 해당하는 메뉴 리스트 반환 */
	List<Menu> findByState(Integer state);
	
}
