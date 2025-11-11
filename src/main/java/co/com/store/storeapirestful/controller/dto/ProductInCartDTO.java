package co.com.store.storeapirestful.controller.dto;

import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.model.ProductInCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ProductInCartDTO {

    Product product;
    int quantity;

    public Double getTotalPrice() {
        return product.getPrice()*quantity;
    }

    public static ProductInCartDTO fromModel(ProductInCart item) {
        return new ProductInCartDTO(item.getProduct(), item.getQuantity());
    }

    public static ProductInCart toModel(ProductInCartDTO itemDTO) {
        return new ProductInCart(itemDTO.getProduct(), itemDTO.getQuantity());
    }

}
