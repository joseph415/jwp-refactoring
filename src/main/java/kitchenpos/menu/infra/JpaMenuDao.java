package kitchenpos.menu.infra;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import kitchenpos.menu.query.dao.MenuDao;
import kitchenpos.menu.query.dto.MenuProductResponse;
import kitchenpos.menu.query.dto.MenuResponse;
import kitchenpos.menu.query.dto.MenuResponses;

@Repository
public class JpaMenuDao implements MenuDao {

    private final EntityManager entityManager;

    public JpaMenuDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MenuResponses select() {
        final List<MenuResponse> menuResponses = entityManager.createQuery(
                "select new kitchenpos.menu.query.dto.MenuResponse(m.id,m.name,m.price,m.menuGroupId) "
                        + "from Menu m ", MenuResponse.class).getResultList();

        final List<MenuProductResponse> menuProductResponses = entityManager.createQuery(
                "select new kitchenpos.menu.query.dto.MenuProductResponse(mp.seq,mp.menuId,mp.productId,mp.quantity)"
                        + " from MenuProduct mp", MenuProductResponse.class).getResultList();

        Map<Long, List<MenuProductResponse>> menu = menuProductResponses.stream()
                .collect(Collectors.groupingBy(MenuProductResponse::getMenuId));

        menuResponses.forEach(menuResponse -> menuResponse.setMenuProductResponse(
                menu.get(menuResponse.getId())));

        return new MenuResponses(menuResponses);
    }
}
