package kitchenpos.integration;

import static kitchenpos.fixture.OrderFixture.*;
import static kitchenpos.fixture.OrderTableFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import kitchenpos.application.TableService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.dao.OrderDao;
import kitchenpos.domain.OrderTable;

class TableTest extends IntegrationTest {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TableService tableService;

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void createTest() throws Exception {
        OrderTable createdOrderTable = TestObjectUtils.createOrderTable(null, null, 0, true);
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void listTest() throws Exception {
    }

    @DisplayName("빈 테이블 설정 또는 해지할 수 있다.")
    @Test
    void changeEmptyTest() throws Exception {
        OrderTable changedOrderTable = tableService.create(
                TestObjectUtils.createOrderTable(null, null, 0, true));
        orderDao.save(ORDER1);
    }

    @DisplayName("방문한 손님 수를 입력할 수 있다.")
    @Test
    void changeNumberOfGuestTest() throws Exception {
        OrderTable changedOrderTable = tableService.create(
                TestObjectUtils.createOrderTable(null, null, 0, true));
        tableService.changeEmpty(changedOrderTable.getId(), CHANGING_NOT_EMPTY_ORDER_TABLE);
    }
}