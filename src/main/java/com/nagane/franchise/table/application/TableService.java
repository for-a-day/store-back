package com.nagane.franchise.table.application;

import java.util.List;

import com.nagane.franchise.table.dto.TableAdminDto;
import com.nagane.franchise.table.dto.TableLoginDto;
import com.nagane.franchise.table.dto.TableNoDto;
import com.nagane.franchise.table.dto.TableResponseDto;
import com.nagane.franchise.table.dto.TableUpdateDto;

/**
 * @author ljy
 * @since 2024.07.01
 * Table Service 코드
 * 테이블 Service
 * **/
public interface TableService {
	
	/** 테이블 목록 조회 */
	List<TableResponseDto> getTableList(Long storeNo);
	
	/** 테이블 신규 등록 */
	void createTable(Long storeNo);
	
	/** 테이블 수정 */
	void updateTable(TableUpdateDto tableUpdateDto);
	
	/** 테이블 삭제 */
	void deleteTable(Long tableNo);
	
	/** 테이블 오더 로그인 */
	void loginTable(TableLoginDto tableLoginDto);
	
	/** 테이블 관리자 로그인 */
	void loginTableAdmin(TableAdminDto tableAdminDto);
	
	/** 테이블 오더 비활성화 */
	void logoutTable(String tableCode);
}
