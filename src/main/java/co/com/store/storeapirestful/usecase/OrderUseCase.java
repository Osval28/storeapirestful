package co.com.store.storeapirestful.usecase;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.service.dataRepository.OrderRepository;
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







}
