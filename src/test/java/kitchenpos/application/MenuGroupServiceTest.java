package kitchenpos.application;

import static kitchenpos.fixture.MenuGroupFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.MenuGroup.application.MenuGroupService;
import kitchenpos.MenuGroup.domain.MenuGroup;
import kitchenpos.MenuGroup.domain.MenuGroupDao;
import kitchenpos.MenuGroup.ui.dto.MenuGroupRequest;

@ExtendWith(MockitoExtension.class)
class MenuGroupServiceTest {

    @Mock
    private MenuGroupDao menuGroupDao;

    @InjectMocks
    private MenuGroupService menuGroupService;

    @DisplayName("메뉴 그룹을 등록할 수 있다.")
    @Test
    void createTest() {
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("두마리메뉴");

        when(menuGroupDao.save(any())).thenReturn(MENU_GROUP1);

        MenuGroup savedMenuGroup = menuGroupService.create(menuGroupRequest);
        assertAll(
                () -> assertThat(savedMenuGroup.getId()).isEqualTo(1L),
                () -> assertThat(savedMenuGroup.getName()).isEqualTo(
                        "두마리메뉴")
        );
    }

    @DisplayName("메뉴 그룹 목록을 조회 할 수 있다.")
    @Test
    void listTest() {
        when(menuGroupDao.findAll()).thenReturn(MENU_GROUPS);

        List<MenuGroup> list = menuGroupService.list();
        assertAll(
                () -> assertThat(list.size()).isEqualTo(2),
                () -> assertThat(list.get(0).getName()).isEqualTo("두마리메뉴"),
                () -> assertThat(list.get(1).getName()).isEqualTo("한마리메뉴")
        );
    }
}