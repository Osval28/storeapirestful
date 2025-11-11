package co.com.store.storeapirestful.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderEntity {

    @Id
    private String id;
    private String customerId;
    private ArrayList<ProductInCartEntity> items = new ArrayList<>();
    private Double total = 0.0;




}
