package kitchenpos.menu.query.dao;

import kitchenpos.menu.query.dto.MenuGroupResponse;
import kitchenpos.menu.query.dto.MenuGroupResponses;

public interface MenuGroupDao {
    MenuGroupResponse selectById(Long id);

    MenuGroupResponses select();
}
