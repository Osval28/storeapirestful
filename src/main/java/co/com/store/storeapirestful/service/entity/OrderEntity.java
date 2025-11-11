package co.com.store.storeapirestful.service.entity;

import co.com.store.storeapirestful.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

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
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "productsInOrders",
            joinColumns = @JoinColumn(name = "orderID"),
            inverseJoinColumns = @JoinColumn(name = "productID")
    )
    private ArrayList<ProductInCartEntity> items = new ArrayList<>();
    private Double total = 0.0;

    public static OrderEntity fromModel(Order order) {
        ArrayList<ProductInCartEntity> itemsEntity = new ArrayList<>();
        for (var item : order.getItems()) {
            itemsEntity.add(ProductInCartEntity.fromModel(item));
        }
        return new OrderEntity(order.getId(),order.getCustomerId(),itemsEntity,order.getTotal());
    }


}
