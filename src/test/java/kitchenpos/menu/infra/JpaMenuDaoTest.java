package kitchenpos.menu.infra;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import kitchenpos.menu.query.dto.MenuResponses;

@DataJpaTest
public class JpaMenuDaoTest {
    @Autowired
    private TestEntityManager testEntityManager;

    private JpaMenuDao jpaMenuDao;

    @BeforeEach
    void setUp() {
        jpaMenuDao = new JpaMenuDao(testEntityManager.getEntityManager());
    }

    @Test
    void select() {
        final MenuResponses select = jpaMenuDao.select();

        assertThat(select.getMenuResponses().size()).isEqualTo(6);
        assertThat(select.getMenuResponses().get(0).getMenuProductResponse().size()).isEqualTo(1);
    }
}
