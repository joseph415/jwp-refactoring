package kitchenpos.table.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.domain.ordertable.OrderTable;

@Component
public class ValidationDomainService {

    public void checkOrderTable(OrderRepository orderRepository, OrderTable orderTable) {
        if (Objects.nonNull(orderTable.getTableGroupId())) {
            throw new IllegalArgumentException();
        }

        // TODO: 2021/05/20 도메인 상태를 확인하는것 -> 비지니스 영역
        if (orderRepository.existsByOrderTableIdAndOrderStatusIn(
                orderTable.getTableGroupId(),
                Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))) {
            throw new CheckOrderTableException("주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.");
        }
    }

    public void checkOrderTable(OrderRepository orderRepository, List<OrderTable> orderTables) {
        if (CollectionUtils.isEmpty(orderTables)) {
            throw new IllegalArgumentException("orderTable 이 존재하지 않습니다.");
        }

        final List<Long> orderTableIds = orderTables.stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());

        if (orderRepository.existsByOrderTableIdInAndOrderStatusIn(
                orderTableIds,
                Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))) {
            throw new CheckOrderTableException("주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.");
        }
    }
}
