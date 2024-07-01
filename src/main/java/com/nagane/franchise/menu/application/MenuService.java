package com.nagane.franchise.menu.application;

import java.util.List;

import com.nagane.franchise.menu.dto.menu.MenuForToDto;
import com.nagane.franchise.menu.dto.menu.MenuListForToDto;
import com.nagane.franchise.menu.dto.menu.MenuCreateDto;
import com.nagane.franchise.menu.dto.menu.MenuListDto;
import com.nagane.franchise.menu.dto.menu.MenuReadDto;
import com.nagane.franchise.menu.dto.menu.MenuUpdateDto;

public interface MenuService {
	
	// 관리자 crud
	public Long createMenu(MenuCreateDto menuCreateDto);
	public List<MenuListDto> getMenuList(Long categoryNo);
	public MenuReadDto getMenu(Long menuNo);
	public Long updateMenu(MenuUpdateDto menuUpdateDto);
	public boolean deleteMenu(Long menuNo);
	
	// TO 조회
	public List<MenuListForToDto> getAvailableMenuList(Long categoryNo);
	public MenuForToDto getAvailableMenu(Long menuNo);
}
