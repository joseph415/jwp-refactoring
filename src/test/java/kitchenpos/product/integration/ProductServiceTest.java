package kitchenpos.product.integration;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.integration.IntegrationTest;
import kitchenpos.product.command.application.ProductService;
import kitchenpos.product.command.domain.Product;
import kitchenpos.product.command.domain.ProductRepository;
import kitchenpos.product.infra.dao.JpaProductDao;
import kitchenpos.product.query.dto.ProductResponse;
import kitchenpos.product.query.dto.ProductResponses;
import kitchenpos.product.ui.dto.ProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        ProductRequest productRequest = new ProductRequest("참치뱃살", 30000);
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
        assertThat(products.getProductResponses().size()).isEqualTo(1);
    }
}