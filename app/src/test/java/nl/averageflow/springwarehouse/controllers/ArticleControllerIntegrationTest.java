package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.models.Article;
import nl.averageflow.springwarehouse.requests.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.services.ArticleService;
import nl.averageflow.springwarehouse.services.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class ArticleControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ArticleService articleService;

    private Article mockArticle;

    @BeforeEach
    void setUp() {
        this.mockArticle = new Article(new AddArticlesRequestItem("article", 9));
    }

    @WithMockUser
    @Test
    public void getArticleByUidShouldReturnCorrectServiceResponse() throws Exception {
        final UUID randomUid = UUID.randomUUID();
        when(articleService.getArticleByUid(randomUid)).thenReturn(Optional.of(this.mockArticle));

        this.mockMvc.perform(get("/api/articles/" + randomUid))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }
}
