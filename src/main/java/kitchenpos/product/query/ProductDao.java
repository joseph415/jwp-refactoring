package kitchenpos.product.query;

import kitchenpos.product.query.dto.ProductResponse;
import kitchenpos.product.query.dto.ProductResponses;

public interface ProductDao {
    ProductResponse selectById(Long id);

    ProductResponses select();
}
