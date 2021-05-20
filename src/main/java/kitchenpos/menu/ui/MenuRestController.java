package kitchenpos.menu.ui;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kitchenpos.menu.command.application.MenuResponse;
import kitchenpos.menu.command.application.MenuService;
import kitchenpos.menu.infra.JpaMenuDao;
import kitchenpos.menu.query.dto.MenuViewResponses;
import kitchenpos.menu.ui.dto.MenuRequest;

@RestController
public class MenuRestController {
    private final MenuService menuService;
    private final JpaMenuDao jpaMenuDao;

    public MenuRestController(MenuService menuService, JpaMenuDao jpaMenuDao) {
        this.menuService = menuService;
        this.jpaMenuDao = jpaMenuDao;
    }

    @PostMapping("/api/menus")
    public ResponseEntity<MenuResponse> create(
            @RequestBody @Valid final MenuRequest menuRequest) {
        final MenuResponse created = menuService.create(menuRequest);
        final URI uri = URI.create("/api/menus/" + created.getId());

        return ResponseEntity.created(uri)
                .body(created)
                ;
    }

    @GetMapping("/api/menus")
    public ResponseEntity<MenuViewResponses> list() {
        return ResponseEntity.ok()
                .body(jpaMenuDao.select());
    }
}
