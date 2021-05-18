package kitchenpos.menu.command.application;

import kitchenpos.menu.command.domain.product.Product;
import kitchenpos.menu.command.domain.product.ProductRepository;
import kitchenpos.menu.query.dto.ProductRequest;
import kitchenpos.menu.query.dto.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(final ProductRequest productRequest) {
        final Product product = productRepository.save(productRequest.toEntity());

        return ProductResponse.from(product);
    }
}
