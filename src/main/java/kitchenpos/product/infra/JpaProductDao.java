package kitchenpos.product.infra;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import kitchenpos.product.query.ProductDao;
import kitchenpos.product.query.dto.ProductResponse;
import kitchenpos.product.query.dto.ProductResponses;

@Repository
public class JpaProductDao implements ProductDao {

    private final EntityManager entityManager;

    public JpaProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProductResponse selectById(Long id) {
        String selectById =
                "select new kitchenpos.product.query.dto.ProductResponse(p.id,p.name,p.price)" +
                        " from Product p" +
                        " where p.id = (:id)";

        return entityManager.createQuery(selectById, ProductResponse.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public ProductResponses select() {
        // TODO: 2021/05/19 엔티티 자체를 넣어버리면 식별자로 넣어버리기 떄문에 식별자를 다 가져온 후에 하나씩 조회한다.
        String select = "select new kitchenpos.product.query.dto.ProductResponse(p.id,p.name,p.price) from Product p";

        final List<ProductResponse> resultList = entityManager.createQuery(select,
                ProductResponse.class)
                .getResultList();

        return new ProductResponses(resultList);
    }
}
