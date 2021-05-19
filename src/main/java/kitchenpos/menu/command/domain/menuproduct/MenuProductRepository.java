package kitchenpos.menu.command.domain.menuproduct;

import org.springframework.data.jpa.repository.JpaRepository;

import kitchenpos.menu.command.domain.menu.MenuProduct;

public interface MenuProductRepository extends JpaRepository<MenuProduct, Long> {
}
