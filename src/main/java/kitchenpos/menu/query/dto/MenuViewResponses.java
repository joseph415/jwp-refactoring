package kitchenpos.menu.query.dto;

import java.util.List;

public class MenuViewResponses {
    List<MenuViewResponse> menuViewResponses;

    public MenuViewResponses(
            List<MenuViewResponse> menuViewResponses) {
        this.menuViewResponses = menuViewResponses;
    }

    public List<MenuViewResponse> getMenuViewResponses() {
        return menuViewResponses;
    }
}
