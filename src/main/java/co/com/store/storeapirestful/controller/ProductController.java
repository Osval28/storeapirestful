package co.com.store.storeapirestful.controller;

import co.com.store.storeapirestful.controller.dto.ProductDTO;
import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.usecase.ProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")

public class ProductController {

    private final ProductUseCase productUseCase;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<ProductDTO> products = ProductDTO.fromModelList(productUseCase.getProducts());
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<?> getProductById(String id) {
        ProductDTO product = ProductDTO.fromModel(productUseCase.getProductById(id));
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<?> getProductsBetween(Double min, Double max) {
        return ResponseEntity.ok(productUseCase.getProductsBetween(min,max));
    }

    public ResponseEntity<?> createProduct(ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productUseCase.createProduct(ProductDTO.toModel(productDTO)));
    }

    public ResponseEntity<?> updateProduct(Product product) {
        return ResponseEntity.ok(productUseCase.updateProduct(product));
    }

    public ResponseEntity<?> deleteProduct(String id) {
        productUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }










}
