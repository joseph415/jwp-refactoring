package kitchenpos.menu.ui;

import kitchenpos.menu.command.application.ProductService;
import kitchenpos.menu.query.dao.ProductDao;
import kitchenpos.menu.query.dto.ProductRequest;
import kitchenpos.menu.query.dto.ProductResponse;
import kitchenpos.menu.query.dto.ProductResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ProductRestController {
    private final ProductService productService;
    private final ProductDao productDao;

    public ProductRestController(ProductService productService, ProductDao productDao) {
        this.productService = productService;
        this.productDao = productDao;
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid final ProductRequest productRequest) {
        final ProductResponse productResponse = productService.create(productRequest);

        final URI uri = URI.create("/api/products/" + productResponse.getId());

        return ResponseEntity.created(uri)
                .body(productResponse);
    }

    @GetMapping("/api/products")
    public ResponseEntity<ProductResponses> list() {
        return ResponseEntity.ok()
                .body(productDao.select());
    }
}
