package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.Category;
import nl.averageflow.springwarehouse.repositories.CategoryRepository;
import nl.averageflow.springwarehouse.requests.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.requests.EditCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getCategories(final Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategoryByUid(final UUID uid) {
        return this.categoryRepository.findByUid(uid);
    }

    public void addCategories(final Iterable<AddCategoriesRequestItem> rawItems) {
        rawItems.forEach(rawItem -> {
            final Category article = new Category(rawItem);
            this.categoryRepository.save(article);
        });
    }

    public Category editCategory(final UUID uid, final EditCategoryRequest request) {
        final Optional<Category> wantedArticleSearchResult = this.categoryRepository.findByUid(uid);

        if (wantedArticleSearchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find item with wanted UUID");
        }

        final Category itemToUpdate = wantedArticleSearchResult.get();

        //itemToUpdate.setName(request.getName());

        return this.categoryRepository.save(itemToUpdate);
    }

    public void deleteCategoryByUid(final UUID uid) {
        this.categoryRepository.deleteByUid(uid);
    }
}
