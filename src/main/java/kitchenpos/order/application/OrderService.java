package kitchenpos.order.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.command.domain.menu.MenuRepository;
import kitchenpos.order.application.dto.OrderResponse;
import kitchenpos.order.application.dto.OrderResponses;
import kitchenpos.order.domain.MenuPolicyDomainService;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.ui.dto.ChangeOrderStatusRequest;
import kitchenpos.order.ui.dto.OrderRequest;
import kitchenpos.table.domain.ordertable.OrderTableRepository;

@Service
public class OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final OrderTableRepository orderTableRepository;
    private final MenuPolicyDomainService menuPolicyDomainService;

    public OrderService(MenuRepository menuRepository,
            OrderRepository orderRepository,
            OrderTableRepository orderTableRepository,
            MenuPolicyDomainService menuPolicyDomainService) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.orderTableRepository = orderTableRepository;
        this.menuPolicyDomainService = menuPolicyDomainService;
    }

    @Transactional
    public OrderResponse create(final OrderRequest orderRequest) {
        menuPolicyDomainService.verifyCreateOrder(menuRepository, orderTableRepository,
                orderRequest);

        final Order order = orderRepository.save(Order.from(orderRequest));

        return OrderResponse.from(order);
    }

    @Transactional
    public OrderResponse changeOrderStatus(final Long orderId,
            final ChangeOrderStatusRequest changeOrderStatusRequest) {

        final Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);

        order.changeOrderStatus(changeOrderStatusRequest.getOrderStatus());

        return OrderResponse.from(order);
    }

    @Transactional(readOnly = true)
    public OrderResponses list() {
        final List<Order> orders = orderRepository.findAll();

        return OrderResponses.from(orders);
    }
}
