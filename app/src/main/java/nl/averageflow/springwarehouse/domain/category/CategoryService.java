package nl.averageflow.springwarehouse.domain.category;

import nl.averageflow.springwarehouse.domain.category.dto.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.dto.EditCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService implements CategoryServiceContract {

    private final CategoryRepository categoryRepository;

    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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

    public CategoryResponseItem getCategoryByUid(final UUID uid) {
        final Optional<Category> searchResult = this.categoryRepository.findByUid(uid);
        if (searchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        final Category category = searchResult.get();

        return new CategoryResponseItem(
                category.getUid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public void addCategories(final Iterable<AddCategoriesRequestItem> rawItems) {
        rawItems.forEach(rawItem -> {
            final Category category = new Category(rawItem);
            this.categoryRepository.save(category);
        });
    }

    public CategoryResponseItem editCategory(final UUID uid, final EditCategoryRequest request) {
        final Optional<Category> searchResult = this.categoryRepository.findByUid(uid);

        if (searchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find item with wanted UUID");
        }

        final Category itemToUpdate = searchResult.get();

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

    public void deleteCategoryByUid(final UUID uid) {
        this.categoryRepository.deleteByUid(uid);
    }
}
