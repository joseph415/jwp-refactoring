package kitchenpos.fixture;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.menu.command.domain.menugroup.MenuGroup;

public class MenuGroupFixture {
    public static final MenuGroup MENU_GROUP1 = TestObjectUtils.createMenuGroup(1L, "두마리메뉴");

    public static final MenuGroup MENU_GROUP2 = TestObjectUtils.createMenuGroup(2L, "한마리메뉴");

}
