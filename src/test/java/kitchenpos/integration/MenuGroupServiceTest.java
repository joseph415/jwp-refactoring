package kitchenpos.integration;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import kitchenpos.common.TestObjectUtils;
import kitchenpos.domain.MenuGroup;

class MenuGroupServiceTest extends IntegrationTest {
    @DisplayName("메뉴 그룹을 등록할 수 있다.")
    @Test
    void createMenuGroupTest() throws Exception {
        MenuGroup createMenuGroup = TestObjectUtils.createMenuGroup(null, "두마리메뉴");

    }

    @DisplayName("메뉴 그룹의 목록을 조회할 수 있다.")
    @Test
    void listTest() throws Exception {
       
    }
}