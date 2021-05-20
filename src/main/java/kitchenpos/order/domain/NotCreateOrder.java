package kitchenpos.order.domain;

public class NotCreateOrder extends RuntimeException {
    public NotCreateOrder(String message) {
        super(message);
    }
}
