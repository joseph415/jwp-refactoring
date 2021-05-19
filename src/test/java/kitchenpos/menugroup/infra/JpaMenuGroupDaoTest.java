package kitchenpos.menugroup.infra;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import kitchenpos.menu.infra.JpaMenuGroupDao;
import kitchenpos.menu.query.dto.MenuGroupResponse;
import kitchenpos.menu.query.dto.MenuGroupResponses;

@DataJpaTest
class JpaMenuGroupDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    private JpaMenuGroupDao jpaMenuGroupDao;

    @BeforeEach
    void setUp() {
        jpaMenuGroupDao = new JpaMenuGroupDao(entityManager.getEntityManager());
    }

    @DisplayName("menuGroupResponse dto를 바로조회한다.")
    @Test
    void selectById() {
        final MenuGroupResponse responses = jpaMenuGroupDao.selectById(1L);

        assertThat(responses.getId()).isEqualTo(1L);
    }

    @DisplayName("menuGroupResponses dto를 바로조회한다.")
    @Test
    void select() {
        final MenuGroupResponses responses = jpaMenuGroupDao.select();

        assertThat(responses.getResponses().size()).isEqualTo(4);
    }
}