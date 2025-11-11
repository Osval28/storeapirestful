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

    @NotNull(message = "Se requiere el id del producto")
    private String id;

    @NotNull(message = "Se requiere el nombre del producto")
    private String name;

    @NotNull(message = "Se requiere la categoria del producto")
    private String category;


    @NotNull(message = "Se requiere el precio del producto")
    @Min(value = 0, message = "El precio del producto no puede ser negativo")
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
