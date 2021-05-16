package kitchenpos.integration;

import static kitchenpos.fixture.OrderTableFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.application.TableGroupService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.dao.OrderTableDao;
import kitchenpos.dao.TableGroupDao;
import kitchenpos.domain.TableGroup;

class TableGroupTest extends IntegrationTest {
    @Autowired
    private TableGroupService tableGroupService;
    @Autowired
    private TableGroupDao tableGroupDao;
    @Autowired
    private OrderTableDao orderTableDao;

    @DisplayName("2 개 이상의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void createTest() {
        orderTableDao.save(TestObjectUtils.createOrderTable(null, null, 0, true));
        orderTableDao.save(TestObjectUtils.createOrderTable(null, null, 0, true));

        final TableGroup tableGroup = tableGroupService.create(
                TestObjectUtils.createTableGroup(null, null, ORDER_TABLES1));

        assertAll(
                () -> assertThat(tableGroup.getId()).isNotNull(),
                () -> assertThat(tableGroup.getCreatedDate()).isNotNull(),
                () -> assertThat(tableGroup.getOrderTables().size()).isEqualTo(2)
        );
    }

    @DisplayName("단체 지정을 해지할 수 있다.")
    @Test
    void ungroupTest() {
        TableGroup changedTableGroup = tableGroupDao.save(
                TestObjectUtils.createTableGroup(null, LocalDateTime.now(), ORDER_TABLES1));

        tableGroupService.ungroup(changedTableGroup.getId());

        assertAll(
                () -> assertThat(ORDER_TABLES1.get(0).getTableGroupId()).isNull(),
                () -> assertThat(ORDER_TABLES1.get(1).getTableGroupId()).isNull()
        );
    }
}