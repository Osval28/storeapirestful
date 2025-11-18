package co.com.store.storeapirestful.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderSummary {

    private String orderId;
    private LocalDateTime date;
    private List<ItemSummary> items;
    private Double total;

    @Data
    @AllArgsConstructor
    public static class ItemSummary {
        private String productId;
        private String name;
        private Integer quantity;
        private Double unitPrice;
        private Double subtotal;
    }
}
