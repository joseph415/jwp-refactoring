package kitchenpos.menu.query.dto;

import java.util.List;

public class ProductResponses {
    private final List<ProductResponse> responses;

    public ProductResponses(List<ProductResponse> responses) {
        this.responses = responses;
    }

    public List<ProductResponse> getResponses() {
        return responses;
    }
}
