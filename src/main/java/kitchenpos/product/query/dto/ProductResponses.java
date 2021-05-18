package kitchenpos.product.query.dto;

import java.util.List;

public class ProductResponses {
    private List<ProductResponse> productResponses;

    public ProductResponses(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }
}
