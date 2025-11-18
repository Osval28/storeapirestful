package co.com.store.storeapirestful.usecase;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.model.Product;
import co.com.store.storeapirestful.model.ProductInCart;
import co.com.store.storeapirestful.service.repository.OrderRepository;
import co.com.store.storeapirestful.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
        if (order.getItems().isEmpty()){
            throw new IllegalArgumentException("No se puede calcular el total de un pedido vacío");
        }
        for (ProductInCart item : order.getItems()) {
            total += item.getTotalPrice();
        }
        if (total > 100000) {
            total = total*0.95;
        }
        return total;
    }

    public Order addProductToCart(String orderId, String productId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity must be greater than zero");
        }
        Order order = orderRepository.getOrderById(orderId);

        for (ProductInCart productInCart : order.getItems()){
            if(productInCart.getProduct().getId().equals(productId)){
                productInCart.setQuantity(productInCart.getQuantity() + quantity);
                orderRepository.updateOrder(order);
                return order;
            }
        }
        Product product = productRepository.getProductById(productId);
        ProductInCart newProduct = new ProductInCart(product,quantity);
        order.getItems().add(newProduct);
        orderRepository.updateOrder(order);
        return order;

    }




}
