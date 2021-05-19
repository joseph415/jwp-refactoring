package kitchenpos.menu.command.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.command.domain.menu.Menu;
import kitchenpos.menu.command.domain.menu.MenuProduct;
import kitchenpos.menu.command.domain.menu.MenuRepository;
import kitchenpos.menu.command.domain.menugroup.MenuGroupRepository;
import kitchenpos.menu.command.domain.menuproduct.MenuProductRepository;
import kitchenpos.menu.query.dto.MenuResponse;
import kitchenpos.menu.ui.dto.MenuRequest;
import kitchenpos.product.command.domain.product.ProductRepository;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final MenuProductRepository menuProductRepository;
    private final ProductRepository productRepository;

    public MenuService(MenuRepository menuRepository,
            MenuGroupRepository menuGroupRepository,
            MenuProductRepository menuProductRepository,
            ProductRepository productRepository) {
        this.menuRepository = menuRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.menuProductRepository = menuProductRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public MenuResponse create(final MenuRequest menuRequest) {
        checkExistMenuGroup(menuRequest);

        Menu menu = Menu.of(
                productRepository.findAllByIdIn(menuRequest.getMenuProducts().getProductId()),
                menuRequest.getMenuProducts().getQuantity(),
                menuRequest.getName(),
                menuRequest.getPrice(),
                menuRequest.getMenuGroupId()
        );
        final Menu savedMenu = menuRepository.save(menu);

        final List<MenuProduct> menuProducts = menuRequest.getMenuProducts()
                .getProductId()
                .stream()
                .map(productId -> new MenuProduct(savedMenu.getId(), productId,
                        menuRequest.getMenuProducts().getQuantity()))
                .collect(Collectors.toList());
        menuProductRepository.saveAll(menuProducts);

        return MenuResponse.of(savedMenu, menuProducts);
    }

    private void checkExistMenuGroup(MenuRequest menuRequest) {
        if (!menuGroupRepository.existsById(menuRequest.getMenuGroupId())) {
            throw new IllegalArgumentException();
        }
    }
}
