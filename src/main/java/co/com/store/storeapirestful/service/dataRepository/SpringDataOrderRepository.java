package co.com.store.storeapirestful.service.dataRepository;

import co.com.store.storeapirestful.service.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, String> {


}
