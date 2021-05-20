package kitchenpos.table.domain.tablegroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderTableId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "order_table_id", nullable = false)
    private Long orderTableId;

    protected OrderTableId() {
    }

    public OrderTableId(Long seq, Long orderTableId) {
        this.seq = seq;
        this.orderTableId = orderTableId;
    }

    public static OrderTableId from(Long orderTableId) {
        return new OrderTableId(null, orderTableId);
    }

    public Long getSeq() {
        return seq;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }
}
