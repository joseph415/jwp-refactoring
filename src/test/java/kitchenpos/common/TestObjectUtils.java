package kitchenpos.common;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import kitchenpos.menu.command.domain.menu.Menu;
import kitchenpos.menu.command.domain.menu.MenuProduct;
import kitchenpos.menu.command.domain.menugroup.MenuGroup;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.product.command.domain.product.Product;
import kitchenpos.table.domain.ordertable.OrderTable;
import kitchenpos.table.domain.tablegroup.OrderTableId;
import kitchenpos.table.domain.tablegroup.TableGroup;

public class TestObjectUtils {
    private TestObjectUtils() {
    }

    public static Product createProduct(Long id, String name, BigDecimal price) {
        Product product = new Product(name, price);
        ReflectionTestUtils.setField(product, "id", id);

        return product;
    }

    public static MenuGroup createMenuGroup(Long id, String name) {
        MenuGroup menuGroup = new MenuGroup(name);
        ReflectionTestUtils.setField(menuGroup, "id", id);

        return menuGroup;
    }

    public static Menu createMenu(Long id, String name, BigDecimal price, Long menuGroupId,
            List<MenuProduct> menuProducts) {
        Menu menu = new Menu(null, name, price, menuGroupId, menuProducts);
        ReflectionTestUtils.setField(menu, "id", id);

        return menu;
    }

    public static MenuProduct createMenuProduct(Long seq, Long productId,
            Long quantity) {
        MenuProduct menuProduct = new MenuProduct(null, productId, quantity);
        ReflectionTestUtils.setField(menuProduct, "seq", seq);

        return menuProduct;
    }

    public static OrderTable createOrderTable(Long id, Long tableGroupId, int numberOfGuests,
            boolean empty) {
        OrderTable orderTable = new OrderTable(null, tableGroupId, numberOfGuests, empty);
        ReflectionTestUtils.setField(orderTable, "id", id);

        return orderTable;
    }

    public static Order createOrder(Long id, Long orderTableId, String orderStatus,
            LocalDateTime orderedTime, List<OrderLineItem> orderLineItems) {
        Order order = new Order(null, orderTableId, orderStatus, orderedTime, orderLineItems);
        ReflectionTestUtils.setField(order, "id", id);

        return order;
    }

    public static OrderLineItem createOrderLineItem(Long seq, Long menuId,
            long quantity) {
        OrderLineItem orderLineItem = new OrderLineItem(null, menuId, quantity);
        ReflectionTestUtils.setField(orderLineItem, "seq", seq);

        return orderLineItem;
    }

    public static TableGroup createTableGroup(Long id, LocalDateTime createdDate,
            List<OrderTableId> orderTables) {
        TableGroup tableGroup = new TableGroup(null, createdDate, orderTables);
        ReflectionTestUtils.setField(tableGroup, "id", id);

        return tableGroup;
    }
}
