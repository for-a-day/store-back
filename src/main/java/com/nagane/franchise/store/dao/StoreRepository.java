
package com.nagane.franchise.store.dao; 
import org.springframework.data.jpa.repository.JpaRepository;
  
import com.nagane.franchise.store.domain.Store;
  
public interface StoreRepository extends JpaRepository<Store, Long> {
  
}
