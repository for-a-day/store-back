package com.nagane.franchise.menu.application.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagane.franchise.menu.application.CategoryService;
import com.nagane.franchise.menu.dao.CategoryRepository;
import com.nagane.franchise.menu.dao.MenuRepository;
import com.nagane.franchise.menu.domain.Category;
import com.nagane.franchise.menu.domain.Menu;
import com.nagane.franchise.menu.dto.category.CategoryCreateDto;
import com.nagane.franchise.menu.dto.category.CategoryListDto;
import com.nagane.franchise.menu.dto.category.CategoryListForToDto;
import com.nagane.franchise.menu.dto.category.CategoryUpdateDto;

/**
 * @author nsr
 * @since 2024.06.28
 * 카테고리 관리 기능
 * **/
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	// Admin
	/**
	 * 카테고리 등록
	 * @param CategoryCreateDto 생성할 카테고리 정보
	 * @return Long 카테고리 번호
	 */
	@Override
	public Long createCategory(CategoryCreateDto categoryCreateDto) {
		// 카테고리 엔티티 생성
		Category category = Category.builder()
				.categoryName(categoryCreateDto.getCategoryName())
				.state(1)
				.build();
		
		// 카테고리 저장
		Category categorySaved = categoryRepository.save(category);
		
		return categorySaved.getCategoryNo();
	}

	/**
	 * 카테고리 수정
	 * @param categoryUpdateDto 수정할 카테고리 정보
	 * @return Long 카테고리 번호
	 */
	@Override
	public Long updateCategory(CategoryUpdateDto categoryUpdateDto) {

		// 1. 기존 카테고리 정보 가져오기
		 Category category = categoryRepository.findById(categoryUpdateDto.getCategoryNo())
	                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다."));
		// 2. 카테고리 정보 업데이트
		category.setCategoryNo(categoryUpdateDto.getCategoryNo());
		category.setCategoryName(categoryUpdateDto.getCategoryName());
		category.setState(categoryUpdateDto.getCategoryState());
			
		
		// 3. 카테고리 수정
		Category categorySaved = categoryRepository.save(category);
		
		return categorySaved.getCategoryNo();
	}
	
	/**
	 * 카테고리 목록
	 * @param 
	 * @return List<CategoryListDto>
	 */
	@Override
	public List<CategoryListDto> getCategoryList(){
		
		List<Category> categoryList = categoryRepository.findAll();

		// Category를 CategoryDto로 변환
        List<CategoryListDto> categoryDtoList = categoryList.stream()
                .map(category ->{

                	CategoryListDto categoryDto = new CategoryListDto();
                	categoryDto.setCategoryNo(category.getCategoryNo());
                	categoryDto.setCategoryName(category.getCategoryName());

                    return categoryDto;
                }) // 메소드 참조를 이용한 변환
                .collect(Collectors.toList());
        
		return categoryDtoList;
	}
	
	/**
	 * 카테고리 단종 처리
	 * @param Long 카테고리 번호 
	 * @return boolean
	 */
	@Override
	public boolean updateCategoryState(Long categoryNo) {
		
		// 안에 메뉴가 있는지 없는지 확인 하고 삭제
		Long menuCount = menuRepository.countByCategory_CategoryNo(categoryNo);
		if(menuCount > 0) {
			return false;
		}
		
		Optional<Category> categoryOptional = categoryRepository.findById(categoryNo);

		if(categoryOptional.isPresent()) {
			Category category = categoryOptional.get();
			category.setState(0);
			
			categoryRepository.save(category);
			
			return true;
		}
		
		return false;
	}
	
	

	/**
	 * 카테고리 삭제
	 * @param Long 카테고리 번호 
	 * @return boolean
	 */
	@Override
	public boolean deleteCategory(Long categoryNo) {
		
		// 안에 메뉴가 있는지 없는지 확인 하고 삭제
		Long menuCount = menuRepository.countByCategory_CategoryNo(categoryNo);
		if(menuCount > 0) {
			return false;
		}
		
		categoryRepository.deleteById(categoryNo);
		
			return true;
	}
	
	//TO
	/**
	 * 판매중인 카테고리 목록
	 * @param
	 * @return List<AvailableCategoryListDto>
	 */
	@Override
	public List<CategoryListForToDto> getAvailableCategoryList(){
		
		// 상태가 1(판매중)인 키테고리 목록 조회
		List<Category> categoryList = categoryRepository.findByState(1);
		
		// Category를 CategoryDto로 변환
		List<CategoryListForToDto> categoryDtoList = categoryList.stream()
				.map(category ->{
					
					CategoryListForToDto categoryDto = new CategoryListForToDto();
					categoryDto.setCategoryName(category.getCategoryName());
					
					return categoryDto;
				}) // 메소드 참조를 이용한 변환
				.collect(Collectors.toList());
		
		return categoryDtoList;
	}
	
}
