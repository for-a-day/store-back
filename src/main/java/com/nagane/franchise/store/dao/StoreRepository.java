
package com.nagane.franchise.store.dao; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagane.franchise.store.domain.Store;

/**
 * @author ljy, nsr
 * @since 2024.06.28
 * 지점 관련 repository 코드
 * **/
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	/* 지점 코드로 해당하는 점포 있는지 조회 */
	Optional<Store> findByStoreCode(String storeCode);
	
	/* 지점 코드와 대표자명으로 해당하는 레코드 반환 */
	Optional<Store> findByStoreCodeAndRprName(String storeCode, String rprName);
	
}
