package co.com.store.storeapirestful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor

public class Order {

    private String id;
    private String CustomerId;
    private ArrayList <ProductInCart> items = new ArrayList<>();
    private Double total = 0.0;


    public Double calculateTotal() {
        if (items.isEmpty()){
            throw new IllegalArgumentException("No se puede calcular el total de un pedido vacío");
        }
        for (ProductInCart item : items) {
            total += item.getTotalPrice();
        }
        if (total > 100000) {
            total = total*0.95;
        }
        return total;
    }

    public Order(String id, String customerId, ArrayList<ProductInCart> items) {
        this.id = id;
        CustomerId = customerId;
        this.items = items;
    }

}
