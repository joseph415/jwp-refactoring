package kitchenpos.menu.command.domain.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    Long countByIdIn(List<Long> ids);
}
