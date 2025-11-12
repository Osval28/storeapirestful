package co.com.store.storeapirestful.controller;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.usecase.OrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(orderUseCase.getOrders());
    }

    public ResponseEntity<?> getOrderById(String id) {
        return ResponseEntity.ok(orderUseCase.getOrderById(id));
    }

    public ResponseEntity<?> createOrder(Order order) {
        return ResponseEntity.ok(orderUseCase.createOrder(order));
    }

    public ResponseEntity<?> updateOrder(Order order) {
        return ResponseEntity.ok(orderUseCase.updateOrder(order));
    }

    public ResponseEntity<?> deleteOrder(String id) {
        orderUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }




}
