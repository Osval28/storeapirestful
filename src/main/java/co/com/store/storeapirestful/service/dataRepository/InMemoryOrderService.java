package co.com.store.storeapirestful.service.dataRepository;

import co.com.store.storeapirestful.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class InMemoryOrderService implements OrderRepository{

    private List<Order> orders = new ArrayList<>();


    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    @Override
    public boolean deleteOrder(int id) {
        return false;
    }
}
