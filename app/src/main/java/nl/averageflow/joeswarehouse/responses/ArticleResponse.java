package nl.averageflow.joeswarehouse.responses;

import nl.averageflow.joeswarehouse.models.Article;

import java.util.*;

public final class ArticleResponse {
    private final HashMap<UUID, Article> data;

    private final List<UUID> sort;

    public ArticleResponse(Set<Article> data) {
        HashMap<UUID, Article> dataMap = new HashMap<>();
        List<UUID> dataSort = new ArrayList<>();

        for (Article article : data) {
            dataMap.put(article.getId(), article);
            dataSort.add(article.getId());
        }

        this.data = dataMap;
        this.sort = dataSort;
    }

    public HashMap<UUID, Article> getData() {
        return this.data;
    }

    public List<UUID> getSort() {
        return this.sort;
    }
}
