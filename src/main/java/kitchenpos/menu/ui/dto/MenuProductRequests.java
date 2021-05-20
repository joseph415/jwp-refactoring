package kitchenpos.menu.ui.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuProductRequests {

    private List<MenuProductRequest> menuProductRequest;

    private MenuProductRequests() {
    }

    public List<Long> toProductIds() {
        return menuProductRequest.stream()
                .map(MenuProductRequest::getProductId)
                .collect(Collectors.toList());
    }

    public Map<Long, Long> toMapProductQuantity() {
        return menuProductRequest.stream()
                .collect(Collectors.toMap(MenuProductRequest::getProductId,
                        MenuProductRequest::getQuantity));
    }

    public MenuProductRequests(
            List<MenuProductRequest> menuProductRequest) {
        this.menuProductRequest = menuProductRequest;
    }

    public List<MenuProductRequest> getMenuProductRequest() {
        return menuProductRequest;
    }
}
