package nl.averageflow.springwarehouse.domain.category.service;


import nl.averageflow.springwarehouse.domain.category.dto.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.dto.EditCategoryRequest;
import nl.averageflow.springwarehouse.domain.category.model.Category;
import nl.averageflow.springwarehouse.domain.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        this.categoryService = new CategoryServiceImpl(this.categoryRepository);
    }

    @Test
    public void testGetCategoryByUid() {
        final Category category = mock(Category.class);
        final UUID uid = UUID.randomUUID();

        final CategoryResponseItem expectedResult = new CategoryResponseItem(
                category.getUid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );

        when(this.categoryRepository.findByUid(any())).thenReturn(Optional.of(category));

        final CategoryResponseItem sut = this.categoryService.getCategoryByUid(uid);

        assertEquals(sut, expectedResult);
        verify(this.categoryRepository, times(1)).findByUid(uid);
    }

    @Test
    public void testGetCategories() {
        final Category category = mock(Category.class);
        final Pageable pageable = mock(Pageable.class);

        final CategoryResponseItem formattedItem = new CategoryResponseItem(
                category.getUid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );

        final Page<Category> categoryPage = new PageImpl<>(List.of(category));

        when(this.categoryRepository.findAll(pageable)).thenReturn(categoryPage);

        final Page<CategoryResponseItem> sut = this.categoryService.getCategories(pageable);

        assertEquals(sut, new PageImpl<>(List.of(formattedItem)));
        verify(this.categoryRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testAddCategoriesEmptyIterable() {
        this.categoryService.addCategories(Collections.emptyList());

        verify(this.categoryRepository, times(0)).save(any());
    }

    @Test
    public void testAddCategories() {
        final AddCategoriesRequestItem addCategoriesRequestItem = new AddCategoriesRequestItem("name", "description");

        this.categoryService.addCategories(List.of(addCategoriesRequestItem, addCategoriesRequestItem, addCategoriesRequestItem));

        verify(this.categoryRepository, times(3)).save(any(Category.class));
    }

    @Test
    public void testDeleteCategoryByUid() {
        final UUID uid = UUID.randomUUID();

        this.categoryService.deleteCategoryByUid(uid);

        verify(this.categoryRepository, times(1)).deleteByUid(uid);
    }

    @Test
    public void testEditCategory(){
        final EditCategoryRequest category = new EditCategoryRequest("name", "description");
        final UUID uid = UUID.randomUUID();
        final Category categoryModel = new Category(category.name(), category.description());

        when(this.categoryRepository.findByUid(uid)).thenReturn(Optional.of(categoryModel));
        when(this.categoryRepository.save(any())).thenReturn(categoryModel);

        this.categoryService.editCategory(uid, category);

        verify(this.categoryRepository, times(1)).findByUid(uid);
    }
}
