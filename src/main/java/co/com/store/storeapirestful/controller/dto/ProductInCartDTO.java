package co.com.store.storeapirestful.controller.dto;

import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.model.ProductInCart;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ProductInCartDTO {

    @NotNull(message = "the product is required")
    Product product;

    @NotNull(message = "the quantity is required")
    @Min(value = 0, message = "the quantity must >= 0")
    int quantity;

    public Double getPrice() {
        return product.getPrice()*quantity;
    }

    public static ProductInCartDTO fromModel(ProductInCart item) {
        return new ProductInCartDTO(item.getProduct(), item.getQuantity());
    }

    public static ProductInCart toModel(ProductInCartDTO itemDTO) {
        return new ProductInCart(itemDTO.getProduct(), itemDTO.getQuantity());
    }

}
