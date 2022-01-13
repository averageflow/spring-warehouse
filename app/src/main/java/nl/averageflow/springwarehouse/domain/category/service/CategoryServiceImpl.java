package nl.averageflow.springwarehouse.domain.category.service;

import nl.averageflow.springwarehouse.domain.category.dto.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.dto.EditCategoryRequest;
import nl.averageflow.springwarehouse.domain.category.model.Category;
import nl.averageflow.springwarehouse.domain.category.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryResponseItem> getCategories(final Pageable pageable) {
        final Page<Category> page = this.categoryRepository.findAll(pageable);

        return page.map(category -> new CategoryResponseItem(
                category.getUid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        ));
    }

    @Override
    public CategoryResponseItem getCategoryByUid(final UUID uid) {
        final Category category = this.categoryRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new CategoryResponseItem(
                category.getUid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    @Override
    public void addCategories(final Collection<AddCategoriesRequestItem> rawItems) {
        rawItems.forEach(rawItem -> {
            final var category = new Category(rawItem.name(), rawItem.description());
            this.categoryRepository.save(category);
        });
    }

    @Override
    public CategoryResponseItem editCategory(final UUID uid, final EditCategoryRequest request) {
        final Category itemToUpdate = this.categoryRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find item with wanted UUID"));

        itemToUpdate.setName(request.name());
        itemToUpdate.setDescription(request.description());

        final Category updatedItem = this.categoryRepository.save(itemToUpdate);

        return new CategoryResponseItem(
                updatedItem.getUid(),
                updatedItem.getName(),
                updatedItem.getDescription(),
                updatedItem.getCreatedAt(),
                updatedItem.getUpdatedAt()
        );
    }

    @Override
    public void deleteCategoryByUid(final UUID uid) {
        this.categoryRepository.deleteByUid(uid);
    }
}
