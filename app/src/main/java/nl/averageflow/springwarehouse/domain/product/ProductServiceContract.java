package nl.averageflow.springwarehouse.domain.product;

import nl.averageflow.springwarehouse.domain.product.dto.AddProductsRequestItem;
import nl.averageflow.springwarehouse.domain.product.dto.EditProductRequest;
import nl.averageflow.springwarehouse.domain.product.dto.ProductResponseItem;
import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import nl.averageflow.springwarehouse.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductServiceContract {
    Page<ProductResponseItem> getProducts(final Pageable pageable);

    ProductResponseItem getProductByUid(final UUID uid);

    void deleteProductByUid(final UUID uid);

    void addProducts(final Iterable<AddProductsRequestItem> rawItems);

    void sellProducts(final SellProductsRequest request);

    void reserveItemStock(final long wantedProductAmount, final Product product);

    ProductResponseItem editProduct(final UUID uid, final EditProductRequest request);
}
