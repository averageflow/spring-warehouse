package nl.averageflow.joeswarehouse.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import nl.averageflow.joeswarehouse.models.Article;

public final class ArticleResponse {
    private HashMap<Long, Article> data;

    private List<Long> sort;

    public HashMap<Long, Article> getData() {
        return this.data;
    }

    public List<Long> getSort() {
        return this.sort;
    }

    public ArticleResponse(Set<Article> data) {
        HashMap<Long, Article> dataMap = new HashMap<Long, Article>();
        List<Long> dataSort = new ArrayList<Long>();

        for (Article article : data) {
            dataMap.put(article.getId(), article);
            dataSort.add(article.getId());
        }

        this.data = dataMap;
        this.sort = dataSort;
    }
}
