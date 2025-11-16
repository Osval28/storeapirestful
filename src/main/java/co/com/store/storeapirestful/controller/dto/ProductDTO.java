package co.com.store.storeapirestful.controller.dto;

import co.com.store.storeapirestful.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class ProductDTO {

    @NotNull(message = "the id is required")
    private String id;

    @NotNull(message = "the name is required")
    private String name;

    @NotNull(message = "the category is required")
    private String category;


    @NotNull(message = "the price is required")
    @Min(value = 0, message = "the price must be positive")
    private double price;

    public static ProductDTO fromModel(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getPrice());
    }

    public static Product toModel(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getCategory(), productDTO.getPrice());
    }

    public static List<ProductDTO> fromModelList(List<Product> products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(fromModel(product));
        }
        return productDTOs;
    }



}
