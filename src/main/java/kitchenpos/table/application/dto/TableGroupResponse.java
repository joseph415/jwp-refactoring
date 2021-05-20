package kitchenpos.table.application.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import kitchenpos.table.domain.tablegroup.OrderTableId;
import kitchenpos.table.domain.tablegroup.TableGroup;

public class TableGroupResponse {

    private final Long id;
    private final String createdDate;
    private final List<OrderTableId> orderTableIds;

    public TableGroupResponse(Long id, String createdDate,
            List<OrderTableId> orderTableIds) {
        this.id = id;
        this.createdDate = createdDate;
        this.orderTableIds = orderTableIds;
    }

    public static TableGroupResponse from(TableGroup tableGroup) {
        return new TableGroupResponse(tableGroup.getId(),
                tableGroup.getCreatedDate().format(DateTimeFormatter.ISO_DATE_TIME),
                tableGroup.getOrderTableIds());
    }

    public Long getId() {
        return id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public List<OrderTableId> getOrderTableIds() {
        return orderTableIds;
    }
}
