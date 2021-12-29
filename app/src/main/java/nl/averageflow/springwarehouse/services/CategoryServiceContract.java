package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.Category;
import nl.averageflow.springwarehouse.requests.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.requests.EditCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CategoryServiceContract {
    Page<Category> getCategories(final Pageable pageable);

    Optional<Category> getCategoryByUid(final UUID uid);

    void addCategories(final Iterable<AddCategoriesRequestItem> rawItems);

    Category editCategory(final UUID uid, final EditCategoryRequest request);

    void deleteCategoryByUid(final UUID uid);
}
