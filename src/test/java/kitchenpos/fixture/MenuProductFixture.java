package kitchenpos.fixture;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.menu.command.domain.menu.MenuProduct;

public class MenuProductFixture {
    public static final MenuProduct MENU_PRODUCT1 =
            TestObjectUtils.createMenuProduct(1L, 1L, 1L);

    public static final MenuProduct MENU_PRODUCT2 =
            TestObjectUtils.createMenuProduct(2L, 1L, 1L);
}
