package kitchenpos.table.integration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.common.integration.IntegrationTest;
import kitchenpos.fixture.TableGroupFixture;
import kitchenpos.table.application.TableGroupService;
import kitchenpos.table.domain.ordertable.OrderTableRepository;
import kitchenpos.table.domain.tablegroup.TableGroup;
import kitchenpos.table.domain.tablegroup.TableGroupRepository;

class TableGroupTest extends IntegrationTest {
    @Autowired
    private TableGroupService tableGroupService;
    @Autowired
    private TableGroupRepository tableGroupRepository;
    @Autowired
    private OrderTableRepository orderTableRepository;

    @DisplayName("2 개 이상의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void createTest() {
        orderTableRepository.save(TestObjectUtils.createOrderTable(null, null, 0, true));
        orderTableRepository.save(TestObjectUtils.createOrderTable(null, null, 0, true));

        final TableGroup tableGroup = TableGroupFixture.TABLE_GROUP1;

        assertAll(
                () -> assertThat(tableGroup.getId()).isNotNull(),
                () -> assertThat(tableGroup.getCreatedDate()).isNotNull(),
                () -> assertThat(tableGroup.getOrderTableIds().size()).isEqualTo(2)
        );
    }

    @DisplayName("단체 지정을 해지할 수 있다.")
    @Test
    void ungroupTest() {
        TableGroup changedTableGroup = TableGroupFixture.TABLE_GROUP1;

        tableGroupService.ungroup(changedTableGroup.getId());
        final Optional<TableGroup> tableGroup = tableGroupRepository.findById(
                changedTableGroup.getId());

        assertThat(tableGroup.isPresent()).isFalse();
    }
}