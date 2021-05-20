package kitchenpos.menu.infra;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import kitchenpos.menu.query.dao.MenuDao;
import kitchenpos.menu.query.dto.MenuViewResponse;
import kitchenpos.menu.query.dto.MenuViewResponses;

@Repository
public class JpaMenuDao implements MenuDao {

    private final EntityManager entityManager;

    public JpaMenuDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MenuViewResponses select() {
        final List<MenuViewResponse> menuViewResponses = entityManager.createQuery(
                "select new kitchenpos.menu.query.dto.MenuViewResponse(m.id,m.name,m.price,m.menuGroupId,mp.seq,mp.productId,mp.quantity)"
                        + " from Menu m "
                        + " join fetch m.menuProducts mp", MenuViewResponse.class)
                .getResultList();

        return new MenuViewResponses(menuViewResponses);
    }
}
