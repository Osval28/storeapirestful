package co.com.store.storeapirestful.service.repository;
import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.model.ProductInCart;
import co.com.store.storeapirestful.service.dataRepository.SpringDataOrderRepository;
import co.com.store.storeapirestful.service.entity.OrderEntity;
import co.com.store.storeapirestful.service.entity.ProductInCartEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mySQLOrderService")
@AllArgsConstructor

public class MySQLOrderService implements OrderRepository{

    private final SpringDataOrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return OrderEntity.toModelList(orderRepository.findAll());
    }

    @Override
    public Order getOrderById(String id) {
        return OrderEntity.toModel(orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("Pedido con id: " + id + " no encontrado")));
    }

    @Override
    public Order createOrder(Order order) {
        if (orderRepository.existsById(order.getId())){
            throw new IllegalArgumentException("Pedido con id: " + order.getId() + " ya existe");
        } else {
            OrderEntity orderEntity = OrderEntity.fromModel(order);
            orderRepository.save(orderEntity);
            return OrderEntity.toModel(orderEntity);
        }
    }

    @Override
    public Order updateOrder(Order order) {
        OrderEntity existingOrderEntity = orderRepository.findById(order.getId()).orElseThrow(() ->
                new IllegalArgumentException("Pedido con id: " + order.getId() + " no encontrado"));

        existingOrderEntity.setCustomerId(order.getCustomerId());
        existingOrderEntity.setTotal(order.getTotal());
        List<ProductInCartEntity> newItems = new ArrayList<>();

        for (ProductInCart item : order.getItems()) {
            ProductInCartEntity itemEntity = ProductInCartEntity.fromModel(item);
            itemEntity.setOrder(existingOrderEntity);
            newItems.add(itemEntity);
        }
        existingOrderEntity.setItems(newItems);
        orderRepository.save(existingOrderEntity);
        return OrderEntity.toModel(existingOrderEntity);

    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

}
