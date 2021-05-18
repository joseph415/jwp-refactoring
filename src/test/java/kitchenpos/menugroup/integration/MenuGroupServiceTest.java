package kitchenpos.menugroup.integration;

import kitchenpos.integration.IntegrationTest;
import kitchenpos.menugroup.application.MenuGroupService;
import kitchenpos.menugroup.query.dto.MenuGroupResponse;
import kitchenpos.menugroup.ui.dto.MenuGroupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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