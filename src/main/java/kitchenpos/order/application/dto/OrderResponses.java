package kitchenpos.order.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.order.domain.Order;

public class OrderResponses {
    private final List<OrderResponse> orderResponses;

    public OrderResponses(List<OrderResponse> orderResponses) {
        this.orderResponses = orderResponses;
    }

    public static OrderResponses from(List<Order> orders) {
        final List<OrderResponse> orderResponses = orders.stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());

        return new OrderResponses(orderResponses);
    }

    public List<OrderResponse> getOrderResponses() {
        return orderResponses;
    }
}
