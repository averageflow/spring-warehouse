package nl.averageflow.joeswarehouse.articles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArticleResponse {
    private HashMap<Long, Article> data;

    private List<Long> sort;

    public HashMap<Long, Article> getData() {
        return this.data;
    }

    public void setData(HashMap<Long, Article> data) {
        this.data = data;
    }

    public List<Long> getSort() {
        return this.sort;
    }

    public void setSort(List<Long> sort) {
        this.sort = sort;
    }

    public ArticleResponse(Set<Article> data) {
        HashMap<Long, Article> dataMap = new HashMap<Long, Article>();
        List<Long> dataSort = new ArrayList<Long>();

        for (Article article : data) {
            dataMap.put(article.getId(), article);
            dataSort.add(article.getId());
        }

        this.setData(dataMap);
        this.setSort(dataSort);
    }
}
