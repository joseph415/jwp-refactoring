package kitchenpos.menugroup.infra;

import kitchenpos.menugroup.query.MenuGroupDao;
import kitchenpos.menugroup.query.dto.MenuGroupResponse;
import kitchenpos.menugroup.query.dto.MenuGroupResponses;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaMenuGroupDao implements MenuGroupDao {

    private final EntityManager entityManager;

    public JpaMenuGroupDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MenuGroupResponse selectById(Long id) {
        return entityManager.createQuery("select new kitchenpos.menugroup.query.dto.MenuGroupResponse(mg.id,mg.name) " +
                "from MenuGroup mg" +
                " where mg.id = (:id)", MenuGroupResponse.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public MenuGroupResponses select() {
        final List<MenuGroupResponse> responses = entityManager.createQuery("select new kitchenpos.menugroup.query.dto.MenuGroupResponse(mg.id,mg.name)" +
                "from MenuGroup mg", MenuGroupResponse.class)
                .getResultList();

        return new MenuGroupResponses(responses);
    }
}
