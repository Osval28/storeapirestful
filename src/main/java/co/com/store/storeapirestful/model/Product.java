package co.com.store.storeapirestful.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Product {
    private String id;
    private String name;
    private String category;
    private double price;


}
