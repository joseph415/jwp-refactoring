package kitchenpos.product.application;

import kitchenpos.product.command.application.ProductService;
import kitchenpos.product.command.domain.ProductRepository;
import kitchenpos.product.query.dto.ProductResponse;
import kitchenpos.product.ui.dto.ProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static kitchenpos.fixture.ProductFixture.FRIED_CHICKEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 생성할 수 있다.")
    @Test
    void createTest() {
        ProductRequest productRequest = new ProductRequest("후라이드", 16000);
        when(productRepository.save(any())).thenReturn(FRIED_CHICKEN);

        ProductResponse product = productService.create(productRequest);

        assertAll(
                () -> assertThat(product.getId()).isEqualTo(1L),
                () -> assertThat(product.getName()).isEqualTo("후라이드"),
                () -> assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(16000))
        );
    }

}