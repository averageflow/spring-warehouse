package nl.averageflow.springwarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "product_articles")
@Entity
public final class ArticleAmountInProduct implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @ManyToOne
    @JoinColumn(name = "article_uid", nullable = false)
    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_uid", nullable = false)
    private Product product;

    @Column(name = "amount_of", nullable = false)
    private long amountOf;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    protected ArticleAmountInProduct() {
    }

    public ArticleAmountInProduct(final Product product, final Article article, final long amountOf) {
        this.product = product;
        this.article = article;
        this.amountOf = amountOf;
    }

    public Article getArticle() {
        return this.article;
    }

    public long getAmountOf() {
        return this.amountOf;
    }

}