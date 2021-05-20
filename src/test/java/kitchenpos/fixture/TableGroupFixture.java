package kitchenpos.fixture;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.table.domain.tablegroup.OrderTableId;
import kitchenpos.table.domain.tablegroup.TableGroup;

public class TableGroupFixture {
    public static final List<OrderTableId>
            ORDER_TABLE_IDS1 = Arrays.asList(new OrderTableId(null, 1L),
            new OrderTableId(null, 2L));

    public static final List<OrderTableId>
            ORDER_TABLE_IDS2 = Arrays.asList(new OrderTableId(null, 3L),
            new OrderTableId(null, 4L));

    public static final TableGroup TABLE_GROUP1 = TestObjectUtils.createTableGroup(
            1L, LocalDateTime.now(), ORDER_TABLE_IDS1);

    public static final TableGroup TABLE_GROUP2 = TestObjectUtils.createTableGroup(
            2L, LocalDateTime.now(), ORDER_TABLE_IDS2);
}
