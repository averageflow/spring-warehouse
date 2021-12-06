package nl.averageflow.joeswarehouse.responses;

import nl.averageflow.joeswarehouse.models.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public final class ArticleResponse {
    private final HashMap<Long, Article> data;

    private final List<Long> sort;

    public ArticleResponse(Set<Article> data) {
        HashMap<Long, Article> dataMap = new HashMap<Long, Article>();
        List<Long> dataSort = new ArrayList<>();

        for (Article article : data) {
            dataMap.put(article.getId(), article);
            dataSort.add(article.getId());
        }

        this.data = dataMap;
        this.sort = dataSort;
    }

    public HashMap<Long, Article> getData() {
        return this.data;
    }

    public List<Long> getSort() {
        return this.sort;
    }
}
