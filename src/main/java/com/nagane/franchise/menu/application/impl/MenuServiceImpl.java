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
import com.nagane.franchise.menu.dto.menu.MenuForToDto;
import com.nagane.franchise.menu.dto.menu.MenuListForToDto;
import com.nagane.franchise.menu.dto.menu.MenuCreateDto;
import com.nagane.franchise.menu.dto.menu.MenuListDto;
import com.nagane.franchise.menu.dto.menu.MenuReadDto;
import com.nagane.franchise.menu.dto.menu.MenuUpdateDto;

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
	
	// Admin
	/**
	 * 메뉴 신규 등록
	 * @param MenuCreateDto 메뉴 정보를 담고 있는 DTO 객체
	 * @return Long 등록된 메뉴의 No
	 */
	@Override
	public Long createMenu(MenuCreateDto menuCreateDto) {
		System.out.println(menuCreateDto.toString());
		 
		// 메뉴 코드 중복 확인
		 Optional<Menu> existingMenu = menuRepository.findByMenuId(menuCreateDto.getMenuId());
		if (existingMenu.isPresent()) {
	        throw new IllegalArgumentException("중복된 메뉴 코드가 있습니다.");
	    }

		// 1. 카테고리 정보 가져오기
		 Category category = categoryRepository.findById(menuCreateDto.getCategoryNo())
	                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 No를 찾을 수 없습니다."));
		
		 // 2. 메뉴 엔티티 생성
		Menu menu = Menu.builder()
				.menuName(menuCreateDto.getMenuName())
				.menuId(menuCreateDto.getMenuId())
				.price(menuCreateDto.getPrice())
				.menuImage(menuCreateDto.getMenuImage())
				.description(menuCreateDto.getDescription())
				.category(category)
				.supplyPrice(menuCreateDto.getSupplyPrice())
				.state(1)
				.build();

		// 3. 메뉴 레코드 생성
		Menu saved = menuRepository.save(menu);
		
		System.out.println(saved.toString());

        return saved.getMenuNo();
	}

	/**
	 * 카테고리별 메뉴 목록 조회
	 * @param Long 카테고리 No
	 * @return List<MenuListDto> 조회된 Menu 목록
	 */
	@Override
	public List<MenuListDto> getMenuList(Long categoryNo) {

	    List<Menu> menuList = menuRepository.findByCategory_CategoryNo(categoryNo);

	    // Menu를 MenuDto로 변환
	    List<MenuListDto> menuDtoList = menuList.stream()
	            .map(menu -> {
	                MenuListDto menuDto = new MenuListDto();
	                menuDto.setMenuNo(menu.getMenuNo());
	                menuDto.setMenuName(menu.getMenuName());
	                menuDto.setMenuImage(menu.getMenuImage());
	                return menuDto;
	            })
	            .collect(Collectors.toList());

	    return menuDtoList;
	}

    /**
     * 메뉴 상세 보기
     * @param Long 메뉴의 No
     * @return MenuReadDto 메뉴 정보
     */
    @Override
    public MenuReadDto getMenu(Long menuNo) {
        
        Optional<Menu> menuOptional = menuRepository.findById(menuNo);
        
        if(menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            
            MenuReadDto menuDto = new MenuReadDto();
            menuDto.setMenuNo(menu.getMenuNo());
            menuDto.setMenuName(menu.getMenuName());
            menuDto.setMenuId(menu.getMenuId());
            menuDto.setPrice(menu.getPrice());
            menuDto.setMenuImage(menu.getMenuImage());
            menuDto.setDescription(menu.getDescription());
            menuDto.setSupplyPrice(menu.getSupplyPrice());
            menuDto.setState(menu.getState());
            
            return menuDto;
        }

        return null;
    }

	/**
	 * 메뉴 수정
	 * @param MenuUpdateDto 메뉴 정보를 담고 있는 DTO 객체
	 * @return Long 등록된 메뉴의 No
	 */
	@Override
	public Long updateMenu(MenuUpdateDto menuUpdateDto) {
		System.out.println(menuUpdateDto.toString());
			 Category category = categoryRepository.findById(menuUpdateDto.getCategoryNo())
		                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다."));
			
			 
		// 1. 메뉴를 다시 판매중으로 수정 한 경우, 해당 카테고리도 판매중으로 수정
		if(menuUpdateDto.getState() == 1) {
			 category.setState(1);
		}
		 
		 
		// 2. 기존 메뉴 정보 가져오기
		Menu menu = menuRepository.findById(menuUpdateDto.getMenuNo())
		.orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));


		// 3. 메뉴 정보 업데이트
		menu.setMenuName(menuUpdateDto.getMenuName());
		menu.setPrice(menuUpdateDto.getPrice());
		menu.setMenuImage(menuUpdateDto.getMenuImage());
		menu.setDescription(menuUpdateDto.getDescription());
		menu.setCategory(category);
		menu.setSupplyPrice(menuUpdateDto.getSupplyPrice());
		menu.setState(menuUpdateDto.getState());

		// 4. 메뉴 레코드 업데이트
		Menu saved = menuRepository.save(menu);
		
		System.out.println(saved.toString());

       return saved.getMenuNo();
	}
	
