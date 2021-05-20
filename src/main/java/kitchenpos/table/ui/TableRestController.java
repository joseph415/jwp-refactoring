package kitchenpos.table.ui;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kitchenpos.table.application.TableService;
import kitchenpos.table.application.dto.ChangeEmptyRequest;
import kitchenpos.table.application.dto.ChangeNumberOfGuestRequest;
import kitchenpos.table.application.dto.OrderTableResponse;
import kitchenpos.table.application.dto.OrderTableResponses;
import kitchenpos.table.ui.dto.OrderTableRequest;

@RestController
public class TableRestController {
    private final TableService tableService;

    public TableRestController(final TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/api/tables")
    public ResponseEntity<OrderTableResponse> create(
            @RequestBody @Valid final OrderTableRequest orderTableRequest) {
        final OrderTableResponse created = tableService.create(orderTableRequest);
        final URI uri = URI.create("/api/tables/" + created.getId());

        return ResponseEntity.created(uri)
                .body(created);
    }

    @GetMapping("/api/tables")
    public ResponseEntity<OrderTableResponses> list() {
        return ResponseEntity.ok()
                .body(tableService.list())
                ;
    }

    @PutMapping("/api/tables/{orderTableId}/number-of-guests")
    public ResponseEntity<Void> changeNumberOfGuests(
            @PathVariable final Long orderTableId,
            @RequestBody final ChangeNumberOfGuestRequest changeNumberOfGuestRequest
    ) {
        tableService.changeNumberOfGuests(orderTableId, changeNumberOfGuestRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/tables/{orderTableId}/empty")
    public ResponseEntity<Void> changeEmpty(
            @PathVariable final Long orderTableId,
            @RequestBody @Valid final ChangeEmptyRequest changeEmptyRequest
    ) {
        tableService.changeEmpty(orderTableId, changeEmptyRequest);

        return ResponseEntity.noContent()
                .build();
    }
}
