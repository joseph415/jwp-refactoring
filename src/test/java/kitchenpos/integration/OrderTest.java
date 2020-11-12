package kitchenpos.integration;

import static kitchenpos.fixture.OrderTableFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import kitchenpos.application.OrderService;
import kitchenpos.application.TableService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.OrderStatus;

class OrderTest extends IntegrationTest {
    @Autowired
    private TableService tableService;
    @Autowired
    private OrderService orderService;

    @DisplayName("1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.")
    @Test
    void createTest() throws Exception {
        tableService.changeEmpty(1L, CHANGING_NOT_EMPTY_ORDER_TABLE);
        tableService.changeNumberOfGuests(1L, CHANGING_GUEST_ORDER_TABLE);

        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(1L, null, 1L, 1L);
        kitchenpos.domain.Order createdOrder = TestObjectUtils.createOrder(
                null, 1L, null, null, Collections.singletonList(orderLineItem));
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void listTest() throws Exception {
        tableService.changeEmpty(1L, CHANGING_NOT_EMPTY_ORDER_TABLE);
        tableService.changeNumberOfGuests(1L, CHANGING_GUEST_ORDER_TABLE);
        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(1L, null, 1L, 1L);
        orderService.create(TestObjectUtils.createOrder(
                null, 1L, null, null, Collections.singletonList(orderLineItem)));

    }

    @DisplayName("주문 상태를 변경할 수 있다.")
    @Test
    void changeOrderStatusTest() throws Exception {
        tableService.changeEmpty(1L, CHANGING_NOT_EMPTY_ORDER_TABLE);
        tableService.changeNumberOfGuests(1L, CHANGING_GUEST_ORDER_TABLE);
        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(1L, null, 1L, 1L);
        kitchenpos.domain.Order order = TestObjectUtils.createOrder(
                null, 1L, null, null, Collections.singletonList(orderLineItem));
        kitchenpos.domain.Order changedOrder = orderService.create(order);
        kitchenpos.domain.Order changingOrder = TestObjectUtils.createOrder(null, null, OrderStatus.MEAL.name(), null,
                null);
    }
}