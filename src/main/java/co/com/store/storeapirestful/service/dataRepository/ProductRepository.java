package co.com.store.storeapirestful.service.dataRepository;

import co.com.store.storeapirestful.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository {

    List<Product> getAllProducts();
    Product getProductById(String id);
    List<Product> getProductsBetween(Double min, Double max);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);
    List<Product> getProductsByCategory(String category);



}
