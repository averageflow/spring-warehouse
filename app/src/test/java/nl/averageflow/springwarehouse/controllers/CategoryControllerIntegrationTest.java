package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.category.CategoryController;
import nl.averageflow.springwarehouse.category.Category;
import nl.averageflow.springwarehouse.category.dto.AddCategoriesRequestItem;
import nl.averageflow.springwarehouse.category.CategoryService;
import nl.averageflow.springwarehouse.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class CategoryControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private UserService userService;

    private Category mockCategory;

    @BeforeEach
    void setUp() {
        this.mockCategory = new Category(new AddCategoriesRequestItem("name", "description"));
    }

    @WithMockUser
    @Test
    public void getCategoryByUidShouldReturnCorrectServiceResponse() throws Exception {
        final UUID randomUid = UUID.randomUUID();
        when(categoryService.getCategoryByUid(randomUid)).thenReturn(Optional.of(this.mockCategory));

        this.mockMvc.perform(get("/api/categories/" + randomUid))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(this.mockCategory.getName()))
                .andExpect(jsonPath("$.description").value(this.mockCategory.getDescription()));
    }
}
