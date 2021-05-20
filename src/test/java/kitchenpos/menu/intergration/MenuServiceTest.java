package kitchenpos.menu.intergration;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.common.integration.IntegrationTest;
import kitchenpos.menu.command.application.MenuService;
import kitchenpos.menu.command.domain.menugroup.MenuGroupRepository;
import kitchenpos.menu.query.dto.MenuResponse;
import kitchenpos.menu.ui.dto.MenuProductRequest;
import kitchenpos.menu.ui.dto.MenuRequest;
import kitchenpos.product.command.domain.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

        MenuProductRequest menuProductRequest = new MenuProductRequest(Arrays.asList(1L, 2L), 2);
        MenuRequest menuRequest = new MenuRequest("두마리치킨",
                BigDecimal.valueOf(16000), 1L, menuProductRequest);

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