//	/**
//	 * 메뉴 단종
//	 * @param Long 단종 상태로 변경할 메뉴의 No
//	 * @return boolean 단종 상태로 수정 성공 여부
//	 */
//	@Override
//	public boolean deleteMenu(Long menuNo) {
//		Optional<Menu> menuOptional = menuRepository.findById(menuNo);
//		
//		if(menuOptional.isPresent()) {
//			Menu menu = menuOptional.get();
//			menu.setState(0);
//			
//			menuRepository.save(menu);
//			
//			return true;
//		}
//		
//		return false;
//	}
//	
	
	/**
	 * 메뉴 영구 삭제
	 * @param Long 단종 상태로 변경할 메뉴의 No
	 * @return boolean 단종 상태로 수정 성공 여부
	 */
	@Override
	public boolean deleteMenu(Long menuNo) {
		
			menuRepository.deleteById(menuNo);
			
			return true;
	}

	//  TO
	/**
	 * 카테고리별 판매중인 메뉴 목록 조회
	 * @param Long 카테고리의 No
	 * @return List<MenuListForToDto> 
	 */
	@Override
	public List<MenuListForToDto> getAvailableMenuList(Long categoryNo) {
		// 1. 메뉴 테이블에서 카테고리 번호로 상태가 1(판매)인 레코드 리스트 return
		List<Menu> menuList = menuRepository.findByCategory_CategoryNoAndState(categoryNo, 1);
		// 2. 선택한 카테고리 메뉴 리스트 return
		// Menu를 MenuDto로 변환
        List<MenuListForToDto> menuDtoList = menuList.stream()
                .map(menu ->{

                	MenuListForToDto menuDto = new MenuListForToDto();
                    menuDto.setMenuNo(menu.getMenuNo());
                    menuDto.setMenuName(menu.getMenuName());
                    menuDto.setPrice(menu.getPrice());

                    return menuDto;
                }) // 메소드 참조를 이용한 변환
                .collect(Collectors.toList());			

        return menuDtoList;
	}
	
    /**
     * TO용 판매가능한 메뉴 상세 보기
     * @param Long 메뉴의 No
     * @return MenuForToDto 메뉴 정보
     */
    @Override
    public MenuForToDto getAvailableMenu(Long menuNo) {
        
        Optional<Menu> menuOptional = menuRepository.findById(menuNo);
        
        if(menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            
            MenuForToDto menuDto = new MenuForToDto();
            menuDto.setMenuNo(menu.getMenuNo());
            menuDto.setMenuName(menu.getMenuName());
            menuDto.setPrice(menu.getPrice());
            menuDto.setMenuImage(menu.getMenuImage());
            menuDto.setDescription(menu.getDescription());
            
            return menuDto;
        }

        return null;
    }

}
