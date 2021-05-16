package kitchenpos.integration;

import static kitchenpos.fixture.OrderTableFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.application.TableService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.dao.OrderTableDao;
import kitchenpos.domain.OrderTable;

class TableServiceTest extends IntegrationTest {

    @Autowired
    private TableService tableService;
    @Autowired
    private OrderTableDao orderTableDao;

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void createTest() {
        final OrderTable createdOrderTable = TestObjectUtils.createOrderTable(null, null, 0, true);
        final OrderTable orderTable = tableService.create(createdOrderTable);

        assertThat(orderTable.getId()).isNotNull();
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void listTest() {
        final OrderTable createdOrderTable = TestObjectUtils.createOrderTable(null, null, 0, true);
        orderTableDao.save(createdOrderTable);

        final List<OrderTable> orderTables = tableService.list();
        assertThat(orderTables.size()).isEqualTo(1);
    }

    @DisplayName("빈 테이블 설정 또는 해지할 수 있다.")
    @Test
    void changeEmptyTest() {
        OrderTable changedOrderTable = orderTableDao.save(
                TestObjectUtils.createOrderTable(null, null, 0, true));

        final OrderTable orderTable = tableService.changeEmpty(changedOrderTable.getId(),
                changedOrderTable);

        assertThat(orderTable.isEmpty()).isTrue();
    }

    @DisplayName("방문한 손님 수를 입력할 수 있다.")
    @Test
    void changeNumberOfGuestTest() {
        OrderTable changedOrderTable = orderTableDao.save(
                TestObjectUtils.createOrderTable(null, null, 1, false));

        final OrderTable orderTable = tableService.changeNumberOfGuests(changedOrderTable.getId(),
                CHANGING_NOT_EMPTY_ORDER_TABLE);

        assertThat(orderTable.getNumberOfGuests()).isEqualTo(
                CHANGING_NOT_EMPTY_ORDER_TABLE.getNumberOfGuests());

    }
}