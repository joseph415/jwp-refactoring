package kitchenpos.table.application;

import static kitchenpos.fixture.OrderTableFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.table.application.dto.ChangeEmptyRequest;
import kitchenpos.table.application.dto.ChangeNumberOfGuestRequest;
import kitchenpos.table.application.dto.OrderTableResponses;
import kitchenpos.table.domain.ValidationDomainService;
import kitchenpos.table.domain.ordertable.OrderTableRepository;
import kitchenpos.table.ui.dto.OrderTableRequest;

@ExtendWith(MockitoExtension.class)
class TableServiceTest {

    @Mock
    private OrderTableRepository orderTableRepository;
    @Mock
    private ValidationDomainService validationDomainService;

    @InjectMocks
    private TableService tableService;

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void createTable() {
        OrderTableRequest createOrderTable =
                new OrderTableRequest(1, true);

        when(orderTableRepository.save(any())).thenReturn(ORDER_TABLE1);

        assertThat(tableService.create(createOrderTable).getNumberOfGuests()).isEqualTo(1);
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void listTest() {
        when(orderTableRepository.findAll()).thenReturn(Arrays.asList(ORDER_TABLE1));

        OrderTableResponses list = tableService.list();
        assertAll(
                () -> assertThat(list.getOrderTableResponses().size()).isEqualTo(1),
                () -> assertThat(list.getOrderTableResponses().get(0).getId()).isEqualTo(1L)
        );
    }

    @DisplayName("방문한 손님 수를 입력할 수 있다.")
    @Test
    void changeNumberOfGuestsTest() {
        ChangeNumberOfGuestRequest changeNumberOfGuestRequest = new ChangeNumberOfGuestRequest(5);

        when(orderTableRepository.findById(any())).thenReturn(Optional.of(ORDER_TABLE3));

        tableService.changeNumberOfGuests(ORDER_TABLE3.getId(), changeNumberOfGuestRequest);

        verify(orderTableRepository).findById(any());
    }

    @DisplayName("단체 지정된 주문 테이블은 빈 테이블 설정 또는 해지할 수 없다.")
    @Test
    void notChangeEmptyTest_when_tableGroupNotNull() {
        ChangeEmptyRequest changeEmptyRequest = new ChangeEmptyRequest(false);

        when(orderTableRepository.findById(any())).thenReturn(Optional.of(ORDER_TABLE3));

        tableService.changeEmpty(ORDER_TABLE3.getId(), changeEmptyRequest);

        verify(orderTableRepository).findById(any());
        verify(validationDomainService).checkOrderTable(any(), any(), any());
    }

}