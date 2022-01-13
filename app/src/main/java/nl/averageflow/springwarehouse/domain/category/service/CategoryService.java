package nl.averageflow.springwarehouse.domain.category.service;

import nl.averageflow.springwarehouse.domain.category.dto.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.dto.EditCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    Page<CategoryResponseItem> getCategories(final Pageable pageable);

    CategoryResponseItem getCategoryByUid(final UUID uid);

    void addCategories(final Iterable<AddCategoriesRequestItem> rawItems);

    CategoryResponseItem editCategory(final UUID uid, final EditCategoryRequest request);

    void deleteCategoryByUid(final UUID uid);
}
