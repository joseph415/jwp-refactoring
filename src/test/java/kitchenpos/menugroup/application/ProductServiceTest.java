package kitchenpos.menugroup.application;

import static kitchenpos.fixture.ProductFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.product.application.ProductService;
import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductDao;
import kitchenpos.product.ui.ProductRequest;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 생성할 수 있다.")
    @Test
    void createTest() {
        ProductRequest productRequest = new ProductRequest("후라이드", 16000);
        when(productDao.save(any())).thenReturn(FRIED_CHICKEN);

        Product product = productService.create(productRequest);

        assertAll(
                () -> assertThat(product.getId()).isEqualTo(1L),
                () -> assertThat(product.getName()).isEqualTo("후라이드"),
                () -> assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(16000))
        );
    }

    @DisplayName("상품목록을 조회 할 수 있다.")
    @Test
    void listTest() {
        when(productDao.findAll()).thenReturn(PRODUCTS);

        List<Product> list = productService.list();
        assertAll(
                () -> assertThat(list.size()).isEqualTo(2),
                () -> assertThat(list.get(0).getName()).isEqualTo("후라이드"),
                () -> assertThat(list.get(1).getName()).isEqualTo("양념치킨")
        );
    }
}