package kitchenpos.integration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.application.MenuGroupService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.dao.MenuGroupDao;
import kitchenpos.domain.MenuGroup;
import kitchenpos.ui.dto.MenuGroupRequest;

class MenuGroupServiceTest extends IntegrationTest {
    @Autowired
    private MenuGroupService menuGroupService;
    @Autowired
    private MenuGroupDao menuGroupDao;

    @DisplayName("메뉴 그룹을 등록할 수 있다.")
    @Test
    void should_givenMenuGroup_createMenuGroup() {
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("두마리메뉴");

        final MenuGroup menuGroup = menuGroupService.create(menuGroupRequest);
        assertAll(
                () -> assertThat(menuGroup.getId()).isNotNull(),
                () -> assertThat(menuGroup.getName()).isEqualTo("두마리메뉴")
        );
    }

    @DisplayName("메뉴 그룹의 목록을 조회할 수 있다.")
    @Test
    void should_returnMenuGroupList() {
        menuGroupDao.save(TestObjectUtils.createMenuGroup(null, "한마리메뉴"));

        List<MenuGroup> menuGroups = menuGroupService.list();

        assertThat(menuGroups.size()).isEqualTo(1);
    }
}