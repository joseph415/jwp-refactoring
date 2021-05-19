package kitchenpos.menu.application;

import kitchenpos.menu.command.application.MenuService;
import kitchenpos.menu.command.domain.menu.MenuRepository;
import kitchenpos.menu.command.domain.menugroup.MenuGroupRepository;
import kitchenpos.menu.query.dto.MenuResponse;
import kitchenpos.menu.ui.dto.MenuProductRequest;
import kitchenpos.menu.ui.dto.MenuRequest;
import kitchenpos.product.command.domain.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static kitchenpos.fixture.MenuFixture.MENU1;
import static kitchenpos.fixture.ProductFixture.FRIED_CHICKEN;
import static kitchenpos.fixture.ProductFixture.SEASONING_CHICKEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private MenuGroupRepository menuGroupRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private MenuService menuService;

    @DisplayName("1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.")
    @Test
    void createTest() {

        when(menuGroupRepository.existsById(anyLong())).thenReturn(true);
        when(productRepository.findAllByIdIn(anyList())).thenReturn(Arrays.asList(FRIED_CHICKEN, SEASONING_CHICKEN));
        when(menuRepository.save(any())).thenReturn(MENU1);

        MenuProductRequest menuProductRequest = new MenuProductRequest(Arrays.asList(1L, 2L), 2);
        MenuRequest menuRequest = new MenuRequest("두마리치킨",
                BigDecimal.valueOf(19000), 1L, menuProductRequest);

        MenuResponse savedMenu = menuService.create(menuRequest);

        assertAll(
                () -> assertThat(savedMenu.getId()).isEqualTo(1L),
                () -> assertThat(savedMenu.getMenuGroupId()).isEqualTo(2L),
                () -> assertThat(savedMenu.getName()).isEqualTo("후라이드치킨"),
                () -> assertThat(savedMenu.getPrice()).isEqualTo(BigDecimal.valueOf(16000)),
                () -> assertThat(savedMenu.getMenuProductResponse().size()).isEqualTo(2),
                () -> assertThat(savedMenu.getMenuProductResponse().get(0).getMenuId()).isEqualTo(1L)
        );
    }
}