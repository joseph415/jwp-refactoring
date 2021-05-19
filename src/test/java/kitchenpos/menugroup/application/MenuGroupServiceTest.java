package kitchenpos.menugroup.application;

import static kitchenpos.fixture.MenuGroupFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.menu.command.application.MenuGroupService;
import kitchenpos.menu.command.domain.menugroup.MenuGroupRepository;
import kitchenpos.menu.query.dto.MenuGroupResponse;
import kitchenpos.menu.ui.dto.MenuGroupRequest;

@ExtendWith(MockitoExtension.class)
class MenuGroupServiceTest {

    @Mock
    private MenuGroupRepository menuGroupRepository;

    @InjectMocks
    private MenuGroupService menuGroupService;

    @DisplayName("메뉴 그룹을 등록할 수 있다.")
    @Test
    void createTest() {
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("두마리메뉴");

        when(menuGroupRepository.save(any())).thenReturn(MENU_GROUP1);

        MenuGroupResponse savedMenuGroup = menuGroupService.create(menuGroupRequest);
        assertAll(
                () -> assertThat(savedMenuGroup.getId()).isEqualTo(1L),
                () -> assertThat(savedMenuGroup.getName()).isEqualTo(
                        "두마리메뉴")
        );
    }
}