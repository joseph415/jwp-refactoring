package kitchenpos.table.application.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TableGroupRequest {

    @NotEmpty(message = "하나 이상의 orderTable을 입력해주세요")
    @Size(min = 2)
    private List<Long> orderTables;

    private TableGroupRequest() {
    }

    public TableGroupRequest(List<Long> orderTables) {
        this.orderTables = orderTables;
    }

    public List<Long> getOrderTables() {
        return orderTables;
    }
}
