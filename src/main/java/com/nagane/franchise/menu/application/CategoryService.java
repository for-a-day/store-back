package com.nagane.franchise.menu.application;

import java.util.List;

import com.nagane.franchise.menu.dto.category.CategoryListForToDto;
import com.nagane.franchise.menu.dto.category.CategoryCreateDto;
import com.nagane.franchise.menu.dto.category.CategoryListDto;
import com.nagane.franchise.menu.dto.category.CategoryUpdateDto;

public interface CategoryService {
	
	// 관리자 crud
	public Long createCategory(CategoryCreateDto categoryCreateDto);
	public List<CategoryListDto> getCategoryList();
	public Long updateCategory(CategoryUpdateDto categoryUpdateDto);
	public boolean deleteCategory(Long categoryNo);
	
	// TO 조회
	public List<CategoryListForToDto> getAvailableCategoryList();
}
