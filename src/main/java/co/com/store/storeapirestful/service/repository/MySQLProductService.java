package co.com.store.storeapirestful.service.repository;

import co.com.store.storeapirestful.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mySQLProductService")

public class MySQLProductService implements ProductRepository{


    private List<Product> products = new ArrayList<>();


    @Override
    public List<Product> getAllProducts() {
        return this.products;
    }

    @Override
    public Product getProductById(String id) {
        return this.products.stream().filter(product -> product.getId().equals(id)).findFirst().orElseThrow(()-> new
                IllegalArgumentException("Producto con id: " + id + " no encontrado"));
    }

    @Override
    public List<Product> getProductsBetween(Double min, Double max) {
        return this.products.stream().filter(product -> product.getPrice() >= min && product.getPrice() <= max).toList();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return this.products.stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).toList();
    }

    @Override
    public Product createProduct(Product product) {
        if (products.stream().anyMatch(product1 -> product1.getId().equals(product.getId()))) {
            throw new IllegalArgumentException("El producto con id: " + product.getId() + " ya existe");
        } else {
            products.add(product);
            return product;
        }
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        return existingProduct;
    }

    @Override
    public void deleteProduct(String id) {
        Product productToDelete = getProductById(id);
        this.products.remove(productToDelete);
    }

}
