package nl.averageflow.springwarehouse.domain.category.service;

import nl.averageflow.springwarehouse.domain.category.dto.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.dto.EditCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.UUID;

public interface CategoryService {
    /**
     * Get a paginated list of categories in the system.
     *
     * @param pageable
     * @return a paginated list of categories.
     */
    Page<CategoryResponseItem> getCategories(final Pageable pageable);

    /**
     * Get a category by its UUID.
     *
     * @param uid is the UUID of the category.
     * @return the category item.
     */
    CategoryResponseItem getCategoryByUid(final UUID uid);

    /**
     * Add a collection of categories in the system.
     *
     * @param rawItems are the request items to be added.
     */
    void addCategories(final Collection<AddCategoriesRequestItem> rawItems);

    /**
     * Edit a category's properties by its UUID.
     *
     * @param uid     is the UUID of the article.
     * @param request is the request body containing the update payload.
     * @return the updated category.
     */
    CategoryResponseItem editCategory(final UUID uid, final EditCategoryRequest request);

    /**
     * Delete a category by its UUID.
     *
     * @param uid is the UUID of the category.
     */
    void deleteCategoryByUid(final UUID uid);
}
