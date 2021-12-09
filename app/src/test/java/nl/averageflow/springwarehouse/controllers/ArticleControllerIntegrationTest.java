package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.models.Article;
import nl.averageflow.springwarehouse.requests.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(ArticleController.class)
//public class ArticleControllerIntegrationTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ArticleService articleService;
//
//    private Article mockArticle;
//
//    @BeforeEach
//    void setUp(){
//        this.mockArticle = new Article(new AddArticlesRequestItem(1,"article", 9 ));
//    }
//
//    @Test
//    public void getArticleByUidShouldReturnCorrectServiceResponse() throws Exception {
//        UUID randomUid = UUID.randomUUID();
//        when(articleService.getArticleByUid(randomUid)).thenReturn(Optional.of(this.mockArticle));
//
//        this.mockMvc.perform(get("/api/articles/"+ randomUid))
//                .andDo(print())
//                .andExpect(status().is5xxServerError());
//    }
//}
