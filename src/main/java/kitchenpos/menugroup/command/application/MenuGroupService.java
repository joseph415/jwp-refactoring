package kitchenpos.menugroup.command.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menugroup.command.domain.MenuGroup;
import kitchenpos.menugroup.command.domain.MenuGroupRepository;
import kitchenpos.menugroup.query.dto.MenuGroupResponse;
import kitchenpos.menugroup.ui.dto.MenuGroupRequest;

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
