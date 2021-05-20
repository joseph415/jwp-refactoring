package kitchenpos.menu.command.application;

import java.util.List;

public class MenuResponses {
    List<MenuResponse> menuResponses;

    public MenuResponses(List<MenuResponse> menuResponses) {
        this.menuResponses = menuResponses;
    }

    public List<MenuResponse> getMenuResponses() {
        return menuResponses;
    }
}
