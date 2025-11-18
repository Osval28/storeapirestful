package co.com.store.storeapirestful.service.repository;

import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.service.dataRepository.SpringDataProductRepository;
import co.com.store.storeapirestful.service.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mySQLProductService")
@AllArgsConstructor
public class MySQLProductService implements ProductRepository{

    private final SpringDataProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return ProductEntity.toModelList(productRepository.findAll());
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }

    @Override
    public List<Product> getProductsBetween(Double min, Double max) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }
}
