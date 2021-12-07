package nl.averageflow.joeswarehouse.requests;

import java.util.List;

public final class AddProductsRequestItem {
    private String itemId;
    private String name;
    private List<AddProductsRequestArticleItem> containArticles;


    /*
    *
    *
    *
    * "item_id": "1",
            "name": "Dining Chair",
						"price": 99.99,
            "contain_articles": [
                {
                    "art_id": "1",
                    "amount_of": "4"
                },
                {
                    "art_id": "2",
                    "amount_of": "8"
                },
                {
                    "art_id": "3",
                    "amount_of": "1"
                }
            ]
    * */
}
