package kitchenpos.fixture;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.menu.command.domain.menu.Menu;

import java.math.BigDecimal;

public class MenuFixture {
    public static final Menu MENU1 = TestObjectUtils.createMenu(1L, "후라이드치킨",
            BigDecimal.valueOf(16000), 2L);

    public static final Menu MENU2 = TestObjectUtils.createMenu(2L, "양념치킨",
            BigDecimal.valueOf(16000), 2L);
}
