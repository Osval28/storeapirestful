package co.com.store.storeapirestful.service.repository;

import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.service.dataRepository.SpringDataProductRepository;
import co.com.store.storeapirestful.service.entity.OrderEntity;
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
        return ProductEntity.toModel(productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Producto con id: " + id + " no encontrado")));
    }

    @Override
    public List<Product> getProductsBetween(Double min, Double max) {
        return ProductEntity.toModelList(productRepository.findByPriceBetween(min, max));
    }

    @Override
    public Product createProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new IllegalArgumentException("Producto con id: " + product.getId() + " ya existe");
        } else {
            ProductEntity productEntity = ProductEntity.fromModel(product);
            productRepository.save(productEntity);
            return ProductEntity.toModel(productEntity);
        }
    }

    @Override
    public Product updateProduct(Product product) {
        ProductEntity existingProductEntity = productRepository.findById(product.getId()).orElseThrow(() ->
                new IllegalArgumentException("Producto con id: " + product.getId() + " no encontrado"));
        existingProductEntity.setName(product.getName());
        existingProductEntity.setCategory(product.getCategory());
        existingProductEntity.setPrice(product.getPrice());
        productRepository.save(existingProductEntity);
        return ProductEntity.toModel(existingProductEntity);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return ProductEntity.toModelList(productRepository.findByCategory(category));
    }
}
