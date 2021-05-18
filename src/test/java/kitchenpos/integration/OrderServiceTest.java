package kitchenpos.integration;

import kitchenpos.application.OrderService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.dao.OrderDao;
import kitchenpos.dao.OrderTableDao;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.OrderStatus;
import kitchenpos.fixture.OrderFixture;
import kitchenpos.menu.command.domain.menu.Menu;
import kitchenpos.menu.command.domain.menu.MenuDao;
import kitchenpos.menu.command.domain.menuproduct.MenuProduct;
import kitchenpos.menugroup.domain.MenuGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderServiceTest extends IntegrationTest {
    @Autowired
    private OrderTableDao orderTableDao;
    @Autowired
    private MenuGroupRepository menuGroupRepository;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    @DisplayName("1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.")
    @Test
    void createTest() {
        final MenuProduct friedChicken =
                TestObjectUtils.createMenuProduct(1L, null, 1L, 1L);
        final MenuProduct seasoningChicken =
                TestObjectUtils.createMenuProduct(2L, null, 2L, 1L);
        final Menu menu = TestObjectUtils.createMenu(null, "두마리치킨", BigDecimal.valueOf(16000),
                1L, Arrays.asList(friedChicken, seasoningChicken));
        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(1L, null, 1L, 1L);

        menuGroupRepository.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        menuDao.save(menu);
        orderTableDao.save(TestObjectUtils.createOrderTable(null, null, 1, false));

        Order createdOrder = TestObjectUtils.createOrder(
                null, 1L, null, null, Collections.singletonList(orderLineItem));

        final Order order = orderService.create(createdOrder);

        assertAll(
                () -> assertThat(order.getId()).isNotNull(),
                () -> assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.COOKING.name()),
                () -> assertThat(order.getOrderedTime()).isNotNull(),
                () -> assertThat(order.getOrderLineItems().size()).isEqualTo(1)
        );
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void listTest() {
        final MenuProduct friedChicken =
                TestObjectUtils.createMenuProduct(1L, null, 1L, 1L);
        final MenuProduct seasoningChicken =
                TestObjectUtils.createMenuProduct(2L, null, 2L, 1L);
        final Menu menu = TestObjectUtils.createMenu(null, "두마리치킨", BigDecimal.valueOf(16000),
                1L, Arrays.asList(friedChicken, seasoningChicken));
        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(1L, null, 1L, 1L);
        Order createdOrder = TestObjectUtils.createOrder(
                null, 1L, OrderStatus.COOKING.name(), LocalDateTime.now(),
                Collections.singletonList(orderLineItem));

        menuGroupRepository.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        menuDao.save(menu);
        orderTableDao.save(TestObjectUtils.createOrderTable(null, null, 1, false));
        orderDao.save(createdOrder);

        final List<Order> orders = orderService.list();

        assertThat(orders.size()).isEqualTo(1);

    }

    @DisplayName("주문 상태를 변경할 수 있다.")
    @Test
    void changeOrderStatusTest() {
        final MenuProduct friedChicken =
                TestObjectUtils.createMenuProduct(1L, null, 1L, 1L);
        final MenuProduct seasoningChicken =
                TestObjectUtils.createMenuProduct(2L, null, 2L, 1L);
        final Menu menu = TestObjectUtils.createMenu(null, "두마리치킨", BigDecimal.valueOf(16000),
                1L, Arrays.asList(friedChicken, seasoningChicken));
        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(1L, null, 1L, 1L);
        Order createdOrder = TestObjectUtils.createOrder(
                null, 1L, OrderStatus.COOKING.name(), LocalDateTime.now(),
                Collections.singletonList(orderLineItem));

        menuGroupRepository.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        menuDao.save(menu);
        orderTableDao.save(TestObjectUtils.createOrderTable(null, null, 1, false));
        final Order order = orderDao.save(createdOrder);

        final Order changedOrder = orderService.changeOrderStatus(order.getId(),
                OrderFixture.CHANGING_MEAL_ORDER);

        assertThat(changedOrder.getOrderStatus()).isEqualTo(OrderStatus.MEAL.name());
    }
}