package kitchenpos.application;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.menu.command.application.MenuService;
import kitchenpos.menu.command.domain.menu.Menu;
import kitchenpos.menu.command.domain.menu.MenuDao;
import kitchenpos.menu.command.domain.menuproduct.MenuProductDao;
import kitchenpos.menu.command.domain.product.ProductRepository;
import kitchenpos.menugroup.domain.MenuGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static kitchenpos.fixture.MenuFixture.*;
import static kitchenpos.fixture.MenuProductFixture.MENU_PRODUCT1;
import static kitchenpos.fixture.ProductFixture.FRIED_CHICKEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuDao menuDao;
    @Mock
    private MenuGroupRepository menuGroupRepository;
    @Mock
    private MenuProductDao menuProductDao;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private MenuService menuService;

    @DisplayName("1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.")
    @Test
    void createTest() {
        Menu createdMenu = TestObjectUtils.createMenu(null, "후라이드치킨",
                BigDecimal.valueOf(16000), 2L, Collections.singletonList(MENU_PRODUCT1));

        when(menuGroupRepository.existsById(anyLong())).thenReturn(true);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(FRIED_CHICKEN));
        when(menuDao.save(any())).thenReturn(MENU1);
        when(menuProductDao.save(any())).thenReturn(MENU_PRODUCT1);

        Menu savedMenu = menuService.create(createdMenu);
        assertAll(
                () -> assertThat(savedMenu.getId()).isEqualTo(1L),
                () -> assertThat(savedMenu.getMenuGroupId()).isEqualTo(2L),
                () -> assertThat(savedMenu.getName()).isEqualTo("후라이드치킨"),
                () -> assertThat(savedMenu.getPrice()).isEqualTo(BigDecimal.valueOf(16000)),
                () -> assertThat(savedMenu.getMenuProducts().size()).isEqualTo(1),
                () -> assertThat(savedMenu.getMenuProducts().get(0).getMenuId()).isEqualTo(1L),
                () -> assertThat(savedMenu.getMenuProducts().get(0).getProductId()).isEqualTo(1L),
                () -> assertThat(savedMenu.getMenuProducts().get(0).getQuantity()).isEqualTo(1)
        );
    }

    @DisplayName("가격이 null일 경우 에러.")
    @Test
    void notCreateMenu_when_priceNull() {
        assertThatThrownBy(() -> menuService.create(MENU_PRICE_NULL))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가격이 0보다 작을 경우 에러.")
    @Test
    void notCreateMenu_when_UnderZeroPrice() {
        assertThatThrownBy(() -> menuService.create(MENU_PRICE_NEGATIVE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴는 특정 메뉴 그룹에 속하지 않을 경우 에러.")
    @Test
    void notCreateMenu_when_notExist() {
        when(menuGroupRepository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> menuService.create(MENU1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 목록을 조회할 수 있다.")
    @Test
    void listTest() {
        when(menuDao.findAll()).thenReturn(MENUS);
        when(menuProductDao.findAllByMenuId(1L)).thenReturn(MENU1.getMenuProducts());
        when(menuProductDao.findAllByMenuId(2L)).thenReturn(MENU2.getMenuProducts());

        List<Menu> list = menuService.list();
        assertAll(
                () -> assertThat(list.size()).isEqualTo(2),
                () -> assertThat(list.get(0).getId()).isEqualTo(1L),
                () -> assertThat(list.get(1).getId()).isEqualTo(2L)
        );
    }
}