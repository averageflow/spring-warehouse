package nl.averageflow.springwarehouse.services;


import nl.averageflow.springwarehouse.domain.category.Category;
import nl.averageflow.springwarehouse.domain.category.CategoryRepository;
import nl.averageflow.springwarehouse.domain.category.CategoryService;
import nl.averageflow.springwarehouse.domain.category.CategoryServiceContract;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Test
    public void testGetCategoryByUid(){
        final CategoryRepository categoryRepository = mock(CategoryRepository.class);
        final Category category = mock(Category.class);

        final CategoryServiceContract categoryService = new CategoryService(categoryRepository);

        when(categoryRepository.findByUid(any())).thenReturn(Optional.of(category));

        final CategoryResponseItem sut = new CategoryResponseItem(
                category.getUid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );

        assertEquals(categoryService.getCategoryByUid(UUID.randomUUID()), sut);
    }

    @Test
    public void testGetCategories(){
        final CategoryRepository categoryRepository = mock(CategoryRepository.class);
        final Category category = mock(Category.class);
        final Pageable pageable = mock(Pageable.class);

        final CategoryServiceContract categoryService = new CategoryService(categoryRepository);

        final CategoryResponseItem sut = new CategoryResponseItem(
                category.getUid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );

        final Page<Category> categoryPage = new PageImpl<>(List.of(category));

        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);

        assertEquals(categoryService.getCategories(pageable), new PageImpl<>(List.of(sut)));
    }
}
