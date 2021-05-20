package kitchenpos.fixture;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.order.domain.OrderLineItem;

public class OrderLineItemFixture {
    public static final OrderLineItem ORDER_LINE_ITEM1 = TestObjectUtils.createOrderLineItem(
            1L, 1L, 1
    );

    public static final OrderLineItem ORDER_LINE_ITEM2 = TestObjectUtils.createOrderLineItem(
            2L, 1L, 1
    );

    public static final OrderLineItem ORDER_LINE_ITEM3 = TestObjectUtils.createOrderLineItem(
            3L, 1L, 1
    );
}
