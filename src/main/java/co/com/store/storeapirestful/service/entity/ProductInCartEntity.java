package co.com.store.storeapirestful.service.entity;

import co.com.store.storeapirestful.model.ProductInCart;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productsInCart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private OrderEntity order;

    public static ProductInCartEntity fromModel(ProductInCart model) {
        ProductInCartEntity entity = new ProductInCartEntity();entity.setQuantity(model.getQuantity());entity.setProduct(ProductEntity.fromModel(model.getProduct()));
        return entity;
    }

    public static ProductInCart toModel(ProductInCartEntity entity) {
        return new ProductInCart(ProductEntity.toModel(entity.getProduct()), entity.getQuantity());
    }




}
