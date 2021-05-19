package kitchenpos.menu.infra;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import kitchenpos.menu.query.dao.MenuGroupDao;
import kitchenpos.menu.query.dto.MenuGroupResponse;
import kitchenpos.menu.query.dto.MenuGroupResponses;

@Repository
public class JpaMenuGroupDao implements MenuGroupDao {

    private final EntityManager entityManager;

    public JpaMenuGroupDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MenuGroupResponse selectById(Long id) {
        return entityManager.createQuery(
                "select new kitchenpos.menu.query.dto.MenuGroupResponse(mg.id,mg.name) " +
                        "from MenuGroup mg" +
                        " where mg.id = (:id)", MenuGroupResponse.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public MenuGroupResponses select() {
        final List<MenuGroupResponse> responses = entityManager.createQuery(
                "select new kitchenpos.menu.query.dto.MenuGroupResponse(mg.id,mg.name)" +
                        "from MenuGroup mg", MenuGroupResponse.class)
                .getResultList();

        return new MenuGroupResponses(responses);
    }
}
