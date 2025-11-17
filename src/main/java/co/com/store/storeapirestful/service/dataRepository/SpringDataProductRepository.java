package co.com.store.storeapirestful.service.dataRepository;

import co.com.store.storeapirestful.service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, String> {

}
