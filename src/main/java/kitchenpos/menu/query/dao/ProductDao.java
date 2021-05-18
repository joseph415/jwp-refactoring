package kitchenpos.menu.query.dao;

import kitchenpos.menu.query.dto.ProductResponse;
import kitchenpos.menu.query.dto.ProductResponses;

public interface ProductDao {
    ProductResponse selectById(Long id);

    ProductResponses select();
}
