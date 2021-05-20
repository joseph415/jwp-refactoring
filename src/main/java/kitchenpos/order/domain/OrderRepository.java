package kitchenpos.order.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join fetch o.orderLineItems")
    List<Order> findAll();

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END" +
            " FROM Order o WHERE o.orderTableId = (:orderTableId) AND o.orderStatus IN (:orderStatuses)")
    boolean existsByOrderTableIdAndOrderStatusIn(@Param("orderTableId") Long orderTableId,
            @Param("orderStatuses") List<String> orderStatuses);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END" +
            " FROM Order o WHERE o.orderTableId In (:orderTableIds) AND o.orderStatus IN (:orderStatuses)")
    boolean existsByOrderTableIdInAndOrderStatusIn(@Param("orderTableIds") List<Long> orderTableIds,
            @Param("orderStatuses") List<String> orderStatuses);
}
