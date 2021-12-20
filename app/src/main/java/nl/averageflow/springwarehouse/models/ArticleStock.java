package nl.averageflow.springwarehouse.models;

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
    private long stock;

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

    public ArticleStock(final Article article, final long stock) {
        this.article = article;
        this.stock = stock;
    }

    public long getStock() {
        return this.stock;
    }

    public void setStock(final long stock) {
        this.stock = stock;
    }


}
