package kitchenpos.order.ui.dto;

import javax.validation.constraints.NotBlank;

public class ChangeOrderStatusRequest {

    @NotBlank(message = "주문 상태를 입력하세요")
    private String orderStatus;

    private ChangeOrderStatusRequest() {
    }

    public ChangeOrderStatusRequest(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
