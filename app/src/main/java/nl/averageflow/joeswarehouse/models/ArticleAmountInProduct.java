package nl.averageflow.joeswarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "product_articles")
@Entity
public final class ArticleAmountInProduct implements Serializable {
    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "uid")
    private UUID uid;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "article_uid")
    private Article article;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "product_uid")
    private Product product;

    @NonNull
    @Column(name = "amount_of")
    private Long amountOf;

    @NonNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @NonNull
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    protected ArticleAmountInProduct() {
    }

    public ArticleAmountInProduct(Product product, Article article, Long amountOf) {
        this.product = product;
        this.article = article;
        this.amountOf = amountOf;
    }

    public Article getArticle() {
        return this.article;
    }

    public Long getAmountOf() {
        return this.amountOf;
    }

}