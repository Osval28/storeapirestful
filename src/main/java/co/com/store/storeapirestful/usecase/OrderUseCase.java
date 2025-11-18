package co.com.store.storeapirestful.usecase;

import co.com.store.storeapirestful.controller.dto.OrderSummary;
import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.model.OrderStatus;
import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.model.ProductInCart;
import co.com.store.storeapirestful.service.repository.OrderRepository;
import co.com.store.storeapirestful.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderUseCase(@Qualifier("mySQLOrderService") OrderRepository orderRepository, @Qualifier("mySQLProductService") ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.getAllOrders();
    }

    public Order getOrderById(String id) {
        Order order = orderRepository.getOrderById(id);
        double total = calculateTotal(order);
        order.setTotal(total);
        orderRepository.updateOrder(order);
        return orderRepository.getOrderById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    public Order updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteOrder(id);
    }

    public Double calculateTotal(Order order) {
        Double total = 0.0;
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("No se puede calcular el total de un pedido vacío");
        }
        for (ProductInCart item : order.getItems()) {
            total += item.getTotalPrice();
        }
        if (total > 100000) {
            total = total * 0.95;
        }
        return total;
    }

    public Order addProductToCart(String orderId, String productId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity must be greater than zero");
        }
        Order order = orderRepository.getOrderById(orderId);

        for (ProductInCart productInCart : order.getItems()) {
            if (productInCart.getProduct().getId().equals(productId)) {
                productInCart.setQuantity(productInCart.getQuantity() + quantity);
                orderRepository.updateOrder(order);
                return order;
            }
        }
        Product product = productRepository.getProductById(productId);
        ProductInCart newProduct = new ProductInCart(product, quantity);
        order.getItems().add(newProduct);
        orderRepository.updateOrder(order);
        return order;
    }

    public Order updateItem(String orderId, String productId, Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The quantity must be greater than or equal to zero");
        }
        Order order = orderRepository.getOrderById(orderId);
        ProductInCart productInCart = order.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Producto con id: " + productId + " no fue encontrado en el pedido"));

        if (quantity == 0) {
            order.getItems().remove(productInCart);
        } else {
            productInCart.setQuantity(quantity);
        }

        orderRepository.updateOrder(order);
        return order;
    }

    public Order cancelOrder(String orderId) {
        Order order = orderRepository.getOrderById(orderId);
        if (order.getStatus() == OrderStatus.CLOSED) {
            throw new IllegalArgumentException("El pedido ya está cerrado y no se puede cancelar");
        }
        order.getItems().clear();
        order.setTotal(0.0);
        order.setStatus(OrderStatus.OPEN);
        orderRepository.updateOrder(order);
        return order;

    }

    public OrderSummary Checkout(String orderId) {
        Order order = orderRepository.getOrderById(orderId);
        if (order.getStatus() == OrderStatus.CLOSED) {
            throw new IllegalArgumentException("Este pedido ya fue cerrado");
        }

        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("No se puede finalizar un pedido vacío");
        }
        double total = calculateTotal(order);
        order.setTotal(total);
        order.setStatus(OrderStatus.CLOSED);
        orderRepository.updateOrder(order);
        List<OrderSummary.ItemSummary> itemSummaries = new ArrayList<>();

        for(ProductInCart product : order.getItems()){
            itemSummaries.add(new OrderSummary.ItemSummary(product.getProduct().getId(), product.getProduct().getName(), product.getQuantity(),
                    product.getProduct().getPrice(),
                    product.getTotalPrice()));
        }

        return new OrderSummary(order.getId(), LocalDateTime.now(),itemSummaries,order.getTotal());
    }


}