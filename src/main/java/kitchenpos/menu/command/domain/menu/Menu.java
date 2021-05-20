package kitchenpos.menu.command.domain.menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import kitchenpos.menu.ui.dto.MenuRequest;
import kitchenpos.product.command.NotCreateMenu;
import kitchenpos.product.command.domain.product.Product;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "menu_group_id", nullable = false)
    private Long menuGroupId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id")
    private List<MenuProduct> menuProducts;

    protected Menu() {
    }

    public Menu(Long id, String name, BigDecimal price, Long menuGroupId,
            List<MenuProduct> menuProducts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
    }

    public static Menu of(List<Product> products, MenuRequest menuRequest) {
        Map<Long, Long> menuProduct = menuRequest.toMapProductQuantity();

        final BigDecimal sum = products.stream()
                .map(product -> product.getPrice()
                        .multiply(BigDecimal.valueOf(menuProduct.get(product.getId()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (menuRequest.getPrice().compareTo(sum) > 0) {
            throw new NotCreateMenu("메뉴에 속한 각 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 합니다.");
        }

        final List<MenuProduct> menuProducts = menuRequest.getMenuProducts()
                .stream()
                .map(menuProductRequest -> new MenuProduct(null,
                        menuProductRequest.getProductId(),
                        menuProductRequest.getQuantity()))
                .collect(Collectors.toList());

        return new Menu(null,
                menuRequest.getName(),
                menuRequest.getPrice(),
                menuRequest.getMenuGroupId(),
                menuProducts);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }
}
