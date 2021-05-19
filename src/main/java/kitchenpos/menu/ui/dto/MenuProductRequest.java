package kitchenpos.menu.ui.dto;

import java.util.List;

public class MenuProductRequest {

    private List<Long> productId;

    private long quantity;

    private MenuProductRequest() {
    }

    public MenuProductRequest(List<Long> productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public List<Long> getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }
}
