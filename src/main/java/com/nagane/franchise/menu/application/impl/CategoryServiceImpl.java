package com.nagane.franchise.menu.application.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagane.franchise.menu.application.CategoryService;
import com.nagane.franchise.menu.dao.CategoryRepository;
import com.nagane.franchise.menu.domain.Category;
import com.nagane.franchise.menu.dto.CategoryDto;

public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;

	/**
	 * 카테고리 등록
	 * @param CategoryDto 저장할 카테고리 정보
	 * @return Long 카테고리 No
	 */
	public Long createCategory(CategoryDto categoryDto) {
		// 2. 카테고리 엔티티 생성 및 수정
		Category category = Category.builder()
				.categoryNo(categoryDto.getCategoryNo())
				.categoryName(categoryDto.getCategoryName())
				.state(categoryDto.getCategoryState())
				.build();
		
		// 카테고리 저장
		Category categorySaved = categoryRepository.save(category);
		
		return categorySaved.getCategoryNo();
	}
	
	
	/**
	 * 판매중인 카테고리 목록
	 * @param Integer 카테고리 상태 
	 * @return 
	 */
	public List<CategoryDto> getAvailableCategoryList(Integer state){
		
		List<Category> categoryList = categoryRepository.findByState(state);

		// Category를 CategoryDto로 변환
        List<CategoryDto> categoryDtoList = categoryList.stream()
                .map(this::convertToDto) // 메소드 참조를 이용한 변환
                .collect(Collectors.toList());
        
		return categoryDtoList;
	}
	

	// Category를 CategoryDto로 변환하는 메소드
    private CategoryDto convertToDto(Category category) {
    	
    	CategoryDto categoryDto = new CategoryDto();
    	category.setCategoryName(category.getCategoryName());
    	category.setState(category.getState());

        return categoryDto;
    }
	
	
}
