
package com.nagane.franchise.store.dao; 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

	/* 영업중인 가맹점 리스트 반환 */
    @Query("SELECT s FROM Store s WHERE s.state = 1")
    List<Store> findActiveStores();
    
    Optional<Store> findByRprName(String rprName);
}
