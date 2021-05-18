package kitchenpos.integration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.application.MenuService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.dao.MenuDao;
import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuProduct;
import kitchenpos.menugroup.domain.MenuGroupDao;
import kitchenpos.product.domain.ProductDao;

class MenuServiceTest extends IntegrationTest {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuGroupDao menuGroupDao;
    @Autowired
    private ProductDao productDao;

    @DisplayName("1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.")
    @Test
    void should_givenMenuProduct_createMenu() {
        menuGroupDao.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        productDao.save(TestObjectUtils.createProduct(null, "후라이드 치킨", BigDecimal.valueOf(8000)));
        productDao.save(TestObjectUtils.createProduct(null, "양념 치킨", BigDecimal.valueOf(8000)));

        MenuProduct friedChicken =
                TestObjectUtils.createMenuProduct(1L, null, 1L, 1L);
        MenuProduct seasoningChicken =
                TestObjectUtils.createMenuProduct(2L, null, 2L, 1L);

        Menu createMenu = TestObjectUtils.createMenu(null, "두마리치킨",
                BigDecimal.valueOf(16000), 1L,
                Arrays.asList(friedChicken, seasoningChicken));

        final Menu menu = menuService.create(createMenu);

        assertAll(
                () -> assertThat(menu.getId()).isNotNull(),
                () -> assertThat(menu.getMenuGroupId()).isNotNull(),
                () -> assertThat(menu.getMenuProducts().size()).isEqualTo(2),
                () -> assertThat(menu.getName()).isEqualTo("두마리치킨"),
                () -> assertThat(menu.getPrice().intValue()).isEqualTo(16000)
        );
    }

    @DisplayName("메뉴의 목록을 조회할 수 있다.")
    @Test
    void listTest() {
        menuGroupDao.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        productDao.save(TestObjectUtils.createProduct(null, "후라이드 치킨", BigDecimal.valueOf(8000)));
        productDao.save(TestObjectUtils.createProduct(null, "양념 치킨", BigDecimal.valueOf(8000)));

        MenuProduct friedChicken =
                TestObjectUtils.createMenuProduct(1L, null, 1L, 1L);
        MenuProduct SeasoningChicken =
                TestObjectUtils.createMenuProduct(2L, null, 2L, 1L);

        menuDao.save(TestObjectUtils.createMenu(null, "두마리치킨",
                BigDecimal.valueOf(16000), 1L,
                Arrays.asList(friedChicken, SeasoningChicken)));

        final List<Menu> menus = menuService.list();

        assertThat(menus.size()).isEqualTo(1);
    }
}
