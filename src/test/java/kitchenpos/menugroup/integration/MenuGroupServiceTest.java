package kitchenpos.menugroup.integration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.integration.IntegrationTest;
import kitchenpos.menu.command.application.MenuGroupService;
import kitchenpos.menu.query.dto.MenuGroupResponse;
import kitchenpos.menu.ui.dto.MenuGroupRequest;

class MenuGroupServiceTest extends IntegrationTest {
    @Autowired
    private MenuGroupService menuGroupService;

    @DisplayName("메뉴 그룹을 등록할 수 있다.")
    @Test
    void should_givenMenuGroup_createMenuGroup() {
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("두마리메뉴");

        final MenuGroupResponse menuGroup = menuGroupService.create(menuGroupRequest);

        assertAll(
                () -> assertThat(menuGroup.getId()).isNotNull(),
                () -> assertThat(menuGroup.getName()).isEqualTo("두마리메뉴")
        );
    }
}