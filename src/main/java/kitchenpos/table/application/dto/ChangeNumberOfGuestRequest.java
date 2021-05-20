package kitchenpos.table.application.dto;

import javax.validation.constraints.PositiveOrZero;

public class ChangeNumberOfGuestRequest {

    @PositiveOrZero(message = "인원수는 0명 이상입니다.")
    private int numberOfGuests;

    public ChangeNumberOfGuestRequest(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}
