package kitchenpos.order.application;

import static kitchenpos.fixture.OrderFixture.*;
import static kitchenpos.fixture.OrderTableFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.menu.command.domain.menu.MenuRepository;
import kitchenpos.order.application.dto.OrderResponse;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.ui.dto.ChangeOrderStatusRequest;
import kitchenpos.order.ui.dto.OrderLineItemRequest;
import kitchenpos.order.ui.dto.OrderLineItemRequests;
import kitchenpos.order.ui.dto.OrderRequest;
import kitchenpos.table.domain.ordertable.OrderTableRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderTableRepository orderTableRepository;

    @InjectMocks
    private OrderService orderService;

    @DisplayName("1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.")
    @Test
    void createTest() {
        OrderRequest createOrder = new OrderRequest(5L, new OrderLineItemRequests(
                Collections.singletonList(new OrderLineItemRequest(1L, 3L))));

        when(menuRepository.countByIdIn(anyList())).thenReturn(1L);
        when(orderTableRepository.findById(anyLong())).thenReturn(Optional.of(ORDER_TABLE5));
        when(orderRepository.save(any())).thenReturn(ORDER1);

        OrderResponse savedOrder = orderService.create(createOrder);
        assertAll(
                () -> assertThat(savedOrder.getId()).isEqualTo(1L),
                () -> assertThat(savedOrder.getOrderStatus()).isEqualTo(OrderStatus.COOKING.name()),
                () -> assertThat(savedOrder.getOrderTableId()).isEqualTo(5L),
                () -> assertThat(savedOrder.getOrderLineItems().get(0).getSeq()).isEqualTo(1L),
                () -> assertThat(savedOrder.getOrderLineItems().get(0).getMenuId()).isEqualTo(1L),
                () -> assertThat(savedOrder.getOrderLineItems().get(0).getQuantity()).isEqualTo(1L)
        );
    }

    @DisplayName("빈 테이블에는 주문을 등록할 수 없다")
    @Test
    void notCreateTest_when_emptyTable() {
        OrderRequest createOrder = new OrderRequest(5L, new OrderLineItemRequests(
                Collections.singletonList(new OrderLineItemRequest(1L, 3L))));

        when(menuRepository.countByIdIn(anyList())).thenReturn(1L);
        when(orderTableRepository.findById(anyLong())).thenReturn(Optional.of(ORDER_TABLE1));

        assertThatThrownBy(() -> orderService.create(createOrder))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void listTest() {
        when(orderRepository.findAll()).thenReturn(ORDERS);

        assertThat(orderService.list().getOrderResponses().size()).isEqualTo(1);
    }

    @DisplayName("주문 상태를 변경할 수 있다.")
    @Test
    void changeOrderStatusTest() {
        ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest(
                OrderStatus.MEAL.name());

        when(orderRepository.findById(any())).thenReturn(Optional.of(ORDER3));

        OrderResponse savedOrder = orderService.changeOrderStatus(3L, changeOrderStatusRequest);

        assertThat(savedOrder.getOrderStatus()).isEqualTo(OrderStatus.MEAL.name());
    }

    @DisplayName("주문 상태가 계산 완료인 경우 변경할 수 없다.")
    @Test
    void notChangeOrderStatusTest_when_OrderStatusIsComplication() {
        ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest(
                OrderStatus.MEAL.name());

        when(orderRepository.findById(any())).thenReturn(Optional.of(ORDER2));

        assertThatThrownBy(() -> orderService.changeOrderStatus(2L, changeOrderStatusRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }
}