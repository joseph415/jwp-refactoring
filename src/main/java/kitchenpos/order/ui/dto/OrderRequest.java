package kitchenpos.order.ui.dto;

import javax.validation.constraints.NotNull;

public class OrderRequest {

    @NotNull
    private Long orderTableId;
    @NotNull
    private OrderLineItemRequests orderLineItemRequests;

    private OrderRequest() {
    }

    public OrderRequest(Long orderTableId,
            OrderLineItemRequests orderLineItemRequests) {
        this.orderTableId = orderTableId;
        this.orderLineItemRequests = orderLineItemRequests;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public OrderLineItemRequests getOrderLineItemRequests() {
        return orderLineItemRequests;
    }
}
