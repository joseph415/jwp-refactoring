package kitchenpos.menu.ui;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kitchenpos.menu.command.application.MenuGroupService;
import kitchenpos.menu.query.dao.MenuGroupDao;
import kitchenpos.menu.query.dto.MenuGroupResponse;
import kitchenpos.menu.query.dto.MenuGroupResponses;
import kitchenpos.menu.ui.dto.MenuGroupRequest;

@RestController
public class MenuGroupRestController {

    private final MenuGroupService menuGroupService;
    private final MenuGroupDao menuGroupRepository;

    public MenuGroupRestController(MenuGroupService menuGroupService,
            MenuGroupDao menuGroupRepository) {
        this.menuGroupService = menuGroupService;
        this.menuGroupRepository = menuGroupRepository;
    }

    @PostMapping("/api/menu-groups")
    public ResponseEntity<MenuGroupResponse> create(
            @RequestBody @Valid final MenuGroupRequest menuGroupRequest) {
        final MenuGroupResponse menuGroupResponse = menuGroupService.create(menuGroupRequest);
        final URI uri = URI.create("/api/menu-groups/" + menuGroupResponse.getId());

        return ResponseEntity.created(uri)
                .body(menuGroupResponse);
    }

    @GetMapping("/api/menu-groups")
    public ResponseEntity<MenuGroupResponses> list() {
        return ResponseEntity.ok()
                .body(menuGroupRepository.select())
                ;
    }
}
