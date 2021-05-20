package kitchenpos.table.domain.tablegroup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TableGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_group_id")
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "table_group_id")
    private List<OrderTableId> orderTableIds;

    protected TableGroup() {
    }

    public TableGroup(Long id, LocalDateTime createdDate,
            List<OrderTableId> orderTableIds) {
        this.id = id;
        this.createdDate = createdDate;
        this.orderTableIds = orderTableIds;
    }

    public static TableGroup from(List<Long> orderTableIds) {
        final List<OrderTableId> ids = orderTableIds.stream()
                .map(id -> new OrderTableId(null, id))
                .collect(Collectors.toList());

        return new TableGroup(null, LocalDateTime.now(), ids);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<OrderTableId> getOrderTableIds() {
        return orderTableIds;
    }
}
