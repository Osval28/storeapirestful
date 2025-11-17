package co.com.store.storeapirestful.service.repository;

import co.com.store.storeapirestful.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface OrderRepository {


    List<Order> getAllOrders();

    Order getOrderById(String id);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrder(String id);




}
