package co.com.store.storeapirestful.service.entity;

import co.com.store.storeapirestful.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String category;
    private double price;

    public static ProductEntity fromModel(Product model) {
        return new ProductEntity(model.getId(), model.getName(), model.getCategory(), model.getPrice());
    }

    public static Product toModel(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getCategory(), entity.getPrice());
    }

    public static List<Product> toModelList(List<ProductEntity> entities) {
        List<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : entities) {
            products.add(toModel(productEntity));
        }
        return products;
    }


}
