package nl.averageflow.springwarehouse.category;

import nl.averageflow.springwarehouse.category.dto.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.category.dto.EditCategoryRequest;
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
