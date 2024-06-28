package com.nagane.franchise.menu.application.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.menu.application.MenuService;
import com.nagane.franchise.menu.dao.CategoryRepository;
import com.nagane.franchise.menu.dao.MenuRepository;
import com.nagane.franchise.menu.domain.Category;
import com.nagane.franchise.menu.domain.Menu;
import com.nagane.franchise.menu.dto.CategoryDto;
import com.nagane.franchise.menu.dto.MenuDto;

/**
 * @author nsr
 * @since 2024.06.28
 * 메뉴 관리 기능
 * **/
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	/**
	 * 카테고리별 메뉴 목록 조회
	 * @param Long 카테고리 No
	 * @return List<MenuDto> 조회된 Menu 목록
	 */
	public List<MenuDto> getMenuList(Long categoryNo) {
		
		List<Menu> menuList = menuRepository.findByCategory_CategoryNo(categoryNo);
		
		// Menu를 MenuDto로 변환
        List<MenuDto> menuDtoList = menuList.stream()
                .map(this::convertToDto) // 메소드 참조를 이용한 변환
                .collect(Collectors.toList());

        return menuDtoList;
	}
	
	// Menu를 MenuDto로 변환하는 메소드
    private MenuDto convertToDto(Menu menu) {
    	
    	CategoryDto category = new CategoryDto();
    	category.setCategoryName(menu.getCategory().getCategoryName());
    	category.setCategoryState(menu.getCategory().getState());
    	
    	
        MenuDto menuDto = new MenuDto();
        menuDto.setMenuNo(menu.getMenuNo());
        menuDto.setMenuName(menu.getMenuName());
        menuDto.setMenuId(menu.getMenuId());
        menuDto.setPrice(menu.getPrice());
        menuDto.setMenuImage(menu.getMenuImage());
        menuDto.setDescription(menu.getDescription());
        menuDto.setCategory(category);
        menuDto.setSupplyPrice(menu.getSupplyPrice());
        menuDto.setState(menu.getState());

        return menuDto;
    }
	
	/**
	 * 메뉴 신규 등록
	 * @param menuDto 메뉴 정보를 담고 있는 DTO 객체
	 * @return Long 등록된 메뉴의 No
	 */
	public Long createMenu(MenuDto menuDto) {
		System.out.println(menuDto.toString());
		 
		// 메뉴 코드 중복 확인
//		 Optional<Menu> existingMenu = menuRepository.findByMenuCode(menuDto.getMenuId());
		Optional<Menu> existingMenu = null;
		if (existingMenu.isPresent()) {
	        throw new IllegalArgumentException("중복된 메뉴 코드가 있습니다.");
	    }

        return saveMenu(menuDto);
	}
	
	/**
	 * 메뉴 수정
	 * @param menuDto 메뉴 정보를 담고 있는 DTO 객체
	 * @return Long 등록된 메뉴의 No
	 */
	public Long updateMenu(MenuDto menuDto) {
		System.out.println(menuDto.toString());
		
		Long savedMenuNo =saveMenu(menuDto);
		
        return savedMenuNo;
	}
	
	private Long saveMenu(MenuDto menuDto) {

		// 1. 카테고리 정보 가져오기
		 Category category = categoryRepository.findById(menuDto.getCategory().getCategoryNo())
	                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 No를 찾을 수 없습니다."));
		
		 // 2. 메뉴 엔티티 생성
		Menu menu = Menu.builder()
				.menuName(menuDto.getMenuName())
				.menuId(menuDto.getMenuId())
				.price(menuDto.getPrice())
				.menuImage(menuDto.getMenuImage())
				.description(menuDto.getDescription())
				.category(category)
				.supplyPrice(menuDto.getSupplyPrice())
				.state(menuDto.getState())
				.build();

		// 3. 메뉴 레코드 생성
		Menu saved = menuRepository.save(menu);
		
		System.out.println(saved.toString());

        return saved.getMenuNo();
	}
	
	/**
	 * 메뉴 단종
	 * @param Long 단종 상태로 변경할 메뉴의 No
	 * @return boolean 단종 상태로 수정 성공 여부
	 */
	public boolean deleteMenu(Long menuNo) {
		Optional<Menu> menuOptional = menuRepository.findById(menuNo);
		
		if(menuOptional.isPresent()) {
			Menu menu = menuOptional.get();
			menu.setState(0);
			
			menuRepository.save(menu);
			
			return true;
		}
		
		return false;
	}
	
	

	
	/**
	 * 카테고리별 판매중인 메뉴 목록
	 * @param Long 카테고리의 No, Integer 메뉴의 상태 (0 : 판매중)
	 * @return boolean 단종 상태로 수정 성공 여부
	 */
	public List<MenuDto> getAvailableMenuList(Long categoryNo) {
		// 1. 메뉴 테이블에서 카테고리 번호로 상태가 1(판매)인 레코드 리스트 return
		List<Menu> menuList = menuRepository.findByCategory_CategoryNoAndState(categoryNo, 1);
		// 2. 선택한 카테고리 메뉴 리스트 return
		// Menu를 MenuDto로 변환
        List<MenuDto> menuDtoList = menuList.stream()
                .map(this::convertToDto) // 메소드 참조를 이용한 변환
                .collect(Collectors.toList());			

        return menuDtoList;
	}
	
	/**
	 * 메뉴 상세 보기
	 * @param Long 메뉴의 No
	 * @return MenuDto 메뉴 정보
	 */
	public MenuDto getMenu(Long menuNo) {
		
		Optional<Menu> menuOptional = menuRepository.findById(menuNo);
		
		if(menuOptional.isPresent()) {
			Menu menu = menuOptional.get();
			MenuDto menuDto = convertToDto(menu);
			return menuDto;
		}

		return null;
	}
}
