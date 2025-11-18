package co.com.store.storeapirestful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String customerId;
    private ArrayList <ProductInCart> items = new ArrayList<>();
    private Double total;
    private OrderStatus status = OrderStatus.OPEN;


    public Order(String id, String customerId, ArrayList<ProductInCart> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }

}
