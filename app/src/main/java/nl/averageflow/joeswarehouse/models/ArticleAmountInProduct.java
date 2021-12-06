package nl.averageflow.joeswarehouse.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Table(name = "product_articles")
@Entity
public final class ArticleAmountInProduct implements Serializable {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Id
    @NonNull
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Id
    @NonNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NonNull
    @Column(name = "amount_of")
    private Long amountOf;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @NonNull
    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    public Article getArticle() {
        return this.article;
    }

    public Long getAmountOf() {
        return this.amountOf;
    }

}