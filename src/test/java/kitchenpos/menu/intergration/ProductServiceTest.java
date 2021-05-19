package kitchenpos.menu.intergration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.integration.IntegrationTest;
import kitchenpos.menu.command.application.ProductService;
import kitchenpos.menu.command.domain.product.Product;
import kitchenpos.menu.command.domain.product.ProductRepository;
import kitchenpos.menu.infra.JpaProductDao;
import kitchenpos.menu.query.dto.ProductResponse;
import kitchenpos.menu.query.dto.ProductResponses;
import kitchenpos.menu.ui.dto.ProductRequest;

class ProductServiceTest extends IntegrationTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JpaProductDao jpaProductDao;

    @DisplayName("상품을 등록할 수 있다.")
    @Test
    void createTest() {
        ProductRequest productRequest = new ProductRequest("참치뱃살", BigDecimal.valueOf(30000));
        final ProductResponse product = productService.create(productRequest);

        assertAll(
                () -> assertThat(product.getId()).isNotNull(),
                () -> assertThat(product.getName()).isEqualTo("참치뱃살"),
                () -> assertThat(product.getPrice().intValue()).isEqualTo(30000)
        );

    }

    @DisplayName("상품을 조회 할 수 있다.")
    @Test
    void listTest() {
        Product createdProduct = TestObjectUtils.createProduct(null, "참치뱃살",
                BigDecimal.valueOf(30000));
        productRepository.save(createdProduct);

        final ProductResponses products = jpaProductDao.select();
        assertThat(products.getResponses().size()).isEqualTo(1);
    }
}