package kitchenpos.menuproduct.domian;

import kitchenpos.menuproduct.domian.MenuProduct;

import java.util.List;
import java.util.Optional;

public interface MenuProductDao {
    MenuProduct save(MenuProduct entity);

    Optional<MenuProduct> findById(Long id);

    List<MenuProduct> findAll();

    List<MenuProduct> findAllByMenuId(Long menuId);
}
