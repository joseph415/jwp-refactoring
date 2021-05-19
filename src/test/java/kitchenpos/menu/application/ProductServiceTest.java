package kitchenpos.menu.application;

import static kitchenpos.fixture.ProductFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.product.command.application.ProductService;
import kitchenpos.product.command.domain.product.ProductRepository;
import kitchenpos.product.query.dto.ProductResponse;
import kitchenpos.product.ui.dto.ProductRequest;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 생성할 수 있다.")
    @Test
    void createTest() {
        ProductRequest productRequest = new ProductRequest("후라이드", BigDecimal.valueOf(16000));
        when(productRepository.save(any())).thenReturn(FRIED_CHICKEN);

        ProductResponse product = productService.create(productRequest);

        assertAll(
                () -> assertThat(product.getId()).isEqualTo(1L),
                () -> assertThat(product.getName()).isEqualTo("후라이드"),
                () -> assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(16000))
        );
    }

}