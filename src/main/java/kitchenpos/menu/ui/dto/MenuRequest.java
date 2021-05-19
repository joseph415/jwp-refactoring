package kitchenpos.menu.ui.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class MenuRequest {
    @NotBlank
    private String name;
    @PositiveOrZero
    private BigDecimal price;
    @NotNull
    private Long menuGroupId;
    @NotNull(message = "빈값 안됨")
    private MenuProductRequest menuProducts;

    private MenuRequest() {
    }

    public MenuRequest(String name, BigDecimal price, Long menuGroupId,
            MenuProductRequest menuProducts) {
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
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

    public MenuProductRequest getMenuProducts() {
        return menuProducts;
    }
}
