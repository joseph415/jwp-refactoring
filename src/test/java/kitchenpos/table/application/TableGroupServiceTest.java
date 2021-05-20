package kitchenpos.table.application;

import static kitchenpos.fixture.OrderTableFixture.*;
import static kitchenpos.fixture.TableGroupFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.order.domain.OrderRepository;
import kitchenpos.table.application.dto.TableGroupRequest;
import kitchenpos.table.application.dto.TableGroupResponse;
import kitchenpos.table.domain.ordertable.OrderTableRepository;
import kitchenpos.table.domain.tablegroup.OrderTableId;
import kitchenpos.table.domain.tablegroup.TableGroupRepository;

@ExtendWith(MockitoExtension.class)
class TableGroupServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderTableRepository orderTableRepository;
    @Mock
    private TableGroupRepository tableGroupRepository;

    @InjectMocks
    private TableGroupService tableGroupService;

    @DisplayName("2 개 이상의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void createTest() {
        TableGroupRequest tableGroupRequest = new TableGroupRequest(
                ORDER_TABLE_IDS1.stream().map(OrderTableId::getSeq).collect(Collectors.toList()));

        when(orderTableRepository.findAllByIdIn(anyList())).thenReturn(
                Arrays.asList(ORDER_TABLE1, ORDER_TABLE2));
        when(tableGroupRepository.save(any())).thenReturn(TABLE_GROUP1);

        TableGroupResponse tableGroup = tableGroupService.create(tableGroupRequest);
        assertAll(
                () -> assertThat(tableGroup.getId()).isEqualTo(1L),
                () -> assertThat(tableGroup.getOrderTableIds().get(0).getSeq())
                        .isEqualTo(1L),
                () -> assertThat(tableGroup.getOrderTableIds().get(1).getSeq())
                        .isEqualTo(1L),
                () -> assertThat(tableGroup.getOrderTableIds().get(0).getSeq()).isEqualTo(1L),
                () -> assertThat(tableGroup.getOrderTableIds().get(1).getSeq()).isEqualTo(2L)
        );
    }

    @DisplayName("2 개 미만의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void createTest_when_OneOrderTable() {
        TableGroupRequest tableGroupRequest = new TableGroupRequest(Collections.singletonList(1L));

        when(orderTableRepository.findAllByIdIn(anyList())).thenReturn(
                Collections.singletonList(ORDER_TABLE1));

        assertThatThrownBy(() -> tableGroupService.create(tableGroupRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }
    //
    // @DisplayName("단체 지정은 중복될 수 없다.")
    // @Test
    // void createTest_when_OrderTable() {
    //     TableGroupRequest tableGroupRequest = new TableGroupRequest(
    //             Arrays.asList(1L, 1L));
    //
    //     when(orderTableRepository.findAllByIdIn(anyList())).thenReturn(Arrays.asList(1L, 1L));
    //
    //     assertThatThrownBy(() -> tableGroupService.create(tableGroupRequest))
    //             .isInstanceOf(IllegalArgumentException.class);
    // }
    //
    // @DisplayName("단체 지정을 해지할 수 있다.")
    // @Test
    // void ungroupTest() {
    //     when(orderTableRepository.findAllByTableGroupId(anyLong())).thenReturn(ORDER_TABLE_IDS2);
    //
    //     tableGroupService.ungroup(TABLE_GROUP1.getId());
    //
    //     assertAll(
    //             () -> assertThat(ORDER_TABLE_IDS2.get(0).getTableGroupId()).isNull(),
    //             () -> assertThat(ORDER_TABLE_IDS2.get(1).getTableGroupId()).isNull(),
    //             () -> assertThat(ORDER_TABLE_IDS2.get(0).isEmpty()).isFalse(),
    //             () -> assertThat(ORDER_TABLE_IDS2.get(1).isEmpty()).isFalse()
    //     );
    // }
    //
    // @DisplayName("단체 지정된 주문 테이블의 주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.")
    // @Test
    // void notUngroupTest_when_orderStatusIsMEALAndCooking() {
    //     when(orderRepository.existsByOrderTableIdInAndOrderStatusIn(anyList(), anyList())).thenReturn(
    //             true);
    //
    //     assertThatThrownBy(() -> tableGroupService.ungroup(TABLE_GROUP1.getId()))
    //             .isInstanceOf(IllegalArgumentException.class);
    // }
}