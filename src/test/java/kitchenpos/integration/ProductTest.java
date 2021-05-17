package kitchenpos.integration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.application.ProductService;
import kitchenpos.common.TestObjectUtils;
import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Product;

class ProductTest extends IntegrationTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;

    @DisplayName("상품을 등록할 수 있다.")
    @Test
    void createTest() {

        Product createdProduct = TestObjectUtils.createProduct(null, "참치뱃살",
                BigDecimal.valueOf(30000));
        final Product product = productService.create(createdProduct);

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
        productDao.save(createdProduct);

        final List<Product> products = productService.list();
        assertThat(products.size()).isEqualTo(1);

    }
}