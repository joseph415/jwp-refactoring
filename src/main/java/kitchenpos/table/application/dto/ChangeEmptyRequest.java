package kitchenpos.table.application.dto;

import javax.validation.constraints.NotNull;

public class ChangeEmptyRequest {

    @NotNull(message = "좌석상태를 입력해주세요")
    private Boolean empty;

    private ChangeEmptyRequest() {
    }

    public ChangeEmptyRequest(Boolean empty) {
        this.empty = empty;
    }

    public Boolean getEmpty() {
        return empty;
    }
}
