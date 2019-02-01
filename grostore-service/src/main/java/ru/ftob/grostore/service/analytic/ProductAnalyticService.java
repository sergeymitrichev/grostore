package ru.ftob.grostore.service.analytic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ftob.grostore.model.analytics.ProductAnalyticByOrigin;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.service.BaseService;

public interface ProductAnalyticService extends BaseService<ProductAnalyticByOrigin, Integer> {

    Page<ProductAnalyticByOrigin> getByAllProduct(Product product, Pageable pageable);
}
