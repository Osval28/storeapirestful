package co.com.store.storeapirestful.controller;

import co.com.store.storeapirestful.controller.dto.ProductDTO;
import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.usecase.ProductUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        ProductDTO product = ProductDTO.fromModel(productUseCase.getProductById(id));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getProductsByCategory(@RequestParam String category) {
        List<ProductDTO> products = ProductDTO.fromModelList(productUseCase.getProductsByCategory(category));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/range")
    public ResponseEntity<?> getProductsBetween(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productUseCase.getProductsBetween(min, max));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productUseCase.createProduct(ProductDTO.toModel(productDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productUseCase.updateProduct(ProductDTO.toModel(productDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}


