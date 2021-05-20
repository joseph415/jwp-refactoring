package kitchenpos.table.domain.ordertable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import kitchenpos.table.domain.FailToUpdate;
import kitchenpos.table.ui.dto.OrderTableRequest;

@Entity
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_table_id")
    private Long id;

    @Column(name = "table_group_id")
    private Long tableGroupId;

    @Column(name = "number_of_guests")
    private int numberOfGuests;

    private boolean empty;

    protected OrderTable() {
    }

    public OrderTable(Long id, Long tableGroupId, int numberOfGuests, boolean empty) {
        this.id = id;
        this.tableGroupId = tableGroupId;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public static OrderTable from(OrderTableRequest orderTableRequest) {
        return new OrderTable(null, null, orderTableRequest.getNumberOfGuests(),
                orderTableRequest.getEmpty());
    }

    public void updateNumberOfGuests(int numberOfGuests) {
        if (this.empty) {
            throw new FailToUpdate("빈 테이블은 게스트 명수를 변경 할 수 없습니다.");
        }
        this.numberOfGuests = numberOfGuests;
    }

    public void updateTableGroup(Long tableGroupId, boolean empty) {
        this.tableGroupId = tableGroupId;
        this.empty = empty;
    }

    public void updateEmpty(Boolean empty) {
        this.empty = empty;
    }

    public Long getId() {
        return id;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }
}
