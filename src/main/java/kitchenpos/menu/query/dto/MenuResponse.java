package kitchenpos.menu.query.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.menu.command.domain.menu.Menu;
import kitchenpos.menu.command.domain.menuproduct.MenuProduct;

public class MenuResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductResponse> menuProductResponse;

    public MenuResponse(Long id, String name, BigDecimal price, Long menuGroupId,
            List<MenuProductResponse> menuProductResponse) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProductResponse = menuProductResponse;
    }

    public MenuResponse(Long id, String name, BigDecimal price, Long menuGroupId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
    }

    public static MenuResponse of(Menu menu, List<MenuProduct> menuProducts) {
        return new MenuResponse(menu.getId(),
                menu.getName(),
                menu.getPrice(),
                menu.getMenuGroupId(),
                menuProducts.stream()
                        .map(MenuProductResponse::of)
                        .collect(Collectors.toList())
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductResponse> getMenuProductResponse() {
        return menuProductResponse;
    }

    public void setMenuProductResponse(
            List<MenuProductResponse> menuProductResponse) {
        this.menuProductResponse = menuProductResponse;
    }
}
