package co.com.store.storeapirestful.service.dataRepository;

import co.com.store.storeapirestful.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository {

    /**
     *
     * @return
     */
    List<Order> getAllOrders();

    Order getOrderById(int id);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    boolean deleteOrder(int id);




}
