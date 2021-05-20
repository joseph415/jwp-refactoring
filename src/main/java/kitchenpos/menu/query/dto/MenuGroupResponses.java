package kitchenpos.menu.query.dto;

import java.util.List;

public class MenuGroupResponses {

    private final List<MenuGroupResponse> responses;

    public MenuGroupResponses(List<MenuGroupResponse> responses) {
        this.responses = responses;
    }

    public List<MenuGroupResponse> getResponses() {
        return responses;
    }
}
