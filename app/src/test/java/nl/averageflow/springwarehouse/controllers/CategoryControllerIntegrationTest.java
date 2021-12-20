package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.models.Category;
import nl.averageflow.springwarehouse.requests.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private Category mockCategory;

    @BeforeEach
    void setUp() {
        this.mockCategory = new Category(new AddCategoriesRequestItem("name", "description"));
    }

    @Test
    public void getCategoryByUidShouldReturnCorrectServiceResponse() throws Exception {
        final UUID randomUid = UUID.randomUUID();
        when(categoryService.getCategoryByUid(randomUid)).thenReturn(Optional.of(this.mockCategory));

        this.mockMvc.perform(get("/api/categories/" + randomUid))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
