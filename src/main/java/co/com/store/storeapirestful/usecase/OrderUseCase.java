package co.com.store.storeapirestful.usecase;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.service.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderUseCase {

    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.getAllOrders();
    }

    public Order getOrderById(String id) {
        return orderRepository.getOrderById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    public Order updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteOrder(id);
    }

}
