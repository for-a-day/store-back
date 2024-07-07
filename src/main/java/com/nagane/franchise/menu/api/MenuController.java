package com.nagane.franchise.menu.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.menu.application.CategoryService;
import com.nagane.franchise.menu.application.MenuService;
import com.nagane.franchise.menu.dto.category.CategoryCreateDto;
import com.nagane.franchise.menu.dto.category.CategoryListDto;
import com.nagane.franchise.menu.dto.category.CategoryListForToDto;
import com.nagane.franchise.menu.dto.category.CategoryUpdateDto;
import com.nagane.franchise.menu.dto.menu.MenuCreateDto;
import com.nagane.franchise.menu.dto.menu.MenuForToDto;
import com.nagane.franchise.menu.dto.menu.MenuListDto;
import com.nagane.franchise.menu.dto.menu.MenuListForToDto;
import com.nagane.franchise.menu.dto.menu.MenuReadDto;
import com.nagane.franchise.menu.dto.menu.MenuUpdateDto;
import com.nagane.franchise.util.model.response.BaseResponseBody;
import com.nagane.franchise.util.model.response.ErrorResponseBody;
import com.nagane.franchise.util.model.response.SuccessResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


/**
 * @author nsr, (doc) jy
 * @since 2024.06.29
 * Menu controller 코드
 * 메뉴 및 카테고리 관련 controller
 * **/
