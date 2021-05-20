package kitchenpos.order.integration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.common.integration.IntegrationTest;
import kitchenpos.menu.command.domain.menu.MenuProduct;
import kitchenpos.menu.command.domain.menu.MenuRepository;
import kitchenpos.menu.command.domain.menugroup.MenuGroupRepository;
import kitchenpos.order.application.OrderService;
import kitchenpos.order.application.dto.OrderResponse;
import kitchenpos.order.application.dto.OrderResponses;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.ui.dto.ChangeOrderStatusRequest;
import kitchenpos.order.ui.dto.OrderLineItemRequest;
import kitchenpos.order.ui.dto.OrderLineItemRequests;
import kitchenpos.order.ui.dto.OrderRequest;
import kitchenpos.product.command.domain.product.Product;
import kitchenpos.product.command.domain.product.ProductRepository;
import kitchenpos.table.domain.ordertable.OrderTableRepository;

class OrderServiceTest extends IntegrationTest {
    @Autowired
    private OrderTableRepository orderTableRepository;
    @Autowired
    private MenuGroupRepository menuGroupRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @DisplayName("1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.")
    @Test
    void createTest() {
        productRepository.saveAll(Arrays.asList(new Product("후라이드", BigDecimal.valueOf(16000)),
                new Product("양념", BigDecimal.valueOf(16000))));
        menuGroupRepository.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        menuRepository.save(
                TestObjectUtils.createMenu(null, "두마리치킨", BigDecimal.valueOf(16000), 1L,
                        Arrays.asList(new MenuProduct(null, 1L, 1L),
                                new MenuProduct(null, 2L, 1L))));
        orderTableRepository.save(TestObjectUtils.createOrderTable(null, null, 1, false));

        OrderRequest createOrder = new OrderRequest(1L, new OrderLineItemRequests(
                Collections.singletonList(new OrderLineItemRequest(1L, 1L))));

        final OrderResponse order = orderService.create(createOrder);

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
        productRepository.saveAll(Arrays.asList(new Product("후라이드", BigDecimal.valueOf(16000)),
                new Product("양념", BigDecimal.valueOf(16000))));
        menuGroupRepository.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        menuRepository.save(
                TestObjectUtils.createMenu(null, "두마리치킨", BigDecimal.valueOf(16000), 1L,
                        Arrays.asList(new MenuProduct(null, 1L, 1L),
                                new MenuProduct(null, 2L, 1L))));
        orderTableRepository.save(TestObjectUtils.createOrderTable(null, null, 1, false));

        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(null, 1L, 1L);
        Order createdOrder = TestObjectUtils.createOrder(
                null, 1L, OrderStatus.COOKING.name(), LocalDateTime.now(),
                Collections.singletonList(orderLineItem));

        orderRepository.save(createdOrder);

        final OrderResponses orders = orderService.list();

        assertThat(orders.getOrderResponses().size()).isEqualTo(1);
    }

    @DisplayName("주문 상태를 변경할 수 있다.")
    @Test
    void changeOrderStatusTest() {
        productRepository.saveAll(Arrays.asList(new Product("후라이드", BigDecimal.valueOf(16000)),
                new Product("양념", BigDecimal.valueOf(16000))));
        menuGroupRepository.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        menuRepository.save(
                TestObjectUtils.createMenu(null, "두마리치킨", BigDecimal.valueOf(16000), 1L,
                        Arrays.asList(new MenuProduct(null, 1L, 1L),
                                new MenuProduct(null, 2L, 1L))));
        orderTableRepository.save(TestObjectUtils.createOrderTable(null, null, 1, false));

        OrderLineItem orderLineItem = TestObjectUtils.createOrderLineItem(null, 1L, 1L);
        Order createdOrder = TestObjectUtils.createOrder(
                null, 1L, OrderStatus.COOKING.name(), LocalDateTime.now(),
                Collections.singletonList(orderLineItem));

        final Order order = orderRepository.save(createdOrder);

        final OrderResponse changedOrder = orderService.changeOrderStatus(order.getId(),
                new ChangeOrderStatusRequest(OrderStatus.MEAL.name()));

        assertThat(changedOrder.getOrderStatus()).isEqualTo(OrderStatus.MEAL.name());
    }
}