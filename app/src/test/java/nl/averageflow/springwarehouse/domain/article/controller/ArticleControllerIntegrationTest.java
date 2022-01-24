package nl.averageflow.springwarehouse.domain.article.controller;

import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.service.ArticleServiceImpl;
import nl.averageflow.springwarehouse.domain.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class ArticleControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private ArticleServiceImpl articleServiceImpl;

    private ArticleResponseItem mockArticle;

    @BeforeEach
    void setUp() {
        this.mockArticle = new ArticleResponseItem(
                UUID.randomUUID(),
                "name",
                9,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now())
        );
    }

    @WithMockUser
    @Test
    public void getArticleByUidShouldReturnCorrectServiceResponse() throws Exception {
        final var randomUid = UUID.randomUUID();
        when(this.articleServiceImpl.getArticleByUid(randomUid)).thenReturn(this.mockArticle);

        this.mockMvc.perform(get("/api/articles/" + randomUid))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
