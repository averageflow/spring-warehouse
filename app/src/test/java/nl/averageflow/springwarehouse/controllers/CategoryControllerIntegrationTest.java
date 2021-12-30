package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.domain.category.CategoryController;
import nl.averageflow.springwarehouse.domain.category.CategoryService;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.user.UserService;
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

import java.sql.Timestamp;
import java.time.Instant;
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

    private CategoryResponseItem mockCategory;

    @BeforeEach
    void setUp() {
        this.mockCategory = new CategoryResponseItem(
                UUID.randomUUID(),
                "name",
                "description",
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now())
        );
    }

    @WithMockUser
    @Test
    public void getCategoryByUidShouldReturnCorrectServiceResponse() throws Exception {
        final UUID randomUid = UUID.randomUUID();
        when(categoryService.getCategoryByUid(randomUid)).thenReturn(this.mockCategory);

        this.mockMvc.perform(get("/api/categories/" + randomUid))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(this.mockCategory.name()))
                .andExpect(jsonPath("$.description").value(this.mockCategory.description()));
    }
}
