package kitchenpos.menu.infra;

import kitchenpos.menu.query.dao.ProductDao;
import kitchenpos.menu.query.dto.ProductResponse;
import kitchenpos.menu.query.dto.ProductResponses;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaProductDao implements ProductDao {

    private final EntityManager entityManager;

    public JpaProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProductResponse selectById(Long id) {
        String selectById = "select new kitchenpos.menu.query.dto.ProductResponse(p.id,p.name,p.price)" +
                " from Product p" +
                " where p.id = (:id)";

        return entityManager.createQuery(selectById, ProductResponse.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public ProductResponses select() {
        String select = "select new kitchenpos.menu.query.dto.ProductResponse(p.id,p.name,p.price) from Product p";

        final List<ProductResponse> resultList = entityManager.createQuery(select, ProductResponse.class)
                .getResultList();

        return new ProductResponses(resultList);
    }
}
