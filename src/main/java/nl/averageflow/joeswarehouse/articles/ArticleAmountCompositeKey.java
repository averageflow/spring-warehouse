package nl.averageflow.joeswarehouse.articles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticleAmountCompositeKey implements Serializable {

    @Column(name = "product_id")
    Long productId;

    @Column(name = "article_id")
    Long articleId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}
