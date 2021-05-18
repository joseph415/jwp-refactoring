package kitchenpos.menugroup.query;

import kitchenpos.menugroup.query.dto.MenuGroupResponse;
import kitchenpos.menugroup.query.dto.MenuGroupResponses;

public interface MenuGroupDao {
    MenuGroupResponse selectById(Long id);

    MenuGroupResponses select();
}
