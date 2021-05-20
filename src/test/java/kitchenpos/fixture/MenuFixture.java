package kitchenpos.fixture;

import static kitchenpos.fixture.MenuProductFixture.*;

import java.math.BigDecimal;
import java.util.Arrays;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.menu.command.domain.menu.Menu;

public class MenuFixture {
    public static final Menu MENU1 = TestObjectUtils.createMenu(1L, "후라이드치킨",
            BigDecimal.valueOf(16000), 2L, Arrays.asList(MENU_PRODUCT1, MENU_PRODUCT2));

}
