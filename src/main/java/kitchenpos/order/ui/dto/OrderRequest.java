package kitchenpos.order.ui.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrderRequest {

    @NotNull
    private Long orderTableId;

    @NotEmpty(message = "OrderLineItem 이 존재하지 않습니다.")
    private List<OrderLineItemRequest> orderLineItems;

    private OrderRequest() {
    }

    public OrderRequest(Long orderTableId,
            List<OrderLineItemRequest> orderLineItems) {
        this.orderTableId = orderTableId;
        this.orderLineItems = orderLineItems;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public List<OrderLineItemRequest> getOrderLineItems() {
        return orderLineItems;
    }
}
