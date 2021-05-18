package kitchenpos.menugroup.ui;

import kitchenpos.menugroup.application.MenuGroupService;
import kitchenpos.menugroup.query.MenuGroupDao;
import kitchenpos.menugroup.query.dto.MenuGroupResponse;
import kitchenpos.menugroup.query.dto.MenuGroupResponses;
import kitchenpos.menugroup.ui.dto.MenuGroupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class MenuGroupRestController {

    private final MenuGroupService menuGroupService;
    private final MenuGroupDao menuGroupRepository;

    public MenuGroupRestController(MenuGroupService menuGroupService, MenuGroupDao menuGroupRepository) {
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
