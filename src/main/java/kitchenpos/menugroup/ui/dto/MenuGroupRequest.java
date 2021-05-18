package kitchenpos.menugroup.ui.dto;

import javax.validation.constraints.NotBlank;

import kitchenpos.menugroup.domain.MenuGroup;

public class MenuGroupRequest {

    @NotBlank(message = "메뉴 이름을 입력하세요")
    private String name;

    private MenuGroupRequest() {
    }

    public MenuGroupRequest(String name) {
        this.name = name;
    }

    public MenuGroup toEntity() {
        return new MenuGroup(name);
    }

    public String getName() {
        return name;
    }
}
