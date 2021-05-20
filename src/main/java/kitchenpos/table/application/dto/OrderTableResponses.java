package kitchenpos.table.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.table.domain.ordertable.OrderTable;

public class OrderTableResponses {
    private final List<OrderTableResponse> orderTableResponses;

    public OrderTableResponses(
            List<OrderTableResponse> orderTableResponses) {
        this.orderTableResponses = orderTableResponses;
    }

    public static OrderTableResponses from(List<OrderTable> orderTables) {
        return new OrderTableResponses(
                orderTables.stream()
                        .map(OrderTableResponse::from)
                        .collect(Collectors.toList())
        );
    }

    public List<OrderTableResponse> getOrderTableResponses() {
        return orderTableResponses;
    }
}
