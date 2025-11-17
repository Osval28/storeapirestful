package co.com.store.storeapirestful.service.entity;

import co.com.store.storeapirestful.model.Order;
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
}
