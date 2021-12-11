package nl.averageflow.springwarehouse.controllers;

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
