package nl.averageflow.springwarehouse.responses;

import nl.averageflow.springwarehouse.models.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public final class ArticleResponse {
    private final HashMap<UUID, Article> data;

    private final Iterable<UUID> sort;

    public ArticleResponse(Iterable<Article> data) {
        HashMap<UUID, Article> dataMap = new HashMap<>();
        ArrayList<UUID> dataSort = new ArrayList<>();

        data.forEach(article -> {
            dataMap.put(article.getUid(), article);
            dataSort.add(article.getUid());
        });

        this.data = dataMap;
        this.sort = dataSort;
    }

    public HashMap<UUID, Article> getData() {
        return this.data;
    }

    public Iterable<UUID> getSort() {
        return this.sort;
    }
}
