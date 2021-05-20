package kitchenpos.order.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kitchenpos.order.ui.dto.OrderRequest;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_table_id")
    private Long orderTableId;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "ordered_time", nullable = false)
    private LocalDateTime orderedTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderLineItem> orderLineItems;

    protected Order() {
    }

    public Order(Long id, Long orderTableId, String orderStatus, LocalDateTime orderedTime,
            List<OrderLineItem> orderLineItems) {
        this.id = id;
        this.orderTableId = orderTableId;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
        this.orderLineItems = orderLineItems;
    }

    public static Order from(OrderRequest orderRequest) {
        final List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItems()
                .stream()
                .map(orderLineItemRequest -> new OrderLineItem(null,
                        orderLineItemRequest.getMenuId(), orderLineItemRequest.getQuantity()))
                .collect(Collectors.toList());

        return new Order(null,
                orderRequest.getOrderTableId(),
                OrderStatus.COOKING.name(),
                LocalDateTime.now(), orderLineItems);
    }

    public void changeOrderStatus(String orderStatus) {
        if (Objects.equals(OrderStatus.COMPLETION.name(), this.orderStatus)) {
            throw new NotUpdateOrderStatus("주문이 완료된 상태입니다.");
        }

        this.orderStatus = OrderStatus.valueOf(orderStatus).name();
    }

    public Long getId() {
        return id;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }
}
