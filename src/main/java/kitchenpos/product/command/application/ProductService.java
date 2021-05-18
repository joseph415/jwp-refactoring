package kitchenpos.product.command.application;

import kitchenpos.product.command.domain.Product;
import kitchenpos.product.command.domain.ProductRepository;
import kitchenpos.product.query.dto.ProductResponse;
import kitchenpos.product.ui.dto.ProductRequest;
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

        return ProductResponse.of(product);
    }
}
