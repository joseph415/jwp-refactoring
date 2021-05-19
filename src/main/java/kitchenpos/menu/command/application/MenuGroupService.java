package kitchenpos.menu.command.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.command.domain.menugroup.MenuGroup;
import kitchenpos.menu.command.domain.menugroup.MenuGroupRepository;
import kitchenpos.menu.query.dto.MenuGroupResponse;
import kitchenpos.menu.ui.dto.MenuGroupRequest;

@Service
public class MenuGroupService {

    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupService(MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Transactional
    public MenuGroupResponse create(final MenuGroupRequest menuGroupRequest) {
        final MenuGroup menuGroup = menuGroupRepository.save(menuGroupRequest.toEntity());

        return MenuGroupResponse.from(menuGroup);
    }
}
