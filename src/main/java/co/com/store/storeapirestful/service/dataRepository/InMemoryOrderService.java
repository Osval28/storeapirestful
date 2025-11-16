package co.com.store.storeapirestful.service.dataRepository;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.model.ProductInCart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class InMemoryOrderService implements OrderRepository{

    private List<Order> orders = new ArrayList<>();


    @Override
    public List<Order> getAllOrders() {
        return this.orders;
    }

    @Override
    public Order getOrderById(String id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Usuario con id: " + id + " no encontrado"));
    }

    @Override
    public Order createOrder(Order order) {
        if (orders.stream().anyMatch(existingOrder -> existingOrder.getId().equals(order.getId()))) {
            throw new IllegalArgumentException("Order con id: " + order.getId() + " ya existe");
        } else {
            orders.add(order);
            return order;
        }
    }

    @Override
    public Order updateOrder(Order order) {
        Order existingOrder = getOrderById(order.getId());
        existingOrder.setItems(order.getItems());
        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setTotal(order.getTotal());
        return existingOrder;
    }

    @Override
    public void deleteOrder(String id) {
        Order orderToDelete = getOrderById(id);
        this.orders.remove(orderToDelete);
    }

    public Double calculateTotal(Order order) {
        Double total = 0.0;
        if (order.getItems().isEmpty()){
            throw new IllegalArgumentException("No se puede calcular el total de un pedido vacío");
        }
        for (ProductInCart item : order.getItems()) {
            total += item.getTotalPrice();
        }
        if (total > 100000) {
            total = total*0.95;
        }
        return total;
    }

}
