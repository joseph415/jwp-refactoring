package kitchenpos.table.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.order.domain.OrderDao;
import kitchenpos.table.application.dto.ChangeEmptyRequest;
import kitchenpos.table.application.dto.ChangeNumberOfGuestRequest;
import kitchenpos.table.application.dto.OrderTableResponse;
import kitchenpos.table.application.dto.OrderTableResponses;
import kitchenpos.table.domain.ValidationDomainService;
import kitchenpos.table.domain.ordertable.OrderTable;
import kitchenpos.table.domain.ordertable.OrderTableRepository;
import kitchenpos.table.ui.dto.OrderTableRequest;

@Service
public class TableService {

    private final OrderDao orderDao;
    private final OrderTableRepository orderTableRepository;
    private final ValidationDomainService validationDomainService;

    public TableService(OrderDao orderDao,
            OrderTableRepository orderTableRepository,
            ValidationDomainService validationDomainService) {
        this.orderDao = orderDao;
        this.orderTableRepository = orderTableRepository;
        this.validationDomainService = validationDomainService;
    }

    @Transactional
    public OrderTableResponse create(final OrderTableRequest orderTableRequest) {
        final OrderTable orderTable = orderTableRepository.save(OrderTable.from(orderTableRequest));

        return OrderTableResponse.from(orderTable);
    }

    @Transactional
    public void changeNumberOfGuests(final Long orderTableId,
            final ChangeNumberOfGuestRequest changeNumberOfGuestRequest) {
        final OrderTable savedOrderTable = orderTableRepository.findById(orderTableId)
                .orElseThrow(IllegalArgumentException::new);
        final int numberOfGuests = changeNumberOfGuestRequest.getNumberOfGuests();

        savedOrderTable.updateNumberOfGuests(numberOfGuests);
    }

    @Transactional
    public void changeEmpty(final Long orderTableId,
            final ChangeEmptyRequest changeEmptyRequest) {
        final OrderTable orderTable = orderTableRepository.findById(orderTableId)
                .orElseThrow(IllegalArgumentException::new);
        validationDomainService.checkOrderTable(orderDao, orderTable);

        orderTable.updateEmpty(changeEmptyRequest.getEmpty());
    }

    public OrderTableResponses list() {
        return OrderTableResponses.from(orderTableRepository.findAll());
    }

}
