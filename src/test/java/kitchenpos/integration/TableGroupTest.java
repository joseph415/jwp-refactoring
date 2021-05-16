package kitchenpos.integration;

import static kitchenpos.fixture.OrderTableFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import kitchenpos.application.TableGroupService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.domain.TableGroup;

class TableGroupTest extends IntegrationTest {
    @Autowired
    private TableGroupService tableGroupService;

    @DisplayName("2 개 이상의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void createTest() throws Exception {
        final TableGroup tableGroup = TestObjectUtils.createTableGroup(null, null, ORDER_TABLES1);
    }

    @DisplayName("단체 지정을 해지할 수 있다.")
    @Test
    void ungroupTest() throws Exception {
        TableGroup changedTableGroup = tableGroupService.create(
                TestObjectUtils.createTableGroup(null, null, ORDER_TABLES1));

    }
}