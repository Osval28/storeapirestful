package co.com.store.storeapirestful.service.entity;

import co.com.store.storeapirestful.model.ProductInCart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInCartEntity {

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "productsInOrders",
            joinColumns = @JoinColumn(name = "productID"),
            inverseJoinColumns = @JoinColumn(name = "orderID")
    )
    private ProductEntity product;


    public static ProductInCartEntity fromModel(ProductInCart productInCart){
        return new ProductInCartEntity(productInCart.getQuantity(), ProductEntity.fromModel(productInCart.getProduct()));
    }

    public static ProductInCart toModel(ProductInCartEntity productInCartEntity){
        return new ProductInCart(ProductEntity.toModel(productInCartEntity.getProduct()),productInCartEntity.getQuantity());
    }


}
