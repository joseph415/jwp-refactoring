package kitchenpos.menu.query.dto;

import java.math.BigDecimal;

public class MenuViewResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private Long seq;
    private Long productId;
    private long quantity;

    private MenuViewResponse() {
    }

    public MenuViewResponse(Long id, String name, BigDecimal price, Long menuGroupId,
            Long seq, Long productId, long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.seq = seq;
        this.productId = productId;
        this.quantity = quantity;
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

    public Long getSeq() {
        return seq;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }
}
