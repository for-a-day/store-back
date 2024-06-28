package com.nagane.franchise.menu.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.menu.domain.Menu;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 메뉴 관련 repository 코드
 * **/
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	/* 카테고리 번호로 메뉴 리스트 반환 */
    List<Menu> findByCategory_CategoryNo(Long categoryNo);
    
    /* 메뉴 코드로 메뉴 존재하는지 여부 조회 */
    Optional<Menu> findByMenuCode(String menuCode);
	
	/* 카테고리 번호와 상태값(1)로 해당하는 메뉴 리스트 반환 */
	List<Menu> findByCategory_CategoryNoAndState(Long categoryNo, Integer state);
	
}
