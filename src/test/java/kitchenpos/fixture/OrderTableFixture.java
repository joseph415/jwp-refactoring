package kitchenpos.fixture;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.table.domain.ordertable.OrderTable;

public class OrderTableFixture {

    public static final OrderTable ORDER_TABLE1 =
            TestObjectUtils.createOrderTable(1L, null, 0, true);

    public static final OrderTable ORDER_TABLE2 =
            TestObjectUtils.createOrderTable(2L, null, 0, true);

    public static final OrderTable ORDER_TABLE3 =
            TestObjectUtils.createOrderTable(3L, 1L, 3, false);

    public static final OrderTable ORDER_TABLE4 =
            TestObjectUtils.createOrderTable(4L, 1L, 3, false);

    public static final OrderTable ORDER_TABLE5 =
            TestObjectUtils.createOrderTable(1L, null, 3, false);

    public static final OrderTable CHANGING_GUEST_ORDER_TABLE =
            TestObjectUtils.createOrderTable(3L, 1L, 5, false);
    
}
