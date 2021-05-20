package kitchenpos.table.integration;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.common.integration.IntegrationTest;
import kitchenpos.table.application.TableService;
import kitchenpos.table.application.dto.ChangeEmptyRequest;
import kitchenpos.table.application.dto.ChangeNumberOfGuestRequest;
import kitchenpos.table.application.dto.OrderTableResponse;
import kitchenpos.table.application.dto.OrderTableResponses;
import kitchenpos.table.domain.ordertable.OrderTable;
import kitchenpos.table.domain.ordertable.OrderTableRepository;
import kitchenpos.table.ui.dto.OrderTableRequest;

class TableServiceTest extends IntegrationTest {

    @Autowired
    private TableService tableService;
    @Autowired
    private OrderTableRepository orderTableRepository;

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void createTest() {
        final OrderTableRequest orderTableRequest = new OrderTableRequest(3, false);
        final OrderTableResponse orderTableResponse = tableService.create(orderTableRequest);

        assertThat(orderTableResponse.getId()).isNotNull();
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void listTest() {
        final OrderTable createdOrderTable = TestObjectUtils.createOrderTable(null, null, 0, true);
        orderTableRepository.save(createdOrderTable);

        final OrderTableResponses orderTableResponses = tableService.list();

        assertThat(orderTableResponses.getOrderTableResponses().size()).isEqualTo(1);
    }

    @DisplayName("빈 테이블 설정 또는 해지할 수 있다.")
    @Test
    void changeEmptyTest() {
        OrderTable orderTable = orderTableRepository.save(
                TestObjectUtils.createOrderTable(null, null, 1, false));
        ChangeEmptyRequest changeEmptyRequest = new ChangeEmptyRequest(true);

        tableService.changeEmpty(orderTable.getId(), changeEmptyRequest);

        assertThat(orderTable.isEmpty()).isTrue();
    }

    @DisplayName("방문한 손님 수를 입력할 수 있다.")
    @Test
    void changeNumberOfGuestTest() {
        OrderTable orderTable = orderTableRepository.save(
                TestObjectUtils.createOrderTable(null, null, 1, false));
        ChangeNumberOfGuestRequest changeNumberOfGuestRequest = new ChangeNumberOfGuestRequest(5);

        tableService.changeNumberOfGuests(orderTable.getId(), changeNumberOfGuestRequest);

        assertThat(orderTable.getNumberOfGuests()).isEqualTo(
                changeNumberOfGuestRequest.getNumberOfGuests());

    }
}