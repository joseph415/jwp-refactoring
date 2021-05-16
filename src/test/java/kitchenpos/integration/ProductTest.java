package kitchenpos.integration;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.Product;

class ProductTest extends IntegrationTest {

    @DisplayName("상품을 등록할 수 있다.")
    @Test
    void createTest() throws Exception {

        Product createdProduct = TestObjectUtils.createProduct(null, "참치뱃살",
                BigDecimal.valueOf(30000));

    }

    @DisplayName("상품을 조회 할 수 있다.")
    @Test
    void listTest() throws Exception {

    }
}