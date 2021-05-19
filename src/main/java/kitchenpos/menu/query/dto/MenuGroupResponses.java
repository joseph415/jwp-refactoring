package kitchenpos.menu.query.dto;

import java.util.List;

public class MenuGroupResponses {

    List<MenuGroupResponse> responses;

    public MenuGroupResponses(List<MenuGroupResponse> responses) {
        this.responses = responses;
    }

    public List<MenuGroupResponse> getResponses() {
        return responses;
    }
}
