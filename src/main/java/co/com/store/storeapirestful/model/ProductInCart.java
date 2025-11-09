package co.com.store.storeapirestful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ProductInCart {
    Product product;
    int quantity;

    public Double getTotalPrice() {
        return product.getPrice()*quantity;
    }


}
