package co.com.store.storeapirestful.service.entity;

import co.com.store.storeapirestful.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String category;
    private double price;

    @OneToMany(fetch = FetchType.LAZY)
    public static ProductEntity fromModel(Product product) {
        return new ProductEntity(product.getId(), product.getName(), product.getCategory(), product.getPrice());
    }

    public static Product toModel(ProductEntity product) {
        return new Product(product.getId(), product.getName(), product.getCategory(), product.getPrice());
    }





}
