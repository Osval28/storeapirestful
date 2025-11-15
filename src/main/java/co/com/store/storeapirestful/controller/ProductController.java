package co.com.store.storeapirestful.controller;

import co.com.store.storeapirestful.usecase.ProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")

public class ProductController {

    private final ProductUseCase productUseCase;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productUseCase.getProducts());
    }













}
