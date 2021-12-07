package nl.averageflow.joeswarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "article_stocks")
@Entity
public final class ArticleStock {
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_uid", nullable = false)
    private Article article;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    protected ArticleStock() {
    }

    public ArticleStock(Article article, Long stock) {
        this.article = article;
        this.stock = stock;
    }

    public Long getStock() {
        return this.stock;
    }


}
