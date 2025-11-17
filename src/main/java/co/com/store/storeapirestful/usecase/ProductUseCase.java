package co.com.store.storeapirestful.usecase;
import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.service.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor

public class ProductUseCase {

    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById (String id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProductsBetween (Double min, Double max) {
        return productRepository.getProductsBetween(min, max);
    }

    public List<Product> getProductsByCategory (String category) {
        return productRepository.getProductsByCategory(category);
    }

    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    public Product updateProduct (Product product) {
        return productRepository.updateProduct(product);
    }

    public void deleteProduct (String id) {
        productRepository.deleteProduct(id);
    }




}
