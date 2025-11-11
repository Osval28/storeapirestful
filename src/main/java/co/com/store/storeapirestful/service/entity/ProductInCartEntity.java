package co.com.store.storeapirestful.service.entity;

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




}
