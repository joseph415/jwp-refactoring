package kitchenpos.menugroup.application;

import kitchenpos.menugroup.domain.MenuGroupRepository;
import kitchenpos.menugroup.query.dto.MenuGroupResponse;
import kitchenpos.menugroup.ui.dto.MenuGroupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static kitchenpos.fixture.MenuGroupFixture.MENU_GROUP1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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