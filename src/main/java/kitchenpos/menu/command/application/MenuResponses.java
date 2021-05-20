package kitchenpos.menu.command.application;

import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.menu.command.domain.menu.Menu;

public class MenuResponses {
    List<MenuResponse> menuResponses;

    public MenuResponses(List<MenuResponse> menuResponses) {
        this.menuResponses = menuResponses;
    }

    public static MenuResponses from(List<Menu> menus) {
        return new MenuResponses(
                menus.stream().map(MenuResponse::from).collect(Collectors.toList()));
    }

    public List<MenuResponse> getMenuResponses() {
        return menuResponses;
    }
}
