package kitchenpos.table.application;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.order.domain.OrderRepository;
import kitchenpos.table.application.dto.TableGroupRequest;
import kitchenpos.table.application.dto.TableGroupResponse;
import kitchenpos.table.domain.ValidationDomainService;
import kitchenpos.table.domain.ordertable.OrderTable;
import kitchenpos.table.domain.ordertable.OrderTableRepository;
import kitchenpos.table.domain.tablegroup.TableGroup;
import kitchenpos.table.domain.tablegroup.TableGroupRepository;

@Service
public class TableGroupService {
    private final OrderRepository orderRepository;
    private final OrderTableRepository orderTableRepository;
    private final TableGroupRepository tableGroupRepository;
    private final ValidationDomainService validationDomainService;

    public TableGroupService(OrderRepository orderRepository,
            OrderTableRepository orderTableRepository,
            TableGroupRepository tableGroupRepository,
            ValidationDomainService validationDomainService) {
        this.orderRepository = orderRepository;
        this.orderTableRepository = orderTableRepository;
        this.tableGroupRepository = tableGroupRepository;
        this.validationDomainService = validationDomainService;
    }

    @Transactional
    public TableGroupResponse create(final TableGroupRequest tableGroupRequest) {
        final List<Long> orderTableIds = tableGroupRequest.getOrderTables();
        final List<OrderTable> orderTables = orderTableRepository.findAllByIdIn(orderTableIds);

        checkOrderTableSize(orderTableIds, orderTables);
        orderTables.forEach(this::checkOrderTableGroup);

        final TableGroup tableGroup = tableGroupRepository.save(
                TableGroup.from(orderTableIds));
        final Long tableGroupId = tableGroup.getId();

        orderTables.forEach(orderTable -> orderTable.updateTableGroup(tableGroupId, false));

        return TableGroupResponse.from(tableGroup);
    }

    private void checkOrderTableSize(List<Long> orderTableIds, List<OrderTable> orderTables) {
        if (orderTableIds.size() != orderTables.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void checkOrderTableGroup(OrderTable orderTable) {
        if (!orderTable.isEmpty() || Objects.nonNull(orderTable.getTableGroupId())) {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        final List<OrderTable> orderTables = orderTableRepository.findAllByTableGroupId(
                tableGroupId);

        validationDomainService.checkOrderTables(orderRepository, orderTables);
        orderTables.forEach(orderTable -> orderTable.updateTableGroup(null, false));

        tableGroupRepository.deleteById(tableGroupId);
    }
}
