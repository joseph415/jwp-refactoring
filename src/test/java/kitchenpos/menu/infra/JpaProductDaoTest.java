package kitchenpos.menu.infra;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import kitchenpos.product.infra.JpaProductDao;
import kitchenpos.product.query.dto.ProductResponses;

@DataJpaTest
class JpaProductDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private JpaProductDao jpaProductDao;

    @BeforeEach
    void setUp() {
        jpaProductDao = new JpaProductDao(testEntityManager.getEntityManager());
    }

    @DisplayName("상품목록을 조회 할 수 있다.")
    @Test
    void listTest() {
        ProductResponses productResponses = jpaProductDao.select();

        assertThat(productResponses.getResponses().size()).isEqualTo(6);
    }
}
