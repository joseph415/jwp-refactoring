package kitchenpos.fixture;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.product.command.domain.product.Product;

import java.math.BigDecimal;

public class ProductFixture {
    public static final Product FRIED_CHICKEN = TestObjectUtils.createProduct(1L, "후라이드",
            BigDecimal.valueOf(16000));

    public static final Product SEASONING_CHICKEN = TestObjectUtils.createProduct(2L, "양념치킨",
            BigDecimal.valueOf(16000));
}
