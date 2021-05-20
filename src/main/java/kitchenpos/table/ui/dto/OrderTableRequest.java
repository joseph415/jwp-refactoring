package kitchenpos.table.ui.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class OrderTableRequest {

    @PositiveOrZero(message = "인원수가 정해지지 않았습니다.")
    private int numberOfGuests;

    @NotNull(message = "테이블 상태를 선택해주세요")
    private Boolean empty;

    private OrderTableRequest() {
    }

    public OrderTableRequest(int numberOfGuests, Boolean empty) {
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public Boolean getEmpty() {
        return empty;
    }
}
