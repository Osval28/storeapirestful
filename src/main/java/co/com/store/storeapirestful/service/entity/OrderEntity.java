package co.com.store.storeapirestful.service.entity;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.model.OrderStatus;
import co.com.store.storeapirestful.model.ProductInCart;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    private String id;

    private String customerId;

    private Double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInCartEntity> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.OPEN;

    public static OrderEntity fromModel(Order order) {

        OrderEntity Orderentity = new OrderEntity();
        Orderentity.setId(order.getId());
        Orderentity.setCustomerId(order.getCustomerId());
        Orderentity.setTotal(order.getTotal());

        List<ProductInCartEntity> itemEntities = new ArrayList<>();
        for (var item : order.getItems()) {
            var itemEntity = ProductInCartEntity.fromModel(item);
            itemEntity.setOrder(Orderentity);
            itemEntities.add(itemEntity);
        }

        Orderentity.setItems(itemEntities);
        return Orderentity;
    }

    public static Order toModel(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setCustomerId(orderEntity.getCustomerId());
        order.setTotal(orderEntity.getTotal());

        ArrayList<ProductInCart> items = new ArrayList<>();
        for (var itemEntity : orderEntity.getItems()) {
            items.add(ProductInCartEntity.toModel(itemEntity));
        }
        order.setItems(items);
        return order;
    }

    public static List<Order> toModelList(List<OrderEntity> orderEntities) {
        List<Order> orders = new ArrayList<>();
        for (var orderEntity : orderEntities) {
            orders.add(toModel(orderEntity));
        }
        return orders;
    }

}
