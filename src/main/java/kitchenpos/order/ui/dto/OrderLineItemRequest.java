package kitchenpos.order.ui.dto;

import javax.validation.constraints.NotNull;

public class OrderLineItemRequest {

    @NotNull
    private Long menuId;
    @NotNull
    private Long quantity;

    private OrderLineItemRequest() {
    }

    public OrderLineItemRequest(Long menuId, Long quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public Long getMenuId() {
        return menuId;
    }

    public Long getQuantity() {
        return quantity;
    }
}
