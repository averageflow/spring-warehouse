package nl.averageflow.joeswarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "product_articles")
@Entity
public final class ArticleAmountInProduct implements Serializable {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "product_id")
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

    public Article getArticle() {
        return this.article;
    }

    public Long getAmountOf() {
        return this.amountOf;
    }

}