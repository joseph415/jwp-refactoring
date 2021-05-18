package kitchenpos.product.ui.dto;

import kitchenpos.product.command.domain.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "잘못된 제품 입력입니다.")
    private String name;

    @Positive(message = "잘못된 가격입력입니다.")
    private int price;

    private ProductRequest() {
    }

    public ProductRequest(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product toEntity() {
        return new Product(name, BigDecimal.valueOf(price));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
