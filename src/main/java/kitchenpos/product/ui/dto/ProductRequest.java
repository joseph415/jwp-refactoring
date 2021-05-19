package kitchenpos.product.ui.dto;

import kitchenpos.product.command.domain.product.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "잘못된 제품 입력입니다.")
    private String name;

    @PositiveOrZero(message = "잘못된 가격입력입니다.")
    private BigDecimal price;

    private ProductRequest() {
    }

    public ProductRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Product toEntity() {
        return new Product(name, price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
