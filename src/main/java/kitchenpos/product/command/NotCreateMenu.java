package kitchenpos.product.command;

public class NotCreateMenu extends RuntimeException {
    public NotCreateMenu(String message) {
        super(message);
    }
}
