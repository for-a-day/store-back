package com.nagane.franchise.menu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.menu.application.CategoryService;
import com.nagane.franchise.menu.application.MenuService;
import com.nagane.franchise.menu.dto.category.CategoryListForToDto;
import com.nagane.franchise.menu.dto.category.CategoryCreateDto;
import com.nagane.franchise.menu.dto.category.CategoryListDto;
import com.nagane.franchise.menu.dto.category.CategoryUpdateDto;
import com.nagane.franchise.menu.dto.menu.MenuForToDto;
import com.nagane.franchise.menu.dto.menu.MenuListForToDto;
import com.nagane.franchise.menu.dto.menu.MenuCreateDto;
import com.nagane.franchise.menu.dto.menu.MenuListDto;
import com.nagane.franchise.menu.dto.menu.MenuReadDto;
import com.nagane.franchise.menu.dto.menu.MenuUpdateDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author nsr
 * @since 2024.06.29
 * Menu controller 코드
 * 메뉴 및 카테고리 관련 controller
 * **/
@Tag(name= "메뉴 관리 API")
@RestController
@RequiredArgsConstructor
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService categoryService;
	

	/**
	 * 카테고리 등록
	 * @param categoryCreateDto
	 * @return
	 */
	@PostMapping("/admin/category")
	public ResponseEntity<String> createCategory(
			@RequestBody CategoryCreateDto categoryCreateDto) {

		// 신규 관리자 계정 생성
		try {
			this.categoryService.createCategory(categoryCreateDto);
			return new ResponseEntity<>("성공적으로 등록되었습니다.", HttpStatus.OK);
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			return new ResponseEntity<>("카테고리 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/**
	 * 카테고리 수정
	 * @param categoryUpdateDto
	 * @return
	 */
	@PutMapping("/admin/category")
	public ResponseEntity<String> updateCategory(
			@RequestBody CategoryUpdateDto categoryUpdateDto) {
		try {
            this.categoryService.updateCategory(categoryUpdateDto);
            return new ResponseEntity<>("카테고리 정보 수정이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("카테고리 정보 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	


    /**
     * 카테고리 목록 조회
     * @param
     * @return
     */
	 @GetMapping("/admin/category")
	    public ResponseEntity<List<CategoryListDto>> getCategoryList() {
	        try {
	            List<CategoryListDto> categoryList = this.categoryService.getCategoryList();
	            return new ResponseEntity<>(categoryList, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	 }
	 
	 
	 
	/**
	* TO 용 판매중인 카테고리 목록 조회
	* @param
	* @return
	*/
	@GetMapping("/to/category")
	public ResponseEntity<List<CategoryListForToDto>> getAvailableCategoryList() {
		try {
			List<CategoryListForToDto> categoryList = this.categoryService.getAvailableCategoryList();
		    return new ResponseEntity<>(categoryList, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 메뉴 등록
	 * @param menuCreateDto
	 * @return
	 */
	@PostMapping("/admin/menu")
	public ResponseEntity<String> createMenu(
			@RequestBody MenuCreateDto menuCreateDto) {

		// 신규 관리자 계정 생성
		try {
			this.menuService.createMenu(menuCreateDto);
			return new ResponseEntity<>("성공적으로 등록되었습니다.", HttpStatus.OK);
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			return new ResponseEntity<>("메뉴 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 메뉴 수정
	 * @param menuUpdateDto
	 * @return
	 */
	@PutMapping("/admin/menu")
	public ResponseEntity<String> updateMenu(
			@RequestBody MenuUpdateDto menuUpdateDto){

		// 메뉴 수정
		try {
			this.menuService.updateMenu(menuUpdateDto);
			return new ResponseEntity<>("성공적으로 등록되었습니다.", HttpStatus.OK);
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			return new ResponseEntity<>("메뉴 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	  /**
     * 메뉴 삭제
     * @param menuId
     * @return
     */
    @DeleteMapping("/admin/menu")
    public ResponseEntity<String> deleteMenu(
    		@RequestParam Long menuNo) {
        try {
            this.menuService.deleteMenu(menuNo);
            return new ResponseEntity<>("메뉴가 성공적으로 삭제되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("메뉴 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    
    /**
     * 특정 카테고리의 판매중인 메뉴 목록 조회
     * @param categoryId
     * @return
     */
    @GetMapping("/to/menu")
    public ResponseEntity<List<MenuListForToDto>> getAvailableMenuList(
    		@RequestParam Long categoryNo) {
        try {
            List<MenuListForToDto> menuList = this.menuService.getAvailableMenuList(categoryNo);
            return new ResponseEntity<>(menuList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * TO용 판매가능한 메뉴 상세정보 조회
     * @param menuId
     * @return
     */
    @GetMapping("/to/menu")
    public ResponseEntity<MenuForToDto> getTOMenuDetail(
    		@RequestParam Long menuNo){
    	try {
            MenuForToDto menu = this.menuService.getAvailableMenu(menuNo);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
	

    /**
     * 특정 카테고리의 메뉴 목록 조회
     * @param categoryId
     * @return
     */
    @GetMapping("/admin/menu")
    public ResponseEntity<List<MenuListDto>> getMenuList(@RequestParam Long categoryId) {
        try {
            List<MenuListDto> menuList = this.menuService.getMenuList(categoryId);
            return new ResponseEntity<>(menuList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    /**
     * TO용 메뉴 상세정보 조회
     * @param menuId
     * @return
     */
    @GetMapping("/admin/menu")
    public ResponseEntity<MenuReadDto> getMenuDetail(@RequestParam Long menuNo){
    	try {
    		MenuReadDto menuList = this.menuService.getMenu(menuNo);
            return new ResponseEntity<>(menuList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
