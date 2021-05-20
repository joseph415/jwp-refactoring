package kitchenpos.order.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import kitchenpos.menu.command.domain.menu.MenuRepository;
import kitchenpos.order.ui.dto.OrderLineItemRequest;
import kitchenpos.order.ui.dto.OrderRequest;
import kitchenpos.table.domain.ordertable.OrderTable;
import kitchenpos.table.domain.ordertable.OrderTableRepository;

@Component
public class MenuPolicyDomainService {
    public void verifyCreateOrder(final MenuRepository menuRepository,
            final OrderTableRepository orderTableRepository, final OrderRequest orderRequest) {

        final List<Long> menuIds = orderRequest.getOrderLineItemRequests()
                .getOrderLineItems()
                .stream()
                .map(OrderLineItemRequest::getMenuId)
                .collect(Collectors.toList());

        if (menuIds.size() != menuRepository.countByIdIn(menuIds)) {
            throw new NotCreateOrder("등록되지 않은 메뉴입니다.");
        }

        final OrderTable orderTable = orderTableRepository.findById(orderRequest.getOrderTableId())
                .orElseThrow(IllegalArgumentException::new);

        if (orderTable.isEmpty()) {
            throw new NotCreateOrder("orderTable 이 현재 주문불가 상태입니다.");
        }
    }
}
