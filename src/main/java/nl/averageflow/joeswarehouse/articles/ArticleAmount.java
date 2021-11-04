package nl.averageflow.joeswarehouse.articles;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import nl.averageflow.joeswarehouse.products.Product;

@Entity
public class ArticleAmount {

    @EmbeddedId
    ArticleAmountCompositeKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("articleId")
    @JoinColumn(name = "article_id")
    Article article;

    int amountOf;

    public ArticleAmountCompositeKey getId() {
        return this.id;
    }

    public void setId(ArticleAmountCompositeKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getAmountOf() {
        return this.amountOf;
    }

    public void setAmountOf(int amountOf) {
        this.amountOf = amountOf;
    }

    protected ArticleAmount() {
    }
}
