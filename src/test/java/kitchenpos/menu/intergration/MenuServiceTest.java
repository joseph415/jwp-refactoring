package kitchenpos.menu.intergration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.common.integration.IntegrationTest;
import kitchenpos.menu.command.application.MenuResponse;
import kitchenpos.menu.command.application.MenuService;
import kitchenpos.menu.command.domain.menugroup.MenuGroupRepository;
import kitchenpos.menu.ui.dto.MenuProductRequest;
import kitchenpos.menu.ui.dto.MenuRequest;
import kitchenpos.product.command.domain.product.ProductRepository;

class MenuServiceTest extends IntegrationTest {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuGroupRepository menuGroupRepository;
    @Autowired
    private ProductRepository productRepository;

    @DisplayName("1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.")
    @Test
    void should_givenMenuProduct_createMenu() {
        menuGroupRepository.save(TestObjectUtils.createMenuGroup(null, "두마리치킨"));
        productRepository.save(
                TestObjectUtils.createProduct(null, "후라이드 치킨", BigDecimal.valueOf(8000)));
        productRepository.save(
                TestObjectUtils.createProduct(null, "양념 치킨", BigDecimal.valueOf(8000)));

        final List<MenuProductRequest> menuProducts = Arrays.asList(new MenuProductRequest(1L, 1),
                new MenuProductRequest(2L, 1));
        MenuRequest menuRequest = new MenuRequest("두마리치킨",
                BigDecimal.valueOf(16000), 1L, menuProducts);

        final MenuResponse menu = menuService.create(menuRequest);

        assertAll(
                () -> assertThat(menu.getId()).isNotNull(),
                () -> assertThat(menu.getMenuGroupId()).isNotNull(),
                () -> assertThat(menu.getMenuProductResponse().get(0).getSeq()).isEqualTo(1),
                () -> assertThat(menu.getMenuProductResponse().get(1).getSeq()).isEqualTo(2),
                () -> assertThat(menu.getMenuProductResponse().size()).isEqualTo(2),
                () -> assertThat(menu.getName()).isEqualTo("두마리치킨"),
                () -> assertThat(menu.getPrice().intValue()).isEqualTo(16000)
        );
    }
}