@Tag(name= "메뉴 관리 API", description = "메뉴 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
public class MenuController {
	
	// 반환할 데이터를 담을 객체 생성
    private BaseResponseBody responseBody;
    
    // 반환할 데이터를 담을 맵 생성
    private Map<String, Object> data;
    
    // 의존성 주입(di)
	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	/**
	 * 카테고리 등록
	 * @param CategoryCreateDto
	 * @return String
	 */
	@Operation(summary = "카테고리 등록", description = "(관리자) 관리자 측에서 카테고리 신규 등록 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/admin/category")
	public ResponseEntity<? extends BaseResponseBody> createCategory(
			@RequestBody CategoryCreateDto categoryCreateDto) {
		
		System.out.println("받음 : " + categoryCreateDto.toString());
		logger.info("받음 : {}", categoryCreateDto.toString());
		
		// 신규 관리자 계정 생성
		try {
			this.categoryService.createCategory(categoryCreateDto);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "성공적으로 등록되었습니다.");
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "카테고리 등록에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}

	/**
	 * 카테고리 수정
	 * @param categoryUpdateDto
	 * @return String
	 */
	@Operation(summary = "카테고리 수정", description = "(관리자) 관리자 측에서 카테고리 정보 수정 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/admin/category")
	public ResponseEntity<? extends BaseResponseBody> updateCategory(
			@RequestBody CategoryUpdateDto categoryUpdateDto) {
		try {
            this.categoryService.updateCategory(categoryUpdateDto);
            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "카테고리 정보 수정이 완료되었습니다.");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "카테고리 정보 수정에 실패했습니다.");
        }
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	
    /**
     * 카테고리 목록 조회
     * @param
     * @return List<CategoryListDto>
     */
	@Operation(summary = "카테고리 목록 조회", description = "(관리자) 관리자 측에서 카테고리 목록을 조회할 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        		content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	 @GetMapping("/admin/category")
	 public ResponseEntity<? extends BaseResponseBody> getCategoryList() {
		 try {
			 List<CategoryListDto> categoryList = this.categoryService.getCategoryList();
			 System.out.println(categoryList.toString());
			 data = new HashMap<>();
			 data.put("categoryList", categoryList);
			 
			 responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "카테고리 목록을 조회했습니다.", data);
		 } catch (Exception e) {
			 System.out.println(e.getMessage());
			 responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "카테고리 목록 조회에 실패했습니다.");
		 }
		 return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	 }
	 
//	 
//	 /**
//	  * 카테고리 단종 처리
//	  * @param Long 카테고리 번호
//	  * @return String
//	  */
//	 @PostMapping("admin/categoryState")
//	 public ResponseEntity<String> updateCategory(@RequestParam Long categoryNo){
//		 try {
//	            this.categoryService.updateCategoryState(categoryNo);
//	            return new ResponseEntity<>("카테고리가 성공적으로 삭제되었습니다.", HttpStatus.OK);
//	        } catch (Exception e) {
//	            return new ResponseEntity<>("카테고리 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//	 }
//	 
//	 
	 /**
	  * 카테고리 영구 삭제
	  * @param Long 카테고리 번호
	  * @return String
	  */
	@Operation(summary = "카테고리 영구 삭제", description = "(관리자) 관리자 측에서 카테고리 영구 삭제할 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	 @DeleteMapping("admin/category")
	 public ResponseEntity<? extends BaseResponseBody> deleteCategory(@RequestParam Long categoryNo){
		 try {
	            boolean success = this.categoryService.deleteCategory(categoryNo);
	            if (success)
	            	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "카테고리가 성공적으로 삭제되었습니다.");
	            else
	            	responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "해당 카테고리의 메뉴가 있습니다.");
	        } catch (Exception e) {
	        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "카테고리 삭제에 실패했습니다.");
	        }
		 return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	 }
	 
	 
	 
	/**
	* TO 용 판매중인 카테고리 목록 조회
	* @param
	* @return List<CategoryListForToDto>
	*/
	@Operation(summary = "TO 용 판매중인 카테고리 목록 조회", description = "(테이블 오더) 테이블 오더에서 현재 조회 가능한 카테고리를 확인할 때 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@GetMapping("/to/category")
	public ResponseEntity<? extends BaseResponseBody> getAvailableCategoryList() {
		try {
			List<CategoryListForToDto> categoryList = this.categoryService.getAvailableCategoryList();
			data = new HashMap<>();
			data.put("categoryList", categoryList);
			responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "해당 카테고리의 메뉴가 있습니다.", data);
		} catch (Exception e) {
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "카테고리 조회에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}

	

	/**
	 * 메뉴 등록
	 * @param MenuCreateDto
	 * @return String
	 */
	@Operation(summary = "메뉴 등록", description = "(관리자) 관리자가 신규 메뉴 등록 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PostMapping("/admin/menu")
	public ResponseEntity<? extends BaseResponseBody> createMenu(
			@ModelAttribute  MenuCreateDto menuCreateDto){
		// 신규 관리자 계정 생성
		try {
			this.menuService.createMenu(menuCreateDto);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "성공적으로 등록되었습니다.");
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "메뉴 등록에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	    

	/**
	 * 메뉴 수정
	 * @param MenuUpdateDto
	 * @return String
	 */
	@Operation(summary = "메뉴 수정", description = "(관리자) 관리자가 메뉴 정보 수정 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
	@PutMapping("/admin/menu")
	public ResponseEntity<? extends BaseResponseBody> updateMenu(
			@ModelAttribute MenuUpdateDto menuUpdateDto){
		
		// 메뉴 수정
		try {
			this.menuService.updateMenu(menuUpdateDto);
			responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "성공적으로 수정되었습니다.");
		// 예외 발생 시, 에러 return
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "메뉴 수정에 실패했습니다.");
		}
		return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	}
	

	  /**
     * 메뉴 영구 삭제
     * @param Long 메뉴 번호
     * @return String
     */
	@Operation(summary = "메뉴 영구 삭제", description = "(관리자) 관리자가 메뉴 삭제 시 사용하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
    @DeleteMapping("/admin/menu")
    public ResponseEntity<? extends BaseResponseBody> deleteMenu(
    		@RequestParam Long menuNo) {
        try {
            this.menuService.deleteMenu(menuNo);
            responseBody = BaseResponseBody.of(HttpStatus.OK.value(), "메뉴가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "메뉴 삭제에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
    

    
    /**
     * 특정 카테고리의 판매중인 메뉴 목록 조회
     * @param Long 카테고리 번호
     * @return List<MenuListForToDto>
     */
	@Operation(summary = "특정 카테고리의 판매중인 메뉴 목록 조회", description = "(테이블 오더) 테이블 오더 기준으로 카테고리를 특정해서 해당 카테고리에 속한 메뉴(단, 판매 중)만 조회하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
    @GetMapping("/to/menuList")
    public ResponseEntity<? extends BaseResponseBody> getAvailableMenuList(
    		@RequestParam String storeCode, @RequestParam Long categoryNo) {
        try {
            List<MenuListForToDto> menuList = this.menuService.getAvailableMenuList(storeCode, categoryNo);
            System.out.println(menuList.toString());
            data = new HashMap<>();
            data.put("menuList", menuList);
            responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "메뉴 목록을 성공적으로 조회했습니다.", data);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "메뉴 목록 조회에 실패했습니다.");
        }
        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
    

    /**
     * TO용 판매가능한 메뉴 상세정보 조회
     * @param Long 메뉴 번호
     * @return MenuForToDto
     */
	@Operation(summary = "TO용 판매가능한 메뉴 상세정보 조회", description = "(테이블 오더) 테이블 오더에서 선택한 (판매 가능한) 메뉴의 상세 정보롤 조회할 수 있게 하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
    @GetMapping("/to/menu")
    public ResponseEntity<? extends BaseResponseBody> getTOMenuDetail(
    		@RequestParam String storeCode, @RequestParam Long menuNo){
    	try {
            MenuForToDto menu = this.menuService.getAvailableMenu(storeCode, menuNo);
            data = new HashMap<>();
            data.put("menu", menu);
            responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "메뉴 정보를 성공적으로 불러왔습니다.", data);
        } catch (Exception e) {
            responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "메뉴 상세 정보 조회에 실패했습니다.");
        }
    	return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
    
    
	

	/**
	 * 특정 카테고리의 메뉴 목록 조회
	 *
	 * @param categoryNo 카테고리 번호
	 * @return 메뉴 목록 응답
	 */
	@Operation(summary = "특정 카테고리의 메뉴 목록 조회", description = "관리자가 선택한 카테고리에 속한 메뉴 목록을 조회할 수 있는 API입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK",
	                content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
	                content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	})
	@GetMapping("/admin/menuList/{categoryNo}")
	public ResponseEntity<? extends BaseResponseBody> getMenuList(@PathVariable Long categoryNo) {
	    try {
	        List<MenuListDto> menuList = this.menuService.getMenuList(categoryNo);
	        Map<String, Object> data = new HashMap<>();
	        data.put("menuList", menuList);
	        SuccessResponseBody responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "메뉴 목록을 성공적으로 조회했습니다.", data);
	        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	    } catch (Exception e) {
	        // 예외 발생 시 처리
	        e.printStackTrace();
	        ErrorResponseBody responseBody = ErrorResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "메뉴 목록 조회에 실패했습니다.");
	        return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
	    }
	}
    
    
    /**
     * 관리자용 메뉴 상세정보 조회
     * @param Long 메뉴 번호
     * @return MenuReadDto
     */
	@Operation(summary = "관리자용 메뉴 상세정보 조회", description = "(관리자) 관리자가 선택한 메뉴의 상세 정보를 조회할 수 있게 하는 api입니다.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", 
	            	content = @Content(schema = @Schema(implementation = SuccessResponseBody.class))),
	        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", 
        	content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
	    })
    @GetMapping("/admin/menu")
    public ResponseEntity<? extends BaseResponseBody> getMenuDetail(@RequestParam Long menuNo){
    	try {
    		MenuReadDto menu = this.menuService.getMenu(menuNo);
    		data = new HashMap<>();
            data.put("menu", menu);
            responseBody = SuccessResponseBody.of(HttpStatus.OK.value(), "메뉴 정보를 성공적으로 불러왔습니다.", data);
        } catch (Exception e) {
        	responseBody = BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "메뉴 상세 정보 조회에 실패했습니다.");
        }
    	return ResponseEntity.status(responseBody.getStatusCode()).body(responseBody);
    }
	
	
	    
}
