package co.com.store.storeapirestful.controller.dto;

import co.com.store.storeapirestful.model.Order;
import co.com.store.storeapirestful.model.OrderStatus;
import co.com.store.storeapirestful.model.ProductInCart;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class OrderDTO {

    private String id;
    private String customerId;

    @NotNull(message = "the items are required")
    private ArrayList <ProductInCartDTO> items = new ArrayList<>();
    private Double total;

    private OrderStatus status = OrderStatus.OPEN;

    public static OrderDTO fromModel(Order order) {
        List<ProductInCartDTO> itemDTOs = new ArrayList<>();
        for (ProductInCart item : order.getItems()) {
            itemDTOs.add(ProductInCartDTO.fromModel(item));
        }
        return new OrderDTO(order.getId(), order.getCustomerId(), new ArrayList<>(itemDTOs), order.getTotal(),OrderStatus.OPEN);
    }
    public static Order toModel(OrderDTO orderDTO) {
        List<ProductInCart> items = new ArrayList<>();
        for (ProductInCartDTO itemDTO : orderDTO.getItems()) {
            items.add(ProductInCartDTO.toModel(itemDTO));
        }
        return new Order(orderDTO.getId(), orderDTO.getCustomerId(), new ArrayList<>(items), orderDTO.getTotal(),OrderStatus.OPEN);
    }

    public static List<OrderDTO> fromModelList(List<Order> orders) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(fromModel(order));
        }
        return orderDTOs;
    }



}