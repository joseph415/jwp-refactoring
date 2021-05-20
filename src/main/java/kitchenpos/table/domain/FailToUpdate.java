package kitchenpos.table.domain;

public class FailToUpdate extends RuntimeException {
    public FailToUpdate(String message) {
        super(message);
    }
}
