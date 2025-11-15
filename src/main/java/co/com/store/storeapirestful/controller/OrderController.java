package co.com.store.storeapirestful.controller;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.usecase.OrderUseCase;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderUseCase orderUseCase;

    @GetMapping
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(orderUseCase.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderUseCase.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderUseCase.createOrder(order));
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderUseCase.updateOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(String id) {
        orderUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }




}
