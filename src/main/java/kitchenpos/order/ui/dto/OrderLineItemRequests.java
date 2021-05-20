package kitchenpos.order.ui.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class OrderLineItemRequests {

    @NotEmpty(message = "OrderLineItem 이 존재하지 않습니다.")
    private List<OrderLineItemRequest> orderLineItems;

    private OrderLineItemRequests() {
    }

    public OrderLineItemRequests(
            List<OrderLineItemRequest> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public List<OrderLineItemRequest> getOrderLineItems() {
        return orderLineItems;
    }
}
