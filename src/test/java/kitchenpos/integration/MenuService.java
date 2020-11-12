package kitchenpos.integration;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuGroup;
import kitchenpos.domain.MenuProduct;

public class MenuService extends IntegrationTest {
    @DisplayName("1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.")
    @Test
    void createTest() throws Exception {
        MenuProduct friedChicken =
                TestObjectUtils.createMenuProduct(3L, null, 1L, 1L);
        MenuProduct SeasoningChicken =
                TestObjectUtils.createMenuProduct(4L, null, 2L, 1L);
        Menu createMenu = TestObjectUtils.createMenu(null, "두마리치킨",
                BigDecimal.valueOf(16000), 1L,
                Arrays.asList(friedChicken, SeasoningChicken));

    }

    @DisplayName("메뉴의 목록을 조회할 수 있다.")
    @Test
    void listTest() throws Exception {

    }
}
