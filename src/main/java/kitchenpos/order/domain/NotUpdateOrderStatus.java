package kitchenpos.order.domain;

public class NotUpdateOrderStatus extends RuntimeException {
    public NotUpdateOrderStatus(String message) {
        super(message);
    }
}
